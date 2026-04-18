# 🧰 Git Cheatsheet

> Complete reference for every Git command you'll ever need — from basics to advanced history inspection.

---

## 📦 Setup & Config

```bash
git config --global user.name "Your Name"        # Set your name
git config --global user.email "you@email.com"   # Set your email
git config --global core.editor "code --wait"    # Set VS Code as editor
git config --global init.defaultBranch main       # Set default branch name

git config --list                                 # View all config values
git config user.name                             # View a specific value
```

---

## 🏗️ Creating & Cloning

```bash
git init                          # Initialize a new repo in current folder
git init my-project               # Create a new folder and init inside it
git clone <url>                   # Clone a remote repo
git clone <url> my-folder         # Clone into a specific folder name
git clone --depth 1 <url>         # Shallow clone (latest snapshot only, no history)
```

---

## 📋 Checking Status & Differences

```bash
git status                        # Show changed, staged, and untracked files
git status -s                     # Short/compact output

git diff                          # Show unstaged changes (line by line)
git diff --staged                 # Show staged changes (what will be committed)
git diff HEAD                     # Show all changes since last commit
git diff <branch1> <branch2>      # Compare two branches
git diff <commit1> <commit2>      # Compare two commits
git diff <commit1> <commit2> -- <file>  # Compare a specific file between commits
```

---

## 📸 Staging & Committing

```bash
git add <file>                    # Stage a specific file
git add .                         # Stage all changes in current directory
git add -p                        # Stage changes interactively (chunk by chunk)
git add -u                        # Stage only tracked (modified/deleted) files

git commit -m "message"           # Commit with inline message
git commit                        # Open editor to write commit message
git commit -am "message"          # Stage all tracked files + commit in one step
git commit --amend                # Edit the last commit (message or content)
git commit --amend --no-edit      # Amend last commit, keep same message
```

> [!warning] Amend
> Never `--amend` a commit that has already been pushed to a shared branch. It rewrites history.

---

## 🕰️ Viewing History (Logs)

### Basic Log

```bash
git log                           # Full log with author, date, message
git log --oneline                 # One line per commit
git log --oneline --graph         # ASCII branch graph
git log --oneline --graph --all   # Graph for ALL branches
git log --stat                    # Show which files changed per commit
git log -p                        # Show full diff for each commit (patch)
git log -n 5                      # Show last 5 commits only
git log --since="2 weeks ago"     # Commits from last 2 weeks
git log --after="2024-01-01"      # Commits after a date
git log --before="2024-06-01"     # Commits before a date
git log --author="Riya"           # Commits by a specific author
git log --grep="login"            # Commits with "login" in the message
```

### Filtering Log by File or Content

```bash
git log -- <file>                       # All commits that touched a file
git log -S "someFunction"               # Commits that added/removed a string (pickaxe)
git log -G "regex.*pattern"             # Commits matching a regex in the diff
git log --follow -- <file>              # Log including file renames
git log --diff-filter=D -- <file>       # Only commits that deleted the file
```

### Pretty Formats

```bash
git log --pretty=format:"%h %an %s"           # hash, author, subject
git log --pretty=format:"%h %ad %s" --date=short  # with short date
git log --decorate                             # Show branch/tag labels
git log --decorate --oneline --all             # Compact full-tree view
```

---

## 🔍 Inspecting Commits

```bash
git show                          # Show the last commit (diff + metadata)
git show <commit>                 # Show a specific commit
git show <commit>:<file>          # Show a file's contents at a specific commit
git show HEAD~2                   # Show 2 commits before HEAD
git show HEAD^                    # Show parent of HEAD (same as HEAD~1)

git blame <file>                  # Show who last changed each line
git blame -L 10,25 <file>         # Blame specific line range (10–25)
git blame -e <file>               # Show email instead of name

git cat-file -p <commit>          # Show raw commit object contents
git ls-tree HEAD                  # List files tracked at HEAD
git ls-tree HEAD -- src/          # List files in a folder at HEAD
```

---

## 🔭 Reflog — Your Safety Net

> The reflog records **every** place HEAD has pointed, even after resets, rebases, or deleted branches. It's your undo button for almost anything.

```bash
git reflog                        # Full history of HEAD movements
git reflog show HEAD              # Same as above
git reflog show <branch>          # Reflog for a specific branch
git reflog --date=iso             # Show with timestamps

# Recover a deleted branch or lost commit
git checkout -b recovered-branch <hash>   # Recreate branch at that commit
git reset --hard HEAD@{3}                 # Go back to 3 reflog entries ago
```

> [!tip] Reflog Expiry
> Reflog entries expire after 90 days by default. Act fast if recovering lost work.

---

## 🌿 Branching

