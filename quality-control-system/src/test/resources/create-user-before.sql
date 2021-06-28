delete from user_role;
delete from usr;

SET IDENTITY_INSERT usr ON; 

insert into usr(id, active, password, username)
    values (1, 1, '1', 'a');     

insert into user_role(user_id, role)
    values (1, 'LECTURER');

SET IDENTITY_INSERT usr OFF;