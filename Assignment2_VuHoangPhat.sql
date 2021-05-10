CREATE DATABASE	Assignment2_VuHoangPhat
go
use Assignment2_VuHoangPhat;
go

create table account
(
	email varchar(100) primary key,
	name varchar(50),
	password varchar(max),
	role bit,
	status bit,
)
go
create table subject
(
	id int identity(1, 1) primary key,
	nameSubject varchar(50) unique,
	timeout int
)
go
create table question
(
	id int identity(1, 1) primary key,
	questionContent varchar(max),
	answer_1 varchar(max),
	answer_2 varchar(max),
	answer_3 varchar(max),
	answer_4 varchar(max),
	correct_answer varchar(max),
	status bit,
	subject_id int FOREIGN KEY REFERENCES subject(id),
	create_date datetime default CURRENT_TIMESTAMP,
	update_date date
)
go
create table history
(
	id int identity(1,1) primary key,
	userName varchar(max),
	quiz_date datetime default CURRENT_TIMESTAMP,
	score float, 
	mail varchar(100) FOREIGN KEY REFERENCES account(email),
	subject_id int FOREIGN KEY REFERENCES subject(id)
)
go

create table history_detail
(
	history_id int FOREIGN KEY REFERENCES history(id),
	question_id int FOREIGN KEY REFERENCES question(id),
	PRIMARY KEY (history_id, question_id),
	question varchar(max),
	correct_answer varchar(max),
	user_answer varchar(max)
)
go


insert into subject(nameSubject, timeout) 
values('PRN311', 50)
insert into subject(nameSubject, timeout) 
values('PRJ321', 50)
insert into subject(nameSubject, timeout) 
values('PRN252', 50)
--PRN311--
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 1','Option 1','Option 2','Option 3','Option 4','Option 1',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 2','Option 1','Option 2','Option 3','Option 4','Option 2',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 3','Option 1','Option 2','Option 3','Option 4','Option 3',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 4','Option 1','Option 2','Option 3','Option 4','Option 4',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 5','Option 1','Option 2','Option 3','Option 5','Option 5',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 6','Option 1','Option 2','Option 6','Option 4','Option 6',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 7','Option 1','Option 7','Option 3','Option 4','Option 7',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 8','Option 8','Option 2','Option 3','Option 4','Option 8',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 9','Option 1','Option 2','Option 3','Option 9','Option 9',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 10','Option 1','Option 2','Option 10','Option 4','Option 10',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 11','Option 1','Option 11','Option 3','Option 4','Option 11',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 12','Option 12','Option 2','Option 3','Option 4','Option 12',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 13','Option 1','Option 2','Option 3','Option 13','Option 13',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 14','Option 1','Option 2','Option 14','Option 4','Option 14',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 15','Option 1','Option 15','Option 3','Option 4','Option 15',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 16','Option 16','Option 2','Option 3','Option 4','Option 16',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 17','Option 1','Option 2','Option 3','Option 17','Option 17',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 18','Option 1','Option 2','Option 18','Option 4','Option 18',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 19','Option 1','Option 19','Option 3','Option 4','Option 19',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 20','Option 20','Option 2','Option 3','Option 4','Option 20',1,1)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 21','Option 1','Option 2','Option 3','Option 21','Option 21',1,1)
--PRJ321--
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 1','Option 1','Option 2','Option 3','Option 4','Option 1',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 2','Option 1','Option 2','Option 3','Option 4','Option 2',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 3','Option 1','Option 2','Option 3','Option 4','Option 3',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 4','Option 1','Option 2','Option 3','Option 4','Option 4',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 5','Option 1','Option 2','Option 3','Option 5','Option 5',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 6','Option 1','Option 2','Option 6','Option 4','Option 6',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 7','Option 1','Option 7','Option 3','Option 4','Option 7',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 8','Option 8','Option 2','Option 3','Option 4','Option 8',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 9','Option 1','Option 2','Option 3','Option 9','Option 9',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 10','Option 1','Option 2','Option 10','Option 4','Option 10',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 11','Option 1','Option 11','Option 3','Option 4','Option 11',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 12','Option 12','Option 2','Option 3','Option 4','Option 12',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 13','Option 1','Option 2','Option 3','Option 13','Option 13',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 14','Option 1','Option 2','Option 14','Option 4','Option 14',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 15','Option 1','Option 15','Option 3','Option 4','Option 15',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 16','Option 16','Option 2','Option 3','Option 4','Option 16',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 17','Option 1','Option 2','Option 3','Option 17','Option 17',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 18','Option 1','Option 2','Option 18','Option 4','Option 18',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 19','Option 1','Option 19','Option 3','Option 4','Option 19',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 20','Option 20','Option 2','Option 3','Option 4','Option 20',1,2)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 21','Option 1','Option 2','Option 3','Option 21','Option 21',1,2)
--PRN252--
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 1','Option 1','Option 2','Option 3','Option 4','Option 1',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 2','Option 1','Option 2','Option 3','Option 4','Option 2',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 3','Option 1','Option 2','Option 3','Option 4','Option 3',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 4','Option 1','Option 2','Option 3','Option 4','Option 4',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 5','Option 1','Option 2','Option 3','Option 5','Option 5',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 6','Option 1','Option 2','Option 6','Option 4','Option 6',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 7','Option 1','Option 7','Option 3','Option 4','Option 7',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 8','Option 8','Option 2','Option 3','Option 4','Option 8',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 9','Option 1','Option 2','Option 3','Option 9','Option 9',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 10','Option 1','Option 2','Option 10','Option 4','Option 10',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 11','Option 1','Option 11','Option 3','Option 4','Option 11',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 12','Option 12','Option 2','Option 3','Option 4','Option 12',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 13','Option 1','Option 2','Option 3','Option 13','Option 13',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 14','Option 1','Option 2','Option 14','Option 4','Option 14',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 15','Option 1','Option 15','Option 3','Option 4','Option 15',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 16','Option 16','Option 2','Option 3','Option 4','Option 16',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 17','Option 1','Option 2','Option 3','Option 17','Option 17',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 18','Option 1','Option 2','Option 18','Option 4','Option 18',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 19','Option 1','Option 19','Option 3','Option 4','Option 19',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 20','Option 20','Option 2','Option 3','Option 4','Option 20',1,3)
insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) 
values('Select option 21','Option 1','Option 2','Option 3','Option 21','Option 21',1,3)