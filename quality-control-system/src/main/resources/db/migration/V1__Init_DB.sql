create table answer (
    id bigint identity not null,
    content varchar(255),
    date_of_reply varchar(255),
    question_id bigint,
    lesson_id bigint,
    user_id bigint,
    primary key (id)
)

create table answer_user (
    user_id bigint not null,
    answer_id bigint not null,
    primary key (answer_id, user_id)
)

create table lesson (
    id bigint identity not null, 
    date_of_class varchar(255), 
    lecture_file varchar(255), 
    sign_of_completeness bit not null, 
    topic varchar(255), 
    user_id bigint, 
    primary key (id)
)

create table lesson_questions (
    lesson_id bigint not null, 
    questions_id bigint not null, 
    primary key (lesson_id, questions_id)
)

create table question (
    id bigint identity not null, 
    content varchar(255), 
    lesson_id bigint, 
    primary key (id)
)

create table user_role (
    user_id bigint not null, 
    role varchar(255)
)

create table usr (
    id bigint identity not null, 
    active bit not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
)

create table user_lesson (
	id bigint identity not null,
	user_id bigint,
	sign_of_completeness bit not null, 
	lesson_id bigint
)

alter table lesson_questions 
    add constraint lesson_questions_uk
    unique (questions_id)

alter table answer 
    add constraint answer_question_fk
    foreign key (question_id) references question

alter table answer 
    add constraint answer_usr_fk
    foreign key (user_id) references usr

alter table answer_user 
    add constraint answer_user_answer_fk
    foreign key (answer_id) references answer

alter table answer_user 
    add constraint answer_user_usr_fk
    foreign key (user_id) references usr
    
alter table user_lesson
	add constraint user_lesson_usr_fk
	foreign key (user_id) references usr

alter table user_lesson
	add constraint user_lesson_lesson_fk
	foreign key (lesson_id) references lesson
	
alter table lesson 
    add constraint lesson_usr_fk
    foreign key (user_id) references usr

alter table lesson_questions 
    add constraint lesson_questions_question_fk
    foreign key (questions_id) references question

alter table lesson_questions 
    add constraint lesson_questions_lesson_fk
    foreign key (lesson_id) references lesson

alter table question 
    add constraint question_lesson_fk
    foreign key (lesson_id) references lesson

alter table user_role 
    add constraint user_role_usr_fk
    foreign key (user_id) references usr