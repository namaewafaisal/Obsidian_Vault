---
folders:
  - Problems
  - Not Fixed
---
```dataviewjs

const folders = dv.current().folders;

// collect all notes in both folders
let notes = [];
for (let f of folders) {
    notes = notes.concat(dv.pages(`"${f}"`).array());
}

// map by filename
let noteMap = new Map();
for (let n of notes) {
    noteMap.set(n.file.name, n);
}

// extract planned problems from links
let planned = dv.current().file.outlinks.map(l => l.path.split("/").pop().replace(".md",""));

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
dv.list(todo.map(p=>dv.fileLink(p.file.path)));

dv.header(2,"Needs Review");
dv.list(review.map(p=>dv.fileLink(p.file.path)));

dv.header(2,"Needs Refactor (Missing YAML)");
dv.list(refactor.map(p=>dv.fileLink(p.file.path)));

dv.header(2,"Done");
dv.list(done.map(p=>dv.fileLink(p.file.path)));

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