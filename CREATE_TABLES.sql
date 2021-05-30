USE task1

CREATE TABLE lessons (
	id TINYINT PRIMARY KEY IDENTITY,
	topic NVARCHAR(50) UNIQUE NOT NULL CHECK (topic!=''),
	date_of_class DATE NOT NULL,
	lecture_file VARBINARY NOT NULL,
	sign_of_completeness BIT DEFAULT 0 NOT NULL,
);

CREATE TABLE questions (
	id TINYINT IDENTITY,
	content NVARCHAR NOT NULL,
	lesson_id TINYINT,
	FOREIGN KEY (lesson_id) REFERENCES lessons(id),
	PRIMARY KEY (id, lesson_id)
);

CREATE TABLE student (
	id_number TINYINT PRIMARY KEY IDENTITY,
	first_name NVARCHAR(50) NOT NULL,
	last_name NVARCHAR(50) NOT NULL,
	pin NVARCHAR(20) NOT NULL
);

CREATE TABLE lessons_student (
	student_id TINYINT,
	lesson_id TINYINT,
	FOREIGN KEY (student_id) REFERENCES student(id_number),
	FOREIGN KEY (lesson_id) REFERENCES lessons(id),
	PRIMARY KEY (student_id, lesson_id)
);

CREATE TABLE answer (
	id TINYINT IDENTITY,
	date_of_reply DATE NOT NULL,
	content NVARCHAR  NOT NULL,
	question_id TINYINT,
	lesson_id TINYINT,
	student_id TINYINT,
	FOREIGN KEY (student_id) REFERENCES student(id_number),
	FOREIGN KEY (question_id, lesson_id) REFERENCES questions(id,lesson_id),
	PRIMARY KEY (id, question_id, lesson_id)
);

CREATE TABLE answer_student (
	student_id TINYINT,
	answer_id TINYINT,
	question_id TINYINT,
	lesson_id TINYINT,
	FOREIGN KEY (student_id) REFERENCES student(id_number),
	FOREIGN KEY (answer_id, question_id, lesson_id) REFERENCES answer(id,question_id,lesson_id),
	PRIMARY KEY (student_id, answer_id)
);