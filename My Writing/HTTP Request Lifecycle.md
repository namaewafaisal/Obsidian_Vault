So a web request is basically data moving through the web through many programs.
4 things can happen when the request moving through. request can get
1. Transformed
2. Routed
3. Blocked
4. Answered

## Stage 0
We type example.com in the browser. nothing has happend yet. no http, no backend, nothing. just text in the url box.
The browser's job is to find the ip address of the url. The english text ie the url is nothing but the domain name. we cant access the service with just the name. 

## Stage 1 DNS
### DNS
Domain Name System is the database or a dictionary like which matches the Domain name and get the corresponding IP. 
the faster the DNS (fast response or low latency because its closer to user) the faster the IP is obtained. This only determines the initail loading speed of the Page. From then on the IP is already in the cache so the DNS is not used hence DNS does not determine the speed. 
After DNS we have the location (IP) to send packets(Requests) to.

## Stage 2 TCP
TCP connection before HTTP
TCP connection is established between the client machine and the Server IP.
The OS takes care of the connection establishment and not the application.
Now a Socket exist which is the combination of the IP and PORT. so application to application connection.
We can send the bytes using this connection
HTTP is still not created

## Stage 3 CDN
CDN is optional
Our request reaches the CDN and CDN (Content Delivery Network) provides the stuff that it has which is not backend logic. Like it can have the Images, Css, JS, and cached API responses so that if CDN is closer to you it can handle these tasks so less latency and less traffic in the backend. If it does not have what the client needs it re routes the Request. So its like a nearby warehouse which mostly store heavy stuff like 