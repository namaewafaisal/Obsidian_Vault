So a web request is basically data moving through the web through many programs.
4 things can happen when the request moving through. request can get
1. Transformed
2. Routed
3. Blocked
4. Answered

## Stage 0
We type example.com in the browser. nothing has happend yet. no http, no backend, nothing. just text in the url box.
The browser's job is to find the ip address of the url. The english text ie the url is nothing but the domain name. we cant access the service with just the name. 

## Stage 1
### DNS
Domain Name System is the database or a dictionary like which matches the Domain name and get the corresponding IP. 
the faster the DNS (fast response or low latency because its closer to user) the faster 