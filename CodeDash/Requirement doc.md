# Requirements Document
## Actors
- Master (ME)
-  Admin (who register the college, maybe even buy service for college if i make it paid)
-  Staff (Views and analyses student reports, and export etc placement head and staff, mentor all come here. if placement head wants the full control he should have been the one that registered the college for service)
-  Student (he manages his profile and handles)

so at the top Master is there and then i control the institution admins. it means i dont get to see the students or anything directly but manage admin subscription or something. then every other is split based on the institution. then admin, staff, and student. so a student is found by college, and role.

initially no institution then an institution registers witht the details

 - name                   
 - email domain           
 - slug (maybe if needed) 
 - email
 - password
the person who registers become the admin.
and he has access to all students and features of the students of his institution

then a student/staff register 
- email
- institution(select via name or slug maybe or id)
- password
the email is verfied agaisnt the domain of the institution and even sent a mail to verify ownership.

then after registration user is created. 
next is the admin has access to change the user role to staff or if the user is student he remains student. meaning default is student.

after that student need to create a profile to mark profile done or else user is removed maybe. but these maybe are just a future features. after creation the student will be listed in dashboard. his individual profile and the leaderboard.
after prfile the user needs to add handles. 
for each handle a record is created and at night included in updation
maybe we even add a feature to verify the handles via mail but likely its paid. 

## Verification

-  admin -> give domain -> check every new staff/student registration against domain -> domain confirmed
- send a verification mail -> enter code -> mail ownership confirmed.
Now the user is of that institution and the owner of the mail.

-  Admin verifies the user -> promote role if ins staff

- verification of student details -> staff compares mail and register number and name maybe? or the initial creation of profile itself done by the staff.
#### Handle verfication
- handle entered -> verification email -> ownership confirmed

Now user confirmed , handles confirmed -> authentic.
> Our college uses registernumber in email as 814723104089@trp.srmtrichy.edu.in. if that is common for student login then match register number from email to confirm student

## Functionalities

- leaderboard based on github contributions, leetcode problems(all,hard,medium,easy,consistency maybe)
- export the stats (userdetails, leetcode, gtihub,codechef selected bythe staff or admin. )
- View last week stats, by days. meaning full data of last 7 days and additonal if exist in the platform data retreived

## Schema (planned as of now)

### User
I think the below is good schema for all the roles users
```
id uuid
email String validated by @Email, Emaildomain given
password min 8 char max 64 min 1 numeric and min 1 uppercase
role enum MASTER or ADMIN for me, STUDENT, STAFF, INSTITUTION_ADMIN (now yet fixed name)
Institution id to join maybe or slug
created_at date enough

past design 
profile completed (no need for admin, staff so removed)
verified -> only creates user if verified the email so no need
```

### Institution
A table where each institution specific data lives
```
id Integer autoincrement or generation type identity
manytoone userid fk 
institution name 
domain name String to verify
```

### StudentProfile
Only needed for the student to create
```
id autoincrement Integer
userid fk
Name String
register number Verified from email
Department
year
section
trainingBatch
created_at date
gender but mostly not needed
one to many -> handles
```
with above student is identified via institution, department, year, section, register number

### StudentHandle
One record per {profile,platform}
```
id Autoincrement
manytoone profile fk
Platform enum github, leetcode etc
lastUpdated Date (last change of username)
onetoone stats fk
```

## HandleStats
For each student's each platform
```
id autoincrement
onetoone handle fk
Platform enum github, leetcode etc

// Below are maybe null fields based on the platform
problemssolved
easysolved
medium solved
hardsolved
ranking
rating
lastUpdated
latestsubmission
totalsubmissioninpastweek
rawdata jsonb
```

