--Address
INSERT into address (id, street, city, postal_code, state_province, country_region)
VALUES (1, '100N 4th street', 'Fairfield', '52557', 'Iowa', 'US' );

INSERT into address (id, street, city, postal_code, state_province, country_region)
VALUES (2, '200W street', 'Oakland', '52557', 'California', 'US' );

--Courses
INSERT INTO course (id, code, name, description)
VALUES (1, 'CS544', 'EA', 'Enterprise Architecture');

INSERT INTO course (id, code, name, description)
VALUES (2, 'CS545', 'WAA', 'Web Application Architecture');

INSERT INTO course (id, code, name, description)
VALUES (3, 'CS472', 'WAP', 'Web Programming');

INSERT INTO course (id, code, name, description)
VALUES (4, 'CS590', 'SA', 'Software Architecture');

INSERT INTO course (id, code, name, description)
VALUES (5, 'CS390', 'FPP', 'Fundamental Programming Practice');

INSERT INTO course (id, code, name, description)
VALUES (6, 'CS422', 'DBMS', 'Database Management System');

INSERT INTO course (id, code, name, description)
VALUES (7, 'CS401', 'MPP', 'Modern Programming Practices');

--Course_Prerequisites
INSERT INTO Course_Prerequisites
values ( 1, 6 );

INSERT INTO Course_Prerequisites
values ( 1, 5 );

INSERT INTO Course_Prerequisites
values ( 1, 7 );

INSERT INTO Course_Prerequisites
values ( 2, 3 );

INSERT INTO Course_Prerequisites
values ( 2, 5 );

INSERT INTO Course_Prerequisites
values ( 4, 7 );

--Faculty
INSERT INTO faculty (id, name, email, title)
VALUES ( 1, 'Ahmed', 'Ahmed@miu.edu', 'Associate Professor' );

INSERT INTO faculty (id, name, email, title)
VALUES ( 2, 'Wzan', 'Wazan@miu.edu', 'Professor' );

INSERT INTO faculty (id, name, email, title)
VALUES ( 3, 'Keven', 'Keven@miu.edu', 'Associate Professor' );

INSERT INTO faculty (id, name, email, title)
VALUES ( 4, 'Rima', 'Rima@miu.edu', 'Assistant Professor' );

INSERT INTO faculty (id, name, email, title)
VALUES ( 5, 'Sanad', 'Sanad@miu.edu', 'Assistant Professor' );

-- Students
Insert Into student (id, student_id, name, email, home_address_id, mailing_address_id)
VALUES (1, '615660', 'Robert', 'fotofilimon@gmail.com', 1, 2);

Insert Into student (id, student_id, name, email, home_address_id, mailing_address_id)
VALUES (2, '615661', 'Dave', 'luwamweldemichael@miu.edu', 2, 1);

Insert Into student (id, student_id, name, email, home_address_id, mailing_address_id)
VALUES (3, '615662', 'John', 'luwamtmariam@gmail.com', 1, 2);

Insert Into student (id, student_id, name, email, home_address_id, mailing_address_id)
VALUES (4, '615663', 'Emily', 'luwamtesfamariam26@gmail.com', 1, 2);

Insert Into student (id, student_id, name, email, home_address_id, mailing_address_id)
VALUES (5, '615664', 'abdelaziz', 'aboukham.abdelaziz@gmail.com', 1, 2);

--Registration Group
INSERT INTO registration_group (id, name)
VALUES ( 1, 'FPP Group Registration' );

INSERT INTO registration_group (id, name)
VALUES ( 2, 'MPP Group Registration' );

--REGISTRATION_GROUP_STUDENTS
INSERT INTO REGISTRATION_GROUP_STUDENTS (REGISTRATION_GROUP_ID, STUDENTS_ID)
values ( 2, 2 );

INSERT INTO REGISTRATION_GROUP_STUDENTS (REGISTRATION_GROUP_ID, STUDENTS_ID)
values ( 2, 1 );

INSERT INTO REGISTRATION_GROUP_STUDENTS (REGISTRATION_GROUP_ID, STUDENTS_ID)
values ( 2, 3 );

INSERT INTO REGISTRATION_GROUP_STUDENTS (REGISTRATION_GROUP_ID, STUDENTS_ID)
values ( 2, 4 );

