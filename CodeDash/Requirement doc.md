## Actors
- Admin (ME)
- Institution Admin (who register the college, maybe even buy service for college if i make it paid)
- Institution Staff (Views and analyses student reports, and export etc placement head and staff, mentor all come here. if placement head wants the full control he should have been the one that registered the college for service)
- Institution Student (he manages his profile and handles)

so at the top ADMIN is there and then i control the institution admins. it means i dont get to see the students or anything directly but manage institution admin subscription or something. then every other is split based on the institution. then institiuion admin, staff, and student. so a student is found by college, and role.

initially no institution then an institution registers witht the details

 - name                   
 - email domain           
 - slug (maybe if needed) 
 - email
 - password
the person who registers become the institution admin.
and he has access to all students and features of the students of his institution

then a student/staff register 
- email
- institution(select via name or slug maybe or id)
- password
the email is verfied agaisnt the domain of the institution and even sent a mail to verify ownership.

then after registration user is created. 
next is the institution admin has access to change the user role to institution staff or if the user is student he remains student. meaning default is student.

after that student need to create a profile to mark profile done or else user is removed maybe. but these maybe are just a future features. after creation the student will be listed in dashboard. his individual profile and the leaderboard.
after prfile the user needs to add handles. 
for each handle a record is created and at night included in updation
maybe we even add a feature to verify the handles via mail but likely its paid. 

## Verification

- Institution admin -> give domain -> check every new staff/student registration against domain -> domain confirmed
- send a verification mail -> enter code -> mail ownership confirmed.
Now the user is of that institution and the owner of the mail.

- Ins Admin verifies the user -> promote role if ins staff

- verification of student details -> staff compares mail and register number and name maybe? or the initial creation of profile itself done by the staff.
#### Handle verfication
- handle entered -> verification email -> ownership confirmed
a


