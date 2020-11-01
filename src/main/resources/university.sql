DROP TABLE IF EXISTS u_marks;
DROP TABLE IF EXISTS u_cource;
DROP TABLE IF EXISTS u_student;
DROP TABLE IF EXISTS u_teacher;
DROP TABLE IF EXISTS u_group;

CREATE TABLE u_cource
(
    id_cource    SERIAL,
    course_title varchar(100),
    PRIMARY KEY (id_cource)
);

CREATE TABLE u_student
(
    id_student SERIAL,
    first_name varchar(50),
    last_name  varchar(50),
    PRIMARY KEY (id_student)
);

CREATE TABLE u_teacher
(
    id_teacher SERIAL,
    first_name varchar(50),
    last_name  varchar(50),
    email      varchar(100),
    id_cource  integer not null,
    PRIMARY KEY (id_teacher),
    FOREIGN KEY (id_cource) REFERENCES u_cource (id_cource) ON DELETE RESTRICT
);

CREATE TABLE marks
(
    id_marks   SERIAL,
    mark       varchar(20),
    id_student integer not null,
    id_cource  integer not null,
    PRIMARY KEY (id_marks),
    FOREIGN KEY (id_student) REFERENCES u_student (id_student) ON DELETE RESTRICT,
    FOREIGN KEY (id_cource) REFERENCES u_cource (id_cource) ON DELETE RESTRICT
);

CREATE TABLE student_cource
(
    id         SERIAL,
    id_student integer,
    id_cource  integer,
    PRIMARY KEY (id),
    FOREIGN KEY (id_student) REFERENCES u_student (id_student) ON DELETE RESTRICT,
    FOREIGN KEY (id_cource) REFERENCES u_cource (id_cource) ON DELETE RESTRICT
);


CREATE TABLE u_group
(
    id_group SERIAL,
    name     varchar(50),
    PRIMARY KEY (id_group)
);









INSERT INTO teacher(id_teacher,first_name, last_name, email)
VALUES (1,'Vlad', 'Vasilev', 'Vlados@gmail.com'),
       (2,'Liza', 'Vasilenko', 'Liza@mail.ru');


INSERT INTO course(id_course, course_title, teacher_id_teacher)
VALUES (1, 'Programming',1),
       (2, 'Pravo',2);

Insert into groups(id_group,name_group,teacher_id_teacher)
VALUES
(1,'ITPm19-1',1),
(2,'ITKNu19-1',2);



INSERT INTO student(id_student,first_name,last_name, id_group,id_course)
VALUES (1,'Vasya', 'Pupkin',1,1),
       (2,'Kostya', 'Isakiy',2,1),
       (3, 'Daniil', 'Kleba',1,1),
       (4, 'Test', 'Test',2,1),
       (5, 'Dima', 'Pokemon',1,1),
       (6, 'Lena', 'Vorovaya',2,2),
       (7, 'Dima', 'Kotelevskiy',1,2),
       (8, 'Egor', 'Stolba',2,1),
       (9,'Margo', 'Roby', 1,1),
       (10,'Arnold','Aslan',1,1);


INSERT INTO public.marks(id_mark, mark, id_student)
VALUES (1, '2', 1),
       (2, '3', 2),
       (3, '2', 3),
       (4, '4', 4),
       (5, '4', 5),
       (6, '3', 6),
       (7, '4', 7),
       (8, '3', 1),
       (10,'4',10);





















SELECT first_name
FROM u_teacher t,
     u_cource c
where c.id_student = 1
group by t.first_name
HAVING COUNT(c.id_student) > 1;

SELECT first_name, last_name, email
FROM u_teacher t,
     student_cource sc
where t.id_cource = sc.id_cource
group by id_teacher
having COUNT(sc.id_student) >= 6;




SELECT first_name, last_name, course_title

FROM u_student s,
     marks m,
     u_cource c

where s.id_student = m.id_student
  and c.id_cource = m.id_cource
  and m.mark = '4'
  and c.course_title = 'Programming'

group by last_name, first_name, course_title;





SELECT first_name, last_name, course_title

FROM marks m

         Inner join u_student s ON s.id_student = m.id_student
         Inner join u_cource c ON m.id_cource = c.id_cource


where c.id_cource = m.id_cource
  and m.mark = '4'
  and c.course_title = 'Programming'

group by last_name, first_name, course_title;