INSERT INTO REGISTRATION_GROUP_STUDENTS (REGISTRATION_GROUP_ID, STUDENTS_ID)
values ( 2, 5 );

--Registration Event
INSERT INTO registration_event (id, start_date_time, end_date_time)
VALUES ( 1, '2022-12-06 12:21:47' , '2022-12-16 12:21:47' );

INSERT INTO registration_event (id, start_date_time, end_date_time)
VALUES ( 2, '2022-12-16 12:21:47' , '2022-12-26 12:21:47' );

--REGISTRATION_EVENT_REGISTRATION_GROUPS
INSERT INTO REGISTRATION_EVENT_REGISTRATION_GROUPS (REGISTRATION_EVENT_ID, REGISTRATION_GROUPS_ID )
VALUES ( 1, 2 );

INSERT INTO REGISTRATION_EVENT_REGISTRATION_GROUPS (REGISTRATION_EVENT_ID, REGISTRATION_GROUPS_ID )
VALUES ( 2, 1 );

-- Academic Block
INSERT INTO academic_block (id, code, semester, start_date,end_date, group_id)
VALUES ( 1, 'AUG', 'winter', '2022-01-01 12:15:47', '2022-01-28 12:10:47' , 1);

INSERT INTO academic_block (id, code, semester, start_date,end_date, group_id)
VALUES ( 2, 'SEP', 'spring', '2022-05-01 10:15:47', '2022-05-28 12:10:47', 2 );

INSERT INTO academic_block (id, code, semester, start_date,end_date, group_id)
VALUES ( 3, 'NOV', 'summer', '2022-12-01 10:15:47', '2022-12-28 12:10:47', 1 );

INSERT INTO academic_block (id, code, semester, start_date,end_date, group_id)
VALUES ( 4, 'DEC', 'summer', '2022-03-01 10:15:47', '2022-12-30 12:10:47', 2 );

--CourseOffering
INSERT INTO course_offering(id, code, name, capacity, available_seats, course_id, faculty_id, block_id)
VALUES (1, 'CS544', 'CS544 Enterprise Architecture', 50, 35, 1, 1, 2);

INSERT INTO course_offering(id, code, name, capacity, available_seats, course_id, faculty_id, block_id)
VALUES (2, 'CS545', 'CS545 Web Application Architecture', 25, 13 ,2, 2, 2);

INSERT INTO course_offering(id, code, name, capacity, available_seats,course_id, faculty_id, block_id)
VALUES (3, 'CS472', 'CS472 Web Programming', 20, 10 , 3, 3, 1);

INSERT INTO course_offering(id, code, name, capacity, available_seats,course_id, faculty_id, block_id)
VALUES (4, 'CS590', 'CS590 Software Architecture', 35, 15, 4, 4, 1);

INSERT INTO course_offering(id, code, name, capacity, available_seats, course_id, faculty_id, block_id)
VALUES (5, 'CS590', 'CS590 Software Architecture', 35, 35 ,5, 5, 3);

--Request
-- INSERT INTO REGISTRATION_REQUEST (id, priority_number, course_offering_id, student_Id, processed)
-- values ( 1, 1, 1 , 1);
--
-- INSERT INTO REGISTRATION_REQUEST (id, priority_number, course_offering_id, student_Id)
-- values ( 2, 2, 2, 2 );
--
-- INSERT INTO REGISTRATION_REQUEST (id, priority_number, course_offering_id, student_Id)
-- values ( 4, 1,  3, 3);
--
-- INSERT INTO REGISTRATION_REQUEST (id, priority_number,course_offering_id, student_Id)
-- values ( 3, 2, 4, 4 );


INSERT INTO REGISTRATION_REQUEST (id, priority_number, course_offering_id, student_Id, processed)
values ( 1, 1, 1 , 1, false);

INSERT INTO REGISTRATION_REQUEST (id, priority_number, course_offering_id, student_Id, processed)
values ( 2, 2, 2, 2, false );

INSERT INTO REGISTRATION_REQUEST (id, priority_number, course_offering_id, student_Id, processed)
values ( 4, 1,  3, 3, false);

INSERT INTO REGISTRATION_REQUEST (id, priority_number,course_offering_id, student_Id, processed)
values ( 3, 2, 4, 4, false);
