---
folders:
  - Leetcode/Problems
  - Leetcode/Not Fixed
---
```dataviewjs

const folders = dv.current().folders;

// collect notes
let notes = [];
for (let f of folders) {
    notes = notes.concat(dv.pages(`"${f}"`).array());
}

// normalize name helper
function clean(name){
    return name.replace(".md","").trim();
}

// map existing files
let noteMap = new Map();
for (let n of notes) {
    noteMap.set(clean(n.file.name), n);
}

// extract planned links
let planned = dv.current().file.outlinks.map(l => clean(l.path.split("/").pop()));


// containers
let done = [];
let review = [];
let todo = [];
let refactor = [];
let missing = [];


for (let name of planned) {

    if (!noteMap.has(name)) {
        missing.push(name);
        continue;
    }

    let page = noteMap.get(name);

    if (!page.status) {
        refactor.push(page);
        continue;
    }

    if (page.status === "done") {
        done.push(page);
    }
    else if (page.status === "needs-review") {
        review.push(page);
    }
    else {
        todo.push(page);
    }

}


// OUTPUT

dv.header(2,"Remaining (Not Done)");
dv.list(todo.map(p => dv.fileLink(p.file.path)));

dv.header(2,"Needs Review");
dv.list(review.map(p => dv.fileLink(p.file.path)));

dv.header(2,"Needs Refactor (Missing YAML)");
dv.list(refactor.map(p => dv.fileLink(p.file.path)));

dv.header(2,"Done");
dv.list(done.map(p => dv.fileLink(p.file.path)));

dv.header(2,"Not Yet Created");
dv.list(missing);

```

# Arrays

- [[26. Remove Duplicates from Sorted Array]]
- [[27. Remove Element]]
- [[283. Move Zeroes]]
- [[977. Squares of a Sorted Array]]
- [[88. Merge Sorted Array]]
- [[189. Rotate Array]]
- [[169. Majority Element]]

# Hashing

- [[217. Contains Duplicate]]
- [[219. Contains Duplicate II]]
- [[242. Valid Anagram]]

# Sliding Window

- [[3. Longest Substring Without Repeating Characters]]
- [[424. Longest Repeating Character Replacement]]
- [[438. Find All Anagrams in a String]]
- [[567. Permutation in String]]

```dataviewjs
const folders = dv.current().folders;

// Collect all notes from specified folders
let notes = [];
for (let f of folders) {
    notes = notes.concat(dv.pages(`"${f}"`).array());
}

// Normalize helper - strips path and .md extension
function clean(raw) {
    return raw.replace(/\.md$/, "").split("/").pop().trim();
}

// Build map: cleaned filename → page object
let noteMap = new Map();
for (let n of notes) {
    noteMap.set(clean(n.file.name), n);
}

// Extract all outlinks from current file, clean them
let planned = dv.current().file.outlinks.values.map(l => clean(l.path));

// Deduplicate
planned = [...new Set(planned)];

// Buckets
let done = [];
let review = [];
let todo = [];
let refactor = [];
let missing = [];

for (let name of planned) {
    // Skip self-reference
    if (name === clean(dv.current().file.name)) continue;

    if (!noteMap.has(name)) {
        missing.push(name);
        continue;
    }

    let page = noteMap.get(name);

    if (!page.status) {
        refactor.push(page);
        continue;
    }

    switch (page.status) {
        case "done":
            done.push(page); break;
        case "needs-review":
            review.push(page); break;
        default:
            todo.push(page);
    }
}

// Render
dv.header(2, `Remaining — ${todo.length}`);
dv.list(todo.length ? todo.map(p => dv.fileLink(p.file.path)) : ["_none_"]);

dv.header(2, `Needs Review — ${review.length}`);
dv.list(review.length ? review.map(p => dv.fileLink(p.file.path)) : ["_none_"]);

dv.header(2, `Needs Refactor (Missing status) — ${refactor.length}`);
dv.list(refactor.length ? refactor.map(p => dv.fileLink(p.file.path)) : ["_none_"]);

dv.header(2, `Done — ${done.length}`);
dv.list(done.length ? done.map(p => dv.fileLink(p.file.path)) : ["_none_"]);

dv.header(2, `Not Yet Created — ${missing.length}`);
dv.list(missing.length ? missing : ["_none_"]);
```

```dataviewjs
// DEBUG BLOCK - remove after fixing

console.log("=== OUTLINKS DEBUG ===");

let outlinks = dv.current().file.outlinks;
console.log("Type:", typeof outlinks, outlinks);
console.log("Has .values:", !!outlinks.values);

let raw = outlinks.values ?? outlinks.array?.() ?? [...outlinks];
console.log("Raw outlinks count:", raw.length);

raw.forEach((l, i) => {
    console.log(`[${i}]`, JSON.stringify(l));
});
```


```dataviewjs
// DEBUG BLOCK 2 - noteMap keys

const folders = dv.current().folders;
let notes = [];
for (let f of folders) {
    notes = notes.concat(dv.pages(`"${f}"`).array());
}

function clean(raw) {
    return raw.replace(/\.md$/, "").split("/").pop().trim();
}

let noteMap = new Map();
for (let n of notes) {
    let key = clean(n.file.name);
    noteMap.set(key, n);
    console.log("noteMap key:", JSON.stringify(key), "| status:", n.status ?? "MISSING");
}

// Now check each outlink against the map
let raw = dv.current().file.outlinks.values;
console.log("\n=== LOOKUP CHECK ===");
for (let l of raw) {
    let name = clean(l.path);
    let found = noteMap.has(name);
    console.log(`"${name}" → found: ${found}`);
}
```
