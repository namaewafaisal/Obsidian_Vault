not many people understand git and just memorises the commands that they use most
Git is a database, every commit is a record or a row in the database. 
Commit : A Snapshot like the linux snapshot. it is like a photograph that stores the state of every file included in the git or in the git folder. 
Commit consists of Snapshot, metadata which says who committed, commit message when etc. and the pointer to the parent commit or the previous commit.
does changing head to a past commit make it forget the commits after that? 
**commit always point backwards**

![](DAG%201.jpg)

Its always directed meaning child commit points to its parent and not viceVersa
Acyclic meaning cant point to both parent and grandparent. its commit can have only one childe i think. but parents cant be in the same chain. in the above both branch 1 and branch 2 are parents or merge but they dont belong in a same path. they are parellel. if they were not then either of them must be an ancestor or merge and you cant point to ancestor but only to parent
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