```bash
git branch                        # List local branches
git branch -r                     # List remote branches
git branch -a                     # List all branches (local + remote)
git branch -v                     # List branches with last commit

git branch <name>                 # Create a branch (don't switch)
git checkout <name>               # Switch to a branch
git checkout -b <name>            # Create AND switch to a branch
git switch <name>                 # Modern way to switch branches
git switch -c <name>              # Modern way to create + switch

git branch -m <old> <new>         # Rename a branch
git branch -d <name>              # Delete a merged branch (safe)
git branch -D <name>              # Force delete (even if unmerged)

git checkout -b <name> <commit>   # Create branch from a specific commit
git checkout -b <name> origin/<name>  # Create local branch from remote
```

---

## 🔀 Merging

```bash
git merge <branch>                # Merge branch into current branch
git merge --no-ff <branch>        # Force a merge commit (no fast-forward)
git merge --squash <branch>       # Squash all branch commits into one staged change
git merge --abort                 # Abort a merge in progress

# After resolving a conflict:
git add <file>                    # Mark conflict as resolved
git commit                        # Complete the merge
```

---

## ♻️ Rebasing

> Rebase replays your commits on top of another branch. Keeps history linear.

```bash
git rebase <branch>               # Rebase current branch onto another
git rebase -i HEAD~3              # Interactive rebase for last 3 commits
git rebase --onto <newbase> <oldbase> <branch>  # Advanced: move commits
git rebase --abort                # Abort in-progress rebase
git rebase --continue             # Continue after resolving a conflict
git rebase --skip                 # Skip a conflicting commit

# Interactive rebase actions (in the editor):
# pick   = keep commit as-is
# reword = keep commit, change message
# edit   = pause and amend the commit
# squash = merge into previous commit (keeps messages)
# fixup  = merge into previous commit (discards message)
# drop   = delete the commit entirely
```

> [!danger] Rebase Warning
> Never rebase commits that have been pushed to a shared/public branch. It rewrites history and breaks teammates' repos.

---

## ⏪ Undoing Things

```bash
# Safe undo — creates a new reverting commit (safe for shared branches)
git revert <commit>               # Revert a specific commit
git revert HEAD                   # Revert the last commit
git revert HEAD~3..HEAD           # Revert a range of commits

# Reset — moves the branch pointer (use with care)
git reset --soft HEAD~1           # Undo last commit, keep changes STAGED
git reset --mixed HEAD~1          # Undo last commit, keep changes UNSTAGED (default)
git reset --hard HEAD~1           # ⚠️ Undo last commit, DELETE all changes

git reset HEAD <file>             # Unstage a file (keep changes in working dir)
git restore --staged <file>       # Modern equivalent of above

# Discard working directory changes
git restore <file>                # Discard unstaged changes in a file
git checkout -- <file>            # Old syntax for same thing
git clean -fd                     # ⚠️ Delete all untracked files + folders
git clean -n                      # Dry run — show what would be deleted
```

| Command | Staged? | Working Dir? | History? |
|---|---|---|---|
| `reset --soft` | ✅ Keeps staged | ✅ Keeps | ❌ Removes commit |
| `reset --mixed` | ❌ Unstages | ✅ Keeps | ❌ Removes commit |
| `reset --hard` | ❌ Gone | ❌ Gone | ❌ Removes commit |
| `revert` | — | ✅ Keeps | ✅ Adds new commit |

---

## 📚 Stashing

> Temporarily shelve changes so you can switch tasks without committing.

```bash
git stash                         # Stash current changes (tracked files only)
git stash -u                      # Stash including untracked files
git stash save "work in progress" # Stash with a description

git stash list                    # View all stashes
git stash show                    # See what's in the latest stash
git stash show -p                 # See full diff of latest stash
git stash show stash@{2}          # Show a specific stash

git stash pop                     # Apply latest stash and remove it
git stash apply                   # Apply latest stash but keep it in list
git stash apply stash@{2}         # Apply a specific stash

git stash drop stash@{1}          # Delete a specific stash
git stash clear                   # ⚠️ Delete ALL stashes

git stash branch <name>           # Create branch from stash (useful for conflicts)
```

---

## ☁️ Remote Repositories

```bash
git remote -v                     # List remotes with URLs
git remote add origin <url>       # Add a remote named "origin"
git remote rename origin upstream # Rename a remote
git remote remove <name>          # Remove a remote
git remote set-url origin <url>   # Change remote URL

git fetch origin                  # Download changes, don't merge
git fetch --all                   # Fetch from all remotes
git pull origin main              # Fetch + merge
git pull --rebase origin main     # Fetch + rebase (cleaner history)

git push origin main              # Push local commits to remote
git push -u origin <branch>       # Push new branch and set upstream
git push --force-with-lease       # Safer force push (checks for new commits)
git push --force                  # ⚠️ Force push — overwrites remote history
git push origin --delete <branch> # Delete a remote branch
git push origin :<branch>         # Alternative syntax to delete remote branch
```

---

## 🏷️ Tags

```bash
git tag                           # List all tags
git tag v1.0.0                    # Create lightweight tag at HEAD
git tag -a v1.0.0 -m "Release"    # Create annotated tag with message
git tag -a v1.0.0 <commit>        # Tag a specific commit

git show v1.0.0                   # Show tag details
git push origin v1.0.0            # Push a single tag
git push origin --tags            # Push all tags

git tag -d v1.0.0                 # Delete local tag
git push origin --delete v1.0.0   # Delete remote tag

git checkout v1.0.0               # Switch to a tag (detached HEAD)
```

