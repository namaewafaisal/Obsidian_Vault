nodes - individual Systems
vertical scaling - upgrading specs
horizontal scaling - increasing the number nodes
load balancer ditributes the workload evenly across the nodes
database cannot be scaled horizontally. cuz one data cannot have its corresponding foreign table in another node so we have all related records in a single data base while splitting the records across multiple nodes.
sharding - splitting the records in different nodes and the backend has a id of which shard the data belongs to.
