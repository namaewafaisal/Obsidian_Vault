# 05 — Platform Guide

## Summary table

| Platform | Method | Reliability | Sync interval | Auth needed | Cost |
|---|---|---|---|---|---|
| LeetCode | GraphQL API (unofficial but stable) | High | Every 6h | None | Free |
| Codeforces | REST API (official) | High | Every 6h | None | Free |
| CodeChef | HTML scrape | Medium | Once/day | None | Free |
| HackerRank | HTML scrape (Playwright) | Low-Medium | Once/day | None | Free |
| GFG | HTML scrape (Cheerio) | Low | Once/day | None | Free |

---

## LeetCode

**Method:** Unofficial but stable GraphQL endpoint at
`https://leetcode.com/graphql/`

No authentication needed for public profile data. This is what the entire
community uses and it has been stable for years. Do not use third-party wrapper
libraries — hit the endpoint directly from your backend.

**What you can get:**
- Problems solved (easy/medium/hard breakdown)
- Contest rating and ranking
- Recent submissions (last 20)
- Topic-wise problem count
- Badges

**Rate limit:** Keep to 1 request/second. They don't publish a hard limit but
1 req/sec has never caused issues at this scale.

**Sample query:**
```graphql
query getUserProfile($username: String!) {
  matchedUser(username: $username) {
    username
    submitStats: submitStatsGlobal {
      acSubmissionNum {
        difficulty
        count
      }
    }
    profile {
      ranking
    }
    userContestRanking {
      rating
      globalRanking
    }
  }
}
```

**Adapter pattern:**
```js
// adapters/leetcode.js
export async function fetch(username) {
  const res = await axios.post('https://leetcode.com/graphql/', {
    query: LC_PROFILE_QUERY,
    variables: { username }
  }, {
    headers: { 'Content-Type': 'application/json' }
  })
  return transformLeetCodeResponse(res.data)
}
```

---

## Codeforces

**Method:** Official public REST API. No auth needed.

**Endpoints used:**
- `https://codeforces.com/api/user.info?handles=username` — rating, rank
- `https://codeforces.com/api/user.rating?handle=username` — contest history
- `https://codeforces.com/api/user.status?handle=username&count=20` — recent submissions

**Rate limit:** Official docs say max 5 requests/second. Stay at 1 req/sec to
be safe.

**What you can get:**
- Current rating and max rating
- Rank (newbie/pupil/specialist/expert/candidate master etc.)
- Contest participation history
- Recent accepted submissions

**Very reliable.** This API has been stable for many years.

---

## CodeChef

**The real situation:** CodeChef's official OAuth API exists but access requires
application approval that they essentially don't respond to for students. Don't
bother applying.

**What works:** Scraping `https://www.codechef.com/users/{username}` with
Cheerio (server-rendered HTML, no JS needed).

**What you can get:**
- Current rating and star level
- Problems solved count
- Contest history

**Anti-block strategy:**
- 1 request per 10 seconds minimum
- Add random jitter (8–14 seconds range)
- Rotate User-Agent header between requests
- Only sync once per day
- Accept that it will occasionally break when CodeChef updates their HTML

**Known fragility:** CodeChef has been actively changing their frontend. Your
selectors will break. Design for this — when the scraper fails, log the error
with the full HTML snippet that caused the failure so you can quickly update
the selector.

```js
// adapters/codechef.js
import * as cheerio from 'cheerio'
import axios from 'axios'

export async function fetch(username) {
  const { data: html } = await axios.get(
    `https://www.codechef.com/users/${username}`,
    { headers: { 'User-Agent': getRandomUserAgent() } }
  )
  const $ = cheerio.load(html)

  // these selectors WILL break eventually — update when they do
  const rating = parseInt($('.rating-number').text().trim())
  const stars = $('.rating-star').length
  const solved = parseInt($('.problems-solved h3').text().match(/\d+/)?.[0] ?? 0)

  if (isNaN(rating)) {
    throw new Error(`CodeChef scrape failed for ${username} — selector may have changed`)
  }
  return { rating, stars, problems_solved: solved }
}
```

---

## HackerRank

**Method:** Playwright headless browser scraping.
URL: `https://www.hackerrank.com/{username}/profile`

**Why Playwright and not Cheerio?**
HackerRank's profile page is client-side rendered (React). The HTML served by
the server is an empty shell — the actual content loads after JavaScript runs.
Cheerio only parses static HTML, so it gets nothing. Playwright runs a real
browser and waits for the content to render.

**What you can get:**
- Total badges count
- Gold/silver/bronze badge breakdown
- Domain scores (Problem Solving, Python, SQL etc.)

**Anti-block strategy:**
- 1 request per 15 seconds, with jitter (12–20 seconds range)
- Rotate User-Agent
- Run in non-headless mode temporarily if getting blocked (harder to detect)
- Once per day only
- Use Playwright's `page.waitForSelector` not arbitrary `sleep` — more reliable

**CRITICAL DEPLOYMENT NOTE:**
Playwright installs Chromium binaries (~200MB). This is too large for Vercel
serverless functions (50MB limit). Deploy the HackerRank worker as a separate
small process on Render free tier or Railway. It just needs to run the Playwright
jobs and write results to the same MongoDB.

```js
// adapters/hackerrank.js
import { chromium } from 'playwright'

export async function fetch(username) {
  const browser = await chromium.launch({ headless: true })
  const page = await browser.newPage()
  await page.setExtraHTTPHeaders({ 'User-Agent': getRandomUserAgent() })

  try {
    await page.goto(`https://www.hackerrank.com/${username}/profile`,
                    { waitUntil: 'networkidle' })
    await page.waitForSelector('.hacker-badge', { timeout: 10000 })

    const badges = await page.$$eval('.hacker-badge', els => els.length)
    // extract more fields as needed

    return { badges }
  } finally {
    await browser.close()
  }
}
```

---

## GeeksForGeeks

**Method:** Cheerio HTML scrape.
URL: `https://www.geeksforgeeks.org/user/{username}/`

GFG's profile page is server-rendered, so Cheerio works. However, GFG rate-limits
aggressively and their HTML structure changes somewhat frequently.

**What you can get:**
- Problems solved count
- GFG score
- Institution name (useful to verify it's the right user)
- Streak

**Anti-block strategy:**
- Same as CodeChef — 10s delay with jitter, once per day
- GFG is more aggressive than CodeChef — if you get 429 responses,
  increase delay to 20–30 seconds

---

## Handling missing handles

Not all students will have all 5 platforms. The system must handle this gracefully.

```js
// when enqueuing, only queue platforms where a handle exists
for (const platform of PLATFORMS) {
  if (student.handles[platform]) {
    await queues[platform].add('sync', { ... })
  }
  // if no handle registered, skip silently
}
```

On the dashboard, show "—" for platforms the student hasn't registered.
Never show "0" — it's ambiguous (did they solve 0 or is the handle not registered?).

---

## CodeChef API situation — full context

The "official API works only during contests" you heard is about the contest
participation endpoints specifically. For user profile data, there is no reliable
free API. The situation is:

- OAuth API exists — requires approval, not granted to students
- Unofficial wrappers exist on npm/PyPI — they scrape CodeChef, same fragility
- Contest endpoints — work during active contests only, useless for profile data

**Conclusion:** Scrape `codechef.com/users/{username}`, accept the fragility,
design for graceful failure.