---

## 🍒 Cherry-Pick

> Apply a specific commit from another branch onto the current branch.

```bash
git cherry-pick <commit>          # Apply a single commit
git cherry-pick <c1> <c2>         # Apply multiple specific commits
git cherry-pick <c1>..<c2>        # Apply a range (exclusive of c1)
git cherry-pick <c1>^..<c2>       # Apply a range (inclusive of c1)
git cherry-pick --no-commit <c>   # Apply changes but don't auto-commit
git cherry-pick --abort           # Abort cherry-pick in progress
git cherry-pick --continue        # Continue after resolving conflicts
```

---

## 🔎 Searching

```bash
git grep "TODO"                   # Search current files for a string
git grep -n "TODO"                # With line numbers
git grep "TODO" <commit>          # Search in a specific commit's snapshot

git log -S "functionName"         # Find commits that added/removed a string
git log -G "regex"                # Find commits matching a regex in diffs

git bisect start                  # Start binary search for a bad commit
git bisect bad                    # Mark current commit as bad
git bisect good <commit>          # Mark a known good commit
git bisect reset                  # End bisect session
```

---

## 🗂️ Submodules

```bash
git submodule add <url> <path>    # Add a submodule
git submodule init                # Initialize submodules after clone
git submodule update              # Pull submodule content
git submodule update --init --recursive  # Full recursive init + update
git submodule status              # Show status of all submodules
```

---

## 🔧 Plumbing / Advanced

```bash
git rev-parse HEAD                # Get full hash of HEAD
git rev-parse --short HEAD        # Get short hash
git rev-parse <branch>            # Get hash of a branch tip

git ls-files                      # List tracked files
git ls-files --others --exclude-standard  # List untracked files

git count-objects -vH             # Repo size and object count

git gc                            # Run garbage collection (cleanup)
git fsck                          # Check repo integrity
git prune                         # Remove unreachable objects

git archive --format=zip HEAD > snapshot.zip  # Export repo as ZIP
git shortlog -sn                  # Commit count by author
```

---

## 🧹 Cleaning Up

```bash
git clean -n                      # Dry run: show what would be deleted
git clean -f                      # Delete untracked files
git clean -fd                     # Delete untracked files and folders
git clean -fdx                    # Delete untracked + gitignored files

git branch --merged               # List branches merged into current
git branch --no-merged            # List branches NOT yet merged
git branch --merged | grep -v main | xargs git branch -d  # Delete all merged branches
```

---

## 🙈 .gitignore

```bash
# In .gitignore file:
node_modules/       # Ignore a folder
*.log               # Ignore all .log files
.env                # Ignore specific file
!important.log      # Exception — do NOT ignore this

git check-ignore -v <file>        # Find out WHY a file is ignored
git rm --cached <file>            # Stop tracking a file (keep it locally)
git rm --cached -r node_modules/  # Stop tracking a folder
```

---

## 💡 Useful Aliases (add to ~/.gitconfig)

```ini
[alias]
  s     = status -s
  l     = log --oneline --graph --decorate --all
  la    = log --oneline --graph --all
  d     = diff
  ds    = diff --staged
  undo  = reset --soft HEAD~1
  rh    = reset --hard HEAD
  wip   = commit -am "wip"
  oops  = commit --amend --no-edit
  gone  = branch -vv | grep ': gone' | awk '{print $1}' | xargs git branch -d
```

---

## 🚦 Quick Reference: HEAD Notation

| Notation | Meaning |
|---|---|
| `HEAD` | Current commit |
| `HEAD~1` or `HEAD^` | 1 commit before HEAD |
| `HEAD~3` | 3 commits before HEAD |
| `HEAD@{1}` | Previous position of HEAD (reflog) |
| `branch@{yesterday}` | Branch as it was yesterday |
| `origin/main` | Remote tracking branch |

---

## 🔄 Common Workflows

### Start a new feature
```bash
git checkout main && git pull origin main
git checkout -b feature/my-feature
# ... code, commit ...
git push -u origin feature/my-feature
# Open Pull Request on GitHub
```

### Sync your branch with main
```bash
git checkout main && git pull origin main
git checkout feature/my-feature
git rebase main          # or: git merge main
```

### Oops, I committed to main directly
```bash
git branch feature/save-my-work   # Save the commit to a new branch
git reset --hard origin/main      # Reset main to match remote
git checkout feature/save-my-work # Continue on the right branch
```

### Recover a deleted branch
```bash
git reflog                        # Find the last commit hash of that branch
git checkout -b recovered <hash>  # Recreate the branch
```

### Undo a pushed commit (safely)
```bash
git revert <commit>               # Creates an undo commit
git push                          # Push the revert
```

---

Tags: #git #cheatsheet #devtools #reference