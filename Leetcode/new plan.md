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
