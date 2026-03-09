---
plan_file: plan.md
---
```dataviewjs
const planPath = dv.current().plan_file;
const planFile = dv.page(planPath);

if (!planFile) {
    dv.paragraph(`⚠️ Could not find plan file at: "${planPath}"`);
} else if (!planFile.folders || planFile.folders.length === 0) {
    dv.paragraph("⚠️ No folders defined in plan file YAML.");
} else {
    const folders = planFile.folders;

    let notes = [];
    for (let f of folders) {
        notes = notes.concat(dv.pages(`"${f}"`).array());
    }

    function clean(raw) {
        return raw.replace(/\.md$/, "").split("/").pop().trim();
    }

    let noteMap = new Map();
    for (let n of notes) {
        noteMap.set(clean(n.file.name), n);
    }

    const cache = app.metadataCache.getCache(planFile.file.path);

    if (!cache) {
        dv.paragraph("⚠️ Could not read metadata cache for plan file.");
    } else {
        const headings = cache.headings ?? [];
        const links    = cache.links    ?? [];

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
            if (assignedTopic) {
                topicMap.get(assignedTopic).push(clean(l.original ?? l.link));
            }
        }

        function getStatus(name) {
            if (!noteMap.has(name)) return "missing";
            let page = noteMap.get(name);
            if (!page.status) return "refactor";
            return page.status;
        }

        function statusIcon(status) {
            switch(status) {
                case "done":         return "✅";
                case "needs-review": return "🔁";
                case "missing":      return "❌";
                case "refactor":     return "⚠️";
                default:             return "🔵";
            }
        }

        for (let topic of orderedTopics) {
            let names = topicMap.get(topic);
            if (names.length === 0) continue;

            dv.header(2, topic);

            let rows = names.map(name => {
                let status = getStatus(name);
                let icon = statusIcon(status);
                if (status === "missing") {
                    return [icon, name, status];
                }
                let page = noteMap.get(name);
                return [icon, dv.fileLink(page.file.path, false, name), status];
            });

            dv.table(["", "Problem", "Status"], rows);
        }

        let allNames = orderedTopics.flatMap(t => topicMap.get(t));
        let counts = { done: 0, "needs-review": 0, refactor: 0, missing: 0, todo: 0 };
        for (let name of allNames) {
            let s = getStatus(name);
            if (s in counts) counts[s]++;
            else counts.todo++;
        }

        dv.header(2, "Summary");
        dv.table(
            ["✅ Done", "🔁 Review", "🔵 Todo", "⚠️ Refactor", "❌ Missing"],
            [[counts.done, counts["needs-review"], counts.todo, counts.refactor, counts.missing]]
        );
    }
}
```
