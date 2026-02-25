---
URL: https://youtu.be/9HOpanT0GRs?si=pv4lQl3SbrJ1htl4
---
Authentication - prove who you are. if you get access
Autorization - restrict the access to certain features or data based on who you are. what you get access to
username - the public proof of identity
password - private proof of identity
Dictionary attack - run through all english or other language words to check if they are the password
brute force - try all possible combination
normal numeric password length is 4 digits so $10^4$ is 10000 possibilites. take only a few milliseconds to generate.
8 characters with all ascii which is 52 chars, 10 digits, 32 symbols is $94^8$
which is more that 6 quadrillion
Nist has some recommendation or standards for password, min 8 and max 64 chars. allow all of unicode. verifiers to check if teh password is commonly used or sequence or repeated chars and tell the user to make better passwords
dont use the hint like what was the name of the first pet
implement rate of failed limit to slow dont the brute force attacks
TFA or MFA used as an double barrier which the works with possesion
ways of authentication 
Knowledge - password
possesion - phone or keycard
inherence - unique to you only (biometrics)
OTP - can be a problem if hacker convinces that sim is swapped
keylogging - recording keystrokes and send to the hacker
credential stuffing - using the data from a dataleak to try in another website
social engineering - showing trust then collecting data like password
phishing - hook that when you click it steals data like a spam email. posing like a legitimate email but redirecting to wrong url to extract
Machine in the middle - Routers, routing servers etc maybe a threat.
man in the middle - hacker posing as server and 