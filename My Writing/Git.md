not many people understand git and just memorises the commands that they use most
Git is a database, every commit is a record or a row in the database. 
Commit : A Snapshot like the linux snapshot. it is like a photograph that stores the state of every file included in the git or in the git folder. 
Commit consists of Snapshot, metadata which says who committed, commit message when etc. and the pointer to the parent commit or the previous commit.
does changing head to a past commit make it forget the commits after that? 
**commit always point backwards**

![](DAG%201.jpg)

Its always directed meaning child commit points to its parent and not viceVersa
Acyclic meaning i cant be my own ancestor so the no cylces. cant point to both parent and grandparent.  but parents cant be in the same chain. in the above both branch 1 and branch 2 are parents or merge but they dont belong in a same path. they are parellel. if they were not then either of them must be an ancestor or merge and you cant point to ancestor but only to parent
No loops is what i meant 
Each commit is complete snapshot and can just be jumped to to be viewed. all code is just there without any problems. but how?
Dag is your entire project's history
Every branch , decision, merge is there.

## Branch
It is just a *Sticky note* that has only one info. ie a hashcode to a commit. meaning a hash of the commit like an address or the name of the commit. it does not have the code or files or anything
they are just like a pointer which point to a certain commit in the dag. just to locate and use it like head pointerin the linked list. 
![](Branch%201.jpg)


either main or other branch, any new commit just created and that points to the parent and the branch label or the pointer to the latest commit of that branch is moved forward
so creating a branch use git checkout or similar is instant because it just creates a label for that commit. just a dedicated name for it to work with.
main branch aint special but agreed to be the master or the best or the **Main** one.

So to the question that if i move a head to a previous commit then do i lost track of the latest is i dont. head is a poniter to pointer *mostly* 
head points to the branched which inturn points to the comiits. when we checkout other bracnh we just move head from the current branch to the checkout branch. the one we worked on is not lost and still stored with the branch name. 
**But when we move head to a Specific commit and not a  branch** essentially head changes from a Ptr to a ptr and becomes a ptr to the commit. when we work on the specific commit and commit the changes it creates an orphan commit because there is no branch pointing to it. so if you move to a branch like main to merge the change you did you the garbage collector will remove the orphan because we dont have its address but its in reflog for a while before its deleted. its like head equals head.next. the orphan points to the commit we worked on but the orphan itself is lost (address) so we the changes are also lost
Head pointing to a commit instead of a branch is called detatched head
i think a solution here could be to create a branch and labelling it to the orphan before moving the head to somewhere else.
![](Head%201.jpg)

so the 30 - 90 days holding feels wrong when realising nothing points to the orphan now. but here comes the reflog. it is a log that stores every movement of head. so if head moved from the orphan to the main then that log stores the orphan commit, and main. so orphan is not lost until log is updated more and old logs are flushed out.

i was right about creating a branch to hold the orphan to not lose it or you want to work from then on

## Code living
Code can live in 3 areas in git.
Working dir : the actual files.
`git add .`
Staging area : its now in a waiting to be stored in next commit
`git commit -m "Message"`
Object database : where commits, trees, and blobs are stored
Repository : “Repository” includes refs, reflog, config, etc.

![](3%20phases%201.jpg)

**Checkout** Only head moves. basically head can point any commit or a branch
The directory now shows the files from that commit. nothing from the repository change or the branch change. just head points somewhere else -> Safe

## Reset
move the branch itself. meaning the branch points to a different commit.
three modes. 
`--soft` : Anychanges and changes in the commits after the new main is still there in the current main. like the work or the staging of the files havent changed.
its like nothing changed because the code is exactly as the same before and after resetting the main, but the code is staged and not pushed yet. like you can have many commits after that to get to those code but every change is now here to stage and push which creates a single commit with all of that. Index is basically the structure of the snapshot. it mirrors the state how the code will look with those staged changes.
soft does not change the commit history, remove commits from the objects of database, lose the code but does stage the code , history of the movement of the head.

`--mixed` : The same as the soft but the code is unstaged. nothing else is different.
so if we commit the unstaged index it looks like same as the current main.
We can also manually unstage the files from soft so `soft + manual unstage ~ mixed`
why the mixed exist? : although both can be done from soft we can also make them a separete function because they are used commonly. 

`--hard` : basically resets the working dir, index etc. its as though like the changes were never done unless we look in the reflog to get those commit changes. its used to start from scratch from the current main.


## Revert
Revert  does not move the branch to the past commit but create a new commit that undo the changes did in the current commit. its like not orphaning the unwanted changes but have it in the dag while still create a new commit that does now have the changes from the unwanted commit
Revert creates a new commit that applies the inverse of an earlier commit, undoing its effect without changing existing history.

## Rebase

a feature branch may have branched out from main. main moved 2 commits. feature moved 2 commits. we can either merge which create a merge commit with 2 parents which are the previous main and feature branch. Rebase does not merge them to create a parellel dag but puts the changes on top of the current main. its like plucking the branch from the root and planting on the main. the parent of the oldest commit in feature branch is the current main. then each of the feature branches commits are put on top essetially a dupliacate with new hashcode, and the child of the added duplicate feature branch commit is the current main now. Rebase replays feature commits on top of the current main by creating new commits with new hashes, resulting in a linear history.
problem is the hash of the commit when feature commits were separate is different from the copies we inserted on the top of the main. since they are treated different commits if rebase and merge happens together (dont know in which order its harmful) it adds duplicates in line of code
basically if you want linear changes in the local then use rebase

rebasing the commits which other people have creates duplication cuz after rebase you work on the duplicate but the others work on the original then they both will be added in main
![](Rebase%201.jpg)


## Reflog

Its the history of the commits head moved. we can recover the commits using reflog essentially recovering the code. 
reflog retains reachable commits for 90 days and unreachable for 30 days