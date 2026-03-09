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

        // Build topic → [names]
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
                topicMap.get(assignedTopic).push(clean(l.link));
            }
        }

        function getStatus(name) {
            if (!noteMap.has(name)) return "missing";
            let page = noteMap.get(name);
            if (!page.status) return "refactor";
            return page.status;
        }

        // Build status → topic → [entries]
        // entry: { name, page or null }
        // Status priority order (most important first)
        const statusOrder = ["missing", "needs-review", "refactor", "done"];

        const statusMeta = {
            "missing":      { label: "❌ Not Created (Planned)",         icon: "❌" },
            "needs-review": { label: "🔁 Needs Review",                  icon: "🔁" },
            "refactor":     { label: "⚠️ Needs Refactor (Missing Status)", icon: "⚠️" },
            "done":         { label: "✅ Done",                           icon: "✅" },
        };

        // Collect into status → topic → entries
        let statusTopicMap = new Map();
        for (let s of statusOrder) {
            statusTopicMap.set(s, new Map());
            for (let t of orderedTopics) {
                statusTopicMap.get(s).set(t, []);
            }
        }

        // Also capture unknown statuses (todo, in-progress, etc.)
        let unknownStatuses = new Set();

        for (let topic of orderedTopics) {
            for (let name of topicMap.get(topic)) {
                let s = getStatus(name);
                if (!statusTopicMap.has(s)) {
                    unknownStatuses.add(s);
                    statusTopicMap.set(s, new Map());
                    for (let t of orderedTopics) {
                        statusTopicMap.get(s).set(t, []);
                    }
                }
                statusTopicMap.get(s).get(topic).push(name);
            }
        }

        const allStatuses = [...statusOrder, ...unknownStatuses];

        // Count totals per status for summary
        let counts = {};
        for (let s of allStatuses) counts[s] = 0;

        // Render: status first, topics inside
        for (let s of allStatuses) {
            let topicEntries = statusTopicMap.get(s);
            if (!topicEntries) continue;

            // Check if any entries exist under this status
            let total = [...topicEntries.values()].reduce((acc, arr) => acc + arr.length, 0);
            if (total === 0) continue;

            counts[s] = total;

            let meta = statusMeta[s] ?? { label: `🔵 ${s}`, icon: "🔵" };
            dv.header(2, `${meta.label} — ${total}`);

            for (let topic of orderedTopics) {
                let names = topicEntries.get(topic);
                if (!names || names.length === 0) continue;

                dv.header(4, topic);

                let rows = names.map(name => {
                    if (s === "missing") {
                        return [name];
                    }
                    let page = noteMap.get(name);
                    return [dv.fileLink(page.file.path, false, name)];
                });

                dv.table(["Problem"], rows);
            }
        }

        // Summary
        dv.header(2, "Summary");
        dv.table(
            ["❌ Planned", "🔁 Review", "⚠️ Refactor", "✅ Done"],
            [[
                counts["missing"]      ?? 0,
                counts["needs-review"] ?? 0,
                counts["refactor"]     ?? 0,
                counts["done"]         ?? 0,
            ]]
        );
    }
}
```