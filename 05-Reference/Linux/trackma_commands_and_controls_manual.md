# Trackma Commands & Controls Manual

A practical command reference for Trackma.

---

# Prompt Structure

Example prompt:

```text
Faizal [anilist] (anime) watching >>
```

Meaning:

- `Faizal` → account
- `anilist` → tracking service
- `anime` → current media type
- `watching` → current filter

You type commands after `>>`.

---

# Core Idea

Trackma works in 3 stages:

1. Make changes locally
2. Changes enter queue
3. Run `send` to sync online

Always remember:

```bash
send
```

Otherwise AniList/MAL will NOT update.

---

# MOST IMPORTANT COMMANDS

| Command | What it does |
|---|---|
| list | show anime/manga |
| search | search local list |
| add | add anime/manga |
| update | update episode/chapter |
| status | change status |
| score | rate anime/manga |
| info | detailed information |
| delete | remove entry |
| send | sync online |
| viewqueue | show pending changes |
| mediatype | switch anime/manga |
| filter | filter by status |
| play | play local episode |

---

# LISTING ENTRIES

## Show all anime/manga

```bash
list
```

Example:

```text
1 Frieren
2 JoJo's Bizarre Adventure (TV)
3 Vinland Saga
```

The number on left is the local ID.

You should mostly use IDs.

---

# SEARCHING

## Search local list

```bash
search jojo
```

Example:

```text
2 JoJo's Bizarre Adventure (TV)
```

Now you can use:

```bash
update 2
```

instead of typing full name.

---

# ADDING ANIME/MANGA

## Add anime

```bash
add frieren
```

Trackma searches AniList/MAL.

Example:

```text
1: Frieren: Beyond Journey's End
2: Frieren Specials
Choose show to add:
```

Choose number:

```text
1
```

Then sync:

```bash
send
```

---

# UPDATING PROGRESS

# Increment by 1

```bash
update 2
```

Example:
- episode 4 → episode 5

Useful while watching.

---

# Set exact episode/chapter

```bash
update 2 12
```

Meaning:
- anime/manga ID = 2
- progress = 12

---

# Using exact title

```bash
update "JoJo's Bizarre Adventure (TV)" 12
```

Quotes required for spaces.

---

# CHANGING STATUS

## Anime statuses

- watching
- completed
- paused
- dropped
- plantowatch
- rewatching

---

## Manga statuses

- reading
- completed
- paused
- dropped
- plantoread

---

## Example

```bash
status 2 completed
```

or:

```bash
status 2 paused
```

Then:

```bash
send
```

---

# SCORING

## Give rating

```bash
score 2 90
```

Meaning:
- anime/manga ID = 2
- score = 90

Then:

```bash
send
```

---

# DELETING ENTRIES

## Remove anime/manga

```bash
delete 2
```

or:

```bash
delete "JoJo's Bizarre Adventure (TV)"
```

Then:

```bash
send
```

---

# QUEUE COMMANDS

# Show pending changes

```bash
viewqueue
```

Example:

```text
UPDATE: JoJo -> episode 12
STATUS: Frieren -> completed
```

---

# Sync queued changes online

```bash
send
```

---

# Cancel unsent changes

```bash
clearqueue
```

---

# FILTERING LISTS

## Show only completed anime

```bash
filter completed
```

Examples:

```bash
filter watching
filter paused
filter dropped
```

See current filter:

```bash
filter
```

---

# SWITCHING ANIME / MANGA

## Manga mode

```bash
mediatype manga
```

Prompt changes:

```text
Faizal [anilist] (manga) >>
```

---

## Back to anime

```bash
mediatype anime
```

---

# INFORMATION COMMANDS

## Detailed anime info

```bash
info 2
```

Shows:
- synopsis
- episodes
- progress
- status
- score
- airing info

---

# PLAYING LOCAL FILES

## Play next unwatched episode

```bash
play 2
```

---

## Play specific episode

```bash
play 2 12
```

---

# RESCAN LOCAL LIBRARY

## Scan anime folder again

```bash
rescan ~/Anime
```

Useful after adding new episodes.

---

# RANDOM COMMAND

## Random unwatched episode

```bash
random
```

---

# SORTING

## Sort list

```bash
sort title
sort score
sort progress
sort status
```

---

# ALTNAME

Useful when local folder names differ.

## Add alternative name

```bash
altname 2 JoJo
```

---

## Remove altname

```bash
altname 2
```

---

# RETRIEVE

## Re-download online list

```bash
retrieve
```

Useful if sync becomes weird.

---

# FASTEST DAILY WORKFLOW

## Watching anime

```bash
list
update 2
send
```

---

## Finished anime

```bash
update 2 24
status 2 completed
score 2 95
send
```

---

## Reading manga

```bash
mediatype manga
update 3 120
send
```

---

# COMMON ERRORS

# "Show not found"

Cause:
- wrong title

Fix:

```bash
search jojo
```

Then use:
- exact title
- or numeric ID

---

# "Show already in the list"

Cause:
- anime already exists

Use:

```bash
update
```

instead.

---

# "Changes not syncing"

Cause:
- forgot:

```bash
send
```

---

# MINIMAL CHEAT SHEET

```bash
list
search jojo
add frieren
update 2
update 2 12
status 2 completed
score 2 90
delete 2
viewqueue
send
mediatype manga
filter watching
info 2
play 2
```

---

# RECOMMENDED HABITS

## Habit 1

Use numeric IDs instead of titles.

---

## Habit 2

Always run:

```bash
send
```

after changes.

---

## Habit 3

Use:

```bash
viewqueue
```

before syncing many changes.

