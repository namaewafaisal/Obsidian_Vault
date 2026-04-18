---
plan_file: "Leetcode/plan"
---

```dataviewjs
console.clear();
console.log("====== FULL DEBUG ======");

// --- 1. PLAN FILE ---
const planPath = dv.current().plan_file;
console.log("1. plan_file from YAML:", planPath);

const planFile = dv.page(planPath);
console.log("2. planFile resolved:", planFile ? planFile.file.path : "NOT FOUND ❌");

if (!planFile) {
    dv.paragraph(`❌ plan_file not found: "${planPath}"`);
} else {

    // --- 2. FOLDERS ---
    const folders = planFile.folders;
    console.log("3. folders from plan YAML:", JSON.stringify(folders));

    if (!folders || folders.length === 0) {
        dv.paragraph("❌ No folders found in plan file YAML");
    } else {

        // --- 3. PAGES PER FOLDER ---
        let notes = [];
        for (let f of folders) {
            let pages = dv.pages(`"${f}"`);
            console.log(`4. folder "${f}" → ${pages.length} pages`);
            pages.forEach(p => console.log("    →", p.file.path, "| status:", p.status ?? "MISSING"));
            notes = notes.concat(pages.array());
        }
        console.log("5. Total notes collected:", notes.length);

        // --- 4. NOTE MAP ---
        function clean(raw) {
            return raw.replace(/\.md$/, "").split("/").pop().trim();
        }

        let noteMap = new Map();
        for (let n of notes) {
            noteMap.set(clean(n.file.name), n);
        }
        console.log("6. noteMap size:", noteMap.size);

        // --- 5. METADATA CACHE ---
        const cache = app.metadataCache.getCache(planFile.file.path);
        console.log("7. cache found:", !!cache);

        if (!cache) {
            dv.paragraph("❌ metadata cache not found for plan file");
        } else {

            const headings = cache.headings ?? [];
            const links    = cache.links    ?? [];
            console.log("8. headings count:", headings.length);
            headings.forEach(h => console.log(`    heading: "${h.heading}" @ line ${h.position.start.line}`));
            console.log("9. links count:", links.length);
            links.forEach(l => console.log(`    link: "${l.link}" | original: "${l.original}" @ line ${l.position.start.line}`));

            // --- 6. TOPIC ASSIGNMENT ---
            let sortedHeadings = [...headings].sort((a,b) => a.position.start.line - b.position.start.line);
            let sortedLinks    = [...links].sort((a,b) => a.position.start.line - b.position.start.line);

            let topicMap = new Map();
            let orderedTopics = [];
            for (let h of sortedHeadings) {
                topicMap.set(h.heading, []);
                orderedTopics.push(h.heading);
            }

            for (let l of sortedLinks) {
                let assignedTopic = null;
                for (let h of sortedHeadings) {
                    if (h.position.start.line < l.position.start.line) {
                        assignedTopic = h.heading;
                    } else {
                        break;
                    }
                }
                let name = clean(l.original ?? l.link);
                console.log(`10. link "${name}" → topic: ${assignedTopic ?? "UNASSIGNED ❌"}`);
                if (assignedTopic) topicMap.get(assignedTopic).push(name);
            }

            // --- 7. STATUS LOOKUP PER PROBLEM ---
            console.log("11. === STATUS LOOKUP ===");
            for (let topic of orderedTopics) {
                for (let name of topicMap.get(topic)) {
                    let found = noteMap.has(name);
                    let status = found ? (noteMap.get(name).status ?? "MISSING STATUS") : "NOT IN NOTEMAP";
                    console.log(`    [${topic}] "${name}" → found: ${found} | status: ${status}`);
                }
            }

            // --- RENDER SUMMARY TO NOTE ---
            dv.header(2, "Debug Summary");
            dv.table(["Check", "Result"], [
                ["plan_file path",         planPath],
                ["plan_file resolved",     planFile.file.path],
                ["folders",                JSON.stringify(folders)],
                ["total notes in folders", notes.length],
                ["noteMap size",           noteMap.size],
                ["headings found",         headings.length],
                ["links found",            links.length],
                ["topics",                 orderedTopics.join(", ") || "NONE ❌"],
            ]);

            dv.header(2, "Per-Topic Link → Status");
            for (let topic of orderedTopics) {
                let names = topicMap.get(topic);
                dv.header(3, topic + ` (${names.length})`);
                dv.table(["Problem", "In noteMap", "Status"], names.map(name => {
                    let found = noteMap.has(name);
                    let status = found ? (noteMap.get(name).status ?? "⚠️ no status field") : "❌ not found";
                    return [name, found ? "✅" : "❌", status];
                }));
            }
        }
    }
}
```
