delete from lesson;

SET IDENTITY_INSERT lesson ON;

insert into lesson(id, date_of_class, sign_of_completeness, topic, user_id)
	values (1, '2021-06-20', 1, 'Theme1', 1),
		   (2, '2021-06-21', 1, 'Theme2', 1),
		   (3, '2021-06-22', 1, 'Theme3', 1),
		   (4, '2021-06-23', 1, 'Theme4', 1),
		   (5, '2021-06-24', 1, 'Theme5', 1);
		   
SET IDENTITY_INSERT lesson OFF;