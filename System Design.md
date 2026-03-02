nodes - individual Systems
vertical scaling - upgrading specs
horizontal scaling - increasing the number nodes
load balancer ditributes the workload evenly across the nodes
database cannot be scaled horizontally. cuz one data cannot have its corresponding foreign table in another node so we have all related records in a single data base while splitting the records across multiple nodes.
sharding - splitting the records in different nodes and the backend has a id of which shard the data belongs to.
services - individual software which is small
application - has everything it needs inside it.
if the entire application is holded in a repository and run in a machine this is monolith architecture. meaning whatever request in the app goes to the same machine and find its way to the service.
### Disadvantages in monolith
- monolith is not recomended cuz it creates single pooint of failure, single change requires rebuilding and deploying the entire application just because they are in the same repo.
- some sevice may get a lot of traffic while some may get barely few so scaling horizontally or vertically is not enough
Modular monolith
- each service is in its own repository 
- handled separately so no single point of failure
- they communicate with each other using http queries
- individual service can be scaled based on the traffic so no idle or unwanted scaling
Microservices
- each service may not need its own repository so we put related together based on need
- so less requirements
- loosely coupled yet efficient
Orchestration 
creating and configuring the systems exactly as their need without version conflicts and stuff. (dont know fully)
developing team hand the code to devops
next is no ops so no heavy devops work