## Phase A — Arrays & Two Pointers

```dataview
LIST file.link
FROM "Problems"
WHERE topic = "array"
AND status != "done"
SORT file.name ASC
```
# List

```dataviewjs

const problems = [
"Problems/26. Remove Duplicates from Sorted Array",
"Problems/27. Remove Element",
"Problems/283. Move Zeroes",
"Problems/977. Squares of a Sorted Array",
"Problems/88. Merge Sorted Array",
"Problems/189. Rotate Array",
"Problems/169. Majority Element"
];

let remaining = [];

for (let p of problems) {
    let page = dv.page(p);
    if (page && page.status != "done") {
        remaining.push("- [ ] " + dv.fileLink(p));
    }
}

dv.paragraph(remaining.join("\n"));

```

```dataviewjs

// folders where problem notes live
const folders = [
  "Problems",
  "Not Fixed"
];

// expected problems (numbers or filenames without .md)
const expected = [
  "26. Remove Duplicates from Sorted Array",
  "27. Remove Element",
  "283. Move Zeroes",
  "977. Squares of a Sorted Array",
  "88. Merge Sorted Array",
  "189. Rotate Array",
  "169. Majority Element"
];

// collect all files from folders
let pages = [];
for (let f of folders) {
    pages = pages.concat(dv.pages(`"${f}"`).array());
}

// create quick lookup
let fileMap = new Map();
for (let p of pages) {
    fileMap.set(p.file.name.replace(".md",""), p);
}


// -------- Remaining (not done) --------

dv.header(2, "Remaining");

let remaining = pages
    .filter(p => p.status != "done")
    .map(p => dv.fileLink(p.file.path));

dv.list(remaining);


// -------- Needs Review --------

dv.header(2, "Needs Review");

let review = pages
    .filter(p => p.status == "needs-review")
    .map(p => dv.fileLink(p.file.path));

dv.list(review);


// -------- Done --------

dv.header(2, "Done");

let done = pages
    .filter(p => p.status == "done")
    .map(p => dv.fileLink(p.file.path));

dv.list(done);


// -------- Missing Files --------

dv.header(2, "Not Yet Created");

let missing = [];

for (let name of expected) {
    if (!fileMap.has(name)) {
        missing.push(name);
    }
}

dv.list(missing);

```
