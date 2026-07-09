Alright. Here's a real decision, not another menu of options.

## The one thing to build your next 50-80 days around: MIT 6.1810 (xv6 labs)

I checked — this is still active and fully self-paced with public labs, source, and automated tests you can run yourself, no enrollment needed: **pdos.csail.mit.edu/6.1810/2024/** (use 2024, it's the most complete stable version with all labs finalized).

Here's why this is the actual answer to what you asked for, point by point:

**"I want transferable knowledge, not topic-checking."** Each lab hands you a real, working Unix-like kernel (xv6) and makes you _extend_ it — implement copy-on-write fork, build a crash-safe file system, write a lock-free scheduler, handle page faults, write a network driver. You don't learn "what is virtual memory" as a slide. You learn it because your code breaks until you understand it. This is exactly the fundamentals-in-context loop you described wanting for networking/DBMS — it's the same principle applied to OS.

**"Doable on my Dell Latitude 7390."** Everything runs inside QEMU (an emulator) on your existing Linux machine. xv6 runs on RISC-V architecture specifically — which means as a side effect, you'll pick up real ISA-level thinking (calling conventions, registers, assembly, how a non-x86 architecture actually works) without needing ARM hardware you don't own. That's the closest legitimate bridge to your ARM curiosity available to you right now.

**"Help in interviews."** OS internals — virtual memory, scheduling, concurrency, file systems, syscalls — are asked at literally every serious backend/systems interview in India and abroad. Right now you have theory-only answers. After this, you have "I implemented this" answers, which is a different tier of credibility entirely.

**"Train me to think like a senior engineer."** Look at what one person who did these labs wrote: the xv6 kernel is a compact teaching operating system with an accompanying book explaining concepts and code structure in detail, and the labs range from adding simple system calls to reducing lock contention in file system and driver code, with a build system that boots the kernel in QEMU and supports GDB debugging. That person also noted the labs progress well in difficulty and gave a real sense of deeply understanding implementation challenges, completed over about three months at 1-2 sessions a week. That's your realistic pace — a few focused sessions weekly, not a sprint. Each lab is a self-contained "24-hour game" problem: a spec, ambiguity, and a working test suite that tells you if your mental model was right.

## The actual sequence

**Phase 0 (~1 week): C sharpening.** You know basics — get comfortable with pointers, structs, manual memory, and gdb specifically, since that's what the labs assume. K&R chapters 5-6 plus a short pointers/structs refresher is enough. Don't overdo this phase — the labs will teach you the rest by force.

**Phase 1 (bulk, ~6-9 weeks depending on pace): the labs themselves, in order** — Utilities → System calls → Page tables → Traps → Copy-on-write → Lock → File system → mmap → (network driver as a stretch lab). Each one: read the relevant xv6 book chapter first, then implement, then write a short note in Obsidian explaining _why_ it works, not just that it passed tests. Those notes become your interview stories.

**Phase 2 (remaining time): pivot point, decided by evidence not guessing.** By the time you've done 5-6 labs, you'll _know_ — viscerally, not theoretically — whether kernel-space work energizes you or whether you'd rather take this OS understanding and apply it to userspace Linux systems (containers, namespaces, cgroups — directly usable on your actual Arch machine, no emulator, and a very real infra-engineering skill). Either branch is legitimate. You pick with real data instead of vibes.

## What runs alongside, not instead

**DSA doesn't disappear** — but it stops being the main event. 30-40 minutes a day, pattern-based, just enough to stay interview-ready generally. This isn't Juspay-grind DSA anymore; it's maintenance.

## One honest tradeoff, said plainly

This is a bet on depth over breadth. You will not "cover" networks/DBMS broadly in this window — you'll get OS to real depth and everything else stays where it is. That's the right trade for someone trying to become a systems engineer, not a generalist. Depth in one hard thing, done for real, is worth more in an interview and in your own sense of competence than breadth in four things done shallowly — which is the trap you're in right now.

Your first concrete step, this week: clone the xv6 repo, get it booting in QEMU on your machine, do Lab 1 (Utilities — it's short, mostly userspace programs). That's it. Not the whole plan — just proof to yourself that this is real and doable, today.



---

Here's what I'd actually want you to do, in order — not a wishlist, an actual sequence you start today.

**1. Right now, before anything else: set up the environment.**
On your Arch machine:
```
sudo pacman -S qemu-full riscv64-elf-gcc riscv64-elf-binutils gdb
git clone https://github.com/mit-pdos/xv6-riscv.git
cd xv6-riscv
make qemu
```
If it boots to an xv6 shell prompt, you're done with setup. This takes 20-30 minutes including hiccups. Don't overthink it, don't research alternatives, don't read the whole book first — just get it booting.

**2. Today or tomorrow: read the xv6 book, chapters 1-2 only.**
Not the whole book. Just enough to understand what xv6 *is* and how the code is organized. This is maybe 1-1.5 hours of reading. Stop there.

**3. This week: do Lab 1 (Utilities).**
Go to pdos.csail.mit.edu/6.1810/2024/labs/util.html. It's the easiest lab — mostly userspace C programs, not deep kernel surgery — designed as an on-ramp. Your goal isn't mastery here, it's momentum: finish one lab, pass its tests, prove to yourself this is real and doable.

**4. Open an Obsidian note today called something like `xv6 - log`.**
After each lab, write 3-5 sentences: what you built, what broke, why it broke, what you learned. This is what becomes your interview story later, and it's also what turns "I did a course" into actual R&D practice — the reflection is the part most people skip.

**5. Don't plan past Lab 1 right now.**
I know that's the opposite of what this whole conversation has been about, but here's why: you've spent this entire conversation in planning mode, which feels productive but is actually another form of avoidance at this point. You have enough plan. The thing you're missing isn't more clarity — it's the first `make qemu` actually running on your screen.

Do steps 1-3 this week. Come back and tell me what broke, what confused you, or what part of the code didn't make sense — that's where I'll actually be useful to you, debugging real confusion instead of hypothetical planning.