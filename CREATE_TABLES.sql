USE task1

CREATE TABLE Lessons (
	Lesson_ID TINYINT PRIMARY KEY IDENTITY,
	Topic NVARCHAR(50) UNIQUE NOT NULL CHECK (Topic!=''),
	Lesson_date DATE NOT NULL,
	Lecture_file VARBINARY NOT NULL,
	Sign_of_completeness BIT DEFAULT 0 NOT NULL
);

CREATE TABLE Questions (
	Question_ID TINYINT PRIMARY KEY IDENTITY,
	Question NVARCHAR NOT NULL
);

CREATE TABLE Lessons_Questions (
	LessonID TINYINT,
	QuestionID TINYINT,
	FOREIGN KEY (LessonID)  REFERENCES Lessons(Lesson_ID),
	FOREIGN KEY (QuestionID)  REFERENCES Questions(Question_ID),
	PRIMARY KEY(LessonID, QuestionID)
);

CREATE TABLE Student (
	Student_ID_number TINYINT PRIMARY KEY IDENTITY,
	Student_Surname NVARCHAR(50) NOT NULL,
	Student_Name NVARCHAR(50) NOT NULL,
	Pin NVARCHAR(20) NOT NULL
);

CREATE TABLE Lessons_Student (
	StudentID TINYINT,
	LessonID TINYINT,
	FOREIGN KEY (StudentID) REFERENCES Student(Student_ID_number),
	FOREIGN KEY (LessonID) REFERENCES Lessons(Lesson_ID),
	PRIMARY KEY (StudentID, LessonID)
);

CREATE TABLE Answer (
	Answer_ID TINYINT PRIMARY KEY IDENTITY,
	Answer_Date DATE NOT NULL,
	Answer_Text NVARCHAR  NOT NULL,
	StudentID TINYINT,
	FOREIGN KEY (StudentID) REFERENCES Student(Student_ID_number)
);

CREATE TABLE Answer_Student (
	StudentID TINYINT,
	AnswerID TINYINT,
	FOREIGN KEY (StudentID) REFERENCES Student(Student_ID_number),
	FOREIGN KEY (AnswerID) REFERENCES Answer(Answer_ID),
	PRIMARY KEY (StudentID, AnswerID)
);