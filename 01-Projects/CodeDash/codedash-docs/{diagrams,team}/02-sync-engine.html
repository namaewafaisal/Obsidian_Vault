<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>CodeDash — Sync Engine</title>
<style>
  body { font-family: system-ui, sans-serif; background: #f9f9f7; margin: 0; padding: 24px; }
  h2 { font-size: 18px; font-weight: 500; color: #1a1a18; margin: 0 0 4px; }
  p  { font-size: 13px; color: #5f5e5a; margin: 0 0 20px; }
</style>
</head>
<body>
<h2>Sync Engine — Queue and Adapter Design</h2>
<p>Cron enqueues jobs → per-platform queues with different concurrency → adapters fetch → two writes to MongoDB.</p>
<svg width="100%" viewBox="0 0 720 530" xmlns="http://www.w3.org/2000/svg">
  <defs>
    <marker id="arr" viewBox="0 0 10 10" refX="8" refY="5" markerWidth="6" markerHeight="6" orient="auto-start-reverse">
      <path d="M2 1L8 5L2 9" fill="none" stroke="#888780" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
    </marker>
  </defs>

  <!-- Cron -->
  <rect x="260" y="20" width="200" height="44" rx="8" fill="#F1EFE8" stroke="#B4B2A9" stroke-width="0.8"/>
  <text x="360" y="47" text-anchor="middle" font-size="13" font-weight="500" fill="#2C2C2A">Cron scheduler</text>

  <line x1="360" y1="64" x2="360" y2="104" stroke="#888780" stroke-width="1.2" marker-end="url(#arr)"/>

  <!-- Queue -->
  <rect x="230" y="104" width="260" height="44" rx="8" fill="#E1F5EE" stroke="#5DCAA5" stroke-width="0.8"/>
  <text x="360" y="131" text-anchor="middle" font-size="13" font-weight="500" fill="#085041">BullMQ job queue</text>

  <!-- Fan out to adapters -->
  <path d="M276 148 L276 184 L116 184 L116 210" fill="none" stroke="#888780" stroke-width="1" marker-end="url(#arr)"/>
  <path d="M316 148 L316 184 L240 184 L240 210" fill="none" stroke="#888780" stroke-width="1" marker-end="url(#arr)"/>
  <path d="M360 148 L360 210" fill="none" stroke="#888780" stroke-width="1" marker-end="url(#arr)"/>
  <path d="M404 148 L404 184 L476 184 L476 210" fill="none" stroke="#888780" stroke-width="1" marker-end="url(#arr)"/>
  <path d="M444 148 L444 184 L596 184 L596 210" fill="none" stroke="#888780" stroke-width="1" marker-end="url(#arr)"/>

  <!-- Platform adapter boxes -->
  <rect x="60" y="210" width="114" height="56" rx="8" fill="#E1F5EE" stroke="#5DCAA5" stroke-width="0.8"/>
  <text x="117" y="232" text-anchor="middle" font-size="13" font-weight="500" fill="#085041">LeetCode</text>
  <text x="117" y="252" text-anchor="middle" font-size="11" fill="#0F6E56">GraphQL API</text>
  <text x="117" y="264" text-anchor="middle" font-size="10" fill="#1D9E75">concurrency: 3</text>

  <rect x="186" y="210" width="114" height="56" rx="8" fill="#E1F5EE" stroke="#5DCAA5" stroke-width="0.8"/>
  <text x="243" y="232" text-anchor="middle" font-size="13" font-weight="500" fill="#085041">Codeforces</text>
  <text x="243" y="252" text-anchor="middle" font-size="11" fill="#0F6E56">REST API</text>
  <text x="243" y="264" text-anchor="middle" font-size="10" fill="#1D9E75">concurrency: 5</text>

  <rect x="312" y="210" width="114" height="56" rx="8" fill="#FAECE7" stroke="#F0997B" stroke-width="0.8"/>
  <text x="369" y="232" text-anchor="middle" font-size="13" font-weight="500" fill="#712B13">CodeChef</text>
  <text x="369" y="252" text-anchor="middle" font-size="11" fill="#993C1D">HTML scrape</text>
  <text x="369" y="264" text-anchor="middle" font-size="10" fill="#D85A30">concurrency: 1</text>

  <rect x="438" y="210" width="114" height="56" rx="8" fill="#FAECE7" stroke="#F0997B" stroke-width="0.8"/>
  <text x="495" y="232" text-anchor="middle" font-size="13" font-weight="500" fill="#712B13">HackerRank</text>
  <text x="495" y="252" text-anchor="middle" font-size="11" fill="#993C1D">Playwright</text>
  <text x="495" y="264" text-anchor="middle" font-size="10" fill="#D85A30">concurrency: 1</text>

  <rect x="556" y="210" width="104" height="56" rx="8" fill="#FAECE7" stroke="#F0997B" stroke-width="0.8"/>
  <text x="608" y="232" text-anchor="middle" font-size="13" font-weight="500" fill="#712B13">GFG</text>
  <text x="608" y="252" text-anchor="middle" font-size="11" fill="#993C1D">HTML scrape</text>
  <text x="608" y="264" text-anchor="middle" font-size="10" fill="#D85A30">concurrency: 1</text>

  <!-- Legend -->
  <rect x="60" y="282" width="12" height="12" rx="2" fill="#1D9E75" opacity="0.8"/>
  <text x="78" y="293" font-size="11" fill="#444441">Official API — safe to parallelize</text>
  <rect x="240" y="282" width="12" height="12" rx="2" fill="#D85A30" opacity="0.8"/>
  <text x="258" y="293" font-size="11" fill="#444441">Scraping — serial only to avoid blocks</text>

  <!-- Rate limiter box -->
  <rect x="60" y="312" width="600" height="80" rx="12" fill="none" stroke="#FAC775" stroke-width="0.8" stroke-dasharray="5 3"/>
  <text x="78" y="332" font-size="11" fill="#854F0B">RATE LIMITER — wraps every adapter call</text>
  <rect x="78" y="342" width="150" height="36" rx="6" fill="#FAEEDA" stroke="#EF9F27" stroke-width="0.8"/>
  <text x="153" y="365" text-anchor="middle" font-size="12" font-weight="500" fill="#633806">Delay + jitter</text>
  <rect x="244" y="342" width="170" height="36" rx="6" fill="#FAEEDA" stroke="#EF9F27" stroke-width="0.8"/>
  <text x="329" y="365" text-anchor="middle" font-size="12" font-weight="500" fill="#633806">Exponential backoff</text>
  <rect x="430" y="342" width="210" height="36" rx="6" fill="#FAEEDA" stroke="#EF9F27" stroke-width="0.8"/>
  <text x="535" y="365" text-anchor="middle" font-size="12" font-weight="500" fill="#633806">Dead letter + failure log</text>

  <!-- Two write pattern -->
  <line x1="360" y1="392" x2="360" y2="422" stroke="#888780" stroke-width="1.2" marker-end="url(#arr)"/>
  <rect x="60" y="422" width="290" height="52" rx="8" fill="#E1F5EE" stroke="#5DCAA5" stroke-width="0.8"/>
  <text x="205" y="443" text-anchor="middle" font-size="13" font-weight="500" fill="#085041">Write 1: upsert platform_data</text>
  <text x="205" y="463" text-anchor="middle" font-size="11" fill="#0F6E56">full payload stored as-is</text>
  <rect x="370" y="422" width="290" height="52" rx="8" fill="#FAEEDA" stroke="#EF9F27" stroke-width="0.8"/>
  <text x="515" y="443" text-anchor="middle" font-size="13" font-weight="500" fill="#633806">Write 2: $set students summary</text>
  <text x="515" y="463" text-anchor="middle" font-size="11" fill="#854F0B">lc.solved, lc.hard, cf.rating…</text>
</svg>

<div style="margin-top:20px; background:#fff; border:1px solid #e8e6e0; border-radius:10px; padding:16px; font-size:13px; color:#444441; line-height:1.7">
  <strong style="display:block; margin-bottom:8px; color:#1a1a18">Time math — 2000 students</strong>
  <table style="border-collapse:collapse; width:100%">
    <tr style="background:#f1efe8">
      <th style="padding:6px 12px; text-align:left; font-weight:500; border-bottom:1px solid #e8e6e0">Platform</th>
      <th style="padding:6px 12px; text-align:left; font-weight:500; border-bottom:1px solid #e8e6e0">Concurrency</th>
      <th style="padding:6px 12px; text-align:left; font-weight:500; border-bottom:1px solid #e8e6e0">Delay/req</th>
      <th style="padding:6px 12px; text-align:left; font-weight:500; border-bottom:1px solid #e8e6e0">Total time</th>
    </tr>
    <tr><td style="padding:6px 12px; border-bottom:1px solid #f1efe8">LeetCode</td><td style="padding:6px 12px; border-bottom:1px solid #f1efe8">3</td><td style="padding:6px 12px; border-bottom:1px solid #f1efe8">1s</td><td style="padding:6px 12px; border-bottom:1px solid #f1efe8">~11 min</td></tr>
    <tr><td style="padding:6px 12px; border-bottom:1px solid #f1efe8">Codeforces</td><td style="padding:6px 12px; border-bottom:1px solid #f1efe8">5</td><td style="padding:6px 12px; border-bottom:1px solid #f1efe8">0.2s</td><td style="padding:6px 12px; border-bottom:1px solid #f1efe8">~1.5 min</td></tr>
    <tr><td style="padding:6px 12px; border-bottom:1px solid #f1efe8">CodeChef</td><td style="padding:6px 12px; border-bottom:1px solid #f1efe8">1 (serial)</td><td style="padding:6px 12px; border-bottom:1px solid #f1efe8">10s</td><td style="padding:6px 12px; border-bottom:1px solid #f1efe8">~5.5 hrs</td></tr>
    <tr><td style="padding:6px 12px; border-bottom:1px solid #f1efe8">HackerRank</td><td style="padding:6px 12px; border-bottom:1px solid #f1efe8">1 (serial)</td><td style="padding:6px 12px; border-bottom:1px solid #f1efe8">15s</td><td style="padding:6px 12px; border-bottom:1px solid #f1efe8">~8 hrs</td></tr>
    <tr><td style="padding:6px 12px">GFG</td><td style="padding:6px 12px">1 (serial)</td><td style="padding:6px 12px">10s</td><td style="padding:6px 12px">~5.5 hrs</td></tr>
  </table>
  <p style="margin:12px 0 0; color:#5f5e5a">All queues drain in parallel. Wall-clock time = slowest queue (8 hrs). Sync starts 2am, done by 10am. Fresh data for college hours.</p>
</div>
</body>
</html>
