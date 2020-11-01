-- HQL/SQL Scripts
--Что бы все правильно работало для Hibernate лучше использовать автогенерацию таблиц.
-- SQL script (FIND TEACHER)
select t.id_teacher as id, t.email as email, t.first_name as first, t.last_name as last
from course c
         inner join teacher t on c.teacher_id_teacher = t.id_teacher
where (select count(s.id_course) from student s where c.id_course = s.id_course) >= 2000;


--HQL script
SELECT teacher
FROM Course c
WHERE size(c.studentList) >= 2000;
----------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------
--Script (FIND STUDENTS)
select s.last_name  as last,
       s.first_name as first,
       s.id_student as id_student,
       s.id_course  as id_cours,
       course_title,
       s.id_group   as id_group
from marks m
         inner join student s on m.id_student = s.id_student
         cross join student s2
         cross join course c3
where m.id_student = s2.id_student
  and s2.id_course = c3.id_course
  and m.mark = '4'
  and c3.course_title = 'Programming'
order by last, first;


--HQL script
SELECT student
FROM Marks m
where m.mark = '4'
  and m.student.course.courseTitle = 'Programming'
order by m.student.lastName, m.student.firstName;
----------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------
---- SQL script (Find All)
select g.name_group                                                                        as name_group,
       (select count(slist.id_group) from student slist where g.id_group = slist.id_group) as col_studentov,
       count(s.id_student)                                                                 as col_bogov,
       t.last_name                                                                         as last_name,
       t.first_name                                                                        as first_name,
       t.email                                                                             as email
from groups g
         cross join marks m
         cross join student s
         cross join teacher t
where g.teacher_id_teacher = t.id_teacher
  and s.id_student = m.id_student
  and s.id_group = g.id_group
  and m.mark = '5'
group by g.id_group, t.last_name, t.first_name, t.email;


--HQL script
SELECT g.nameGroup,
       size(g.studentList),
       count(s.idStudent),
       g.teacher.lastName,
       g.teacher.firstName,
       g.teacher.email
FROM Groups g,
     Marks m,
     Student s

Where s.idStudent = m.student.idStudent
  and s.group.idGroup = g.idGroup
  and m.mark = '5'

group by g.idGroup, g.teacher.lastName, g.teacher.firstName, g.teacher.email;


















--SQL Scripts

-- Script (FIND TEACHER)
SELECT first_name, last_name, email
FROM u_teacher t,
     student_cource sc
where t.id_cource = sc.id_cource
group by id_teacher
having COUNT(sc.id_student) >= 2000;
----------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------
--Script (FIND STUDENTS)
SELECT first_name, last_name, course_title

FROM marks m

         Inner join u_student s ON s.id_student = m.id_student
         Inner join u_cource c ON m.id_cource = c.id_cource


where c.id_cource = m.id_cource
  and m.mark = '4'
  and c.course_title = 'Programming'

order by last_name, first_name;
----------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------
--Script (FIND ALL)
Select distinct count(mark), null as teachers
from u_student s,
     u_group p,
     marks m,
     u_teacher t
where s.id_group = p.id_group
  and s.id_student = m.id_student
  and m.mark = '5'
group by t.first_name
UNION ALL
select distinct count(mark), null
from marks m,
     u_teacher t,
     u_student s
         inner join u_group p ON s.id_group = p.id_group
where s.id_student = m.id_student
group by t.first_name
UNION ALL
select null, t.first_name
from marks m,
     u_group p
         inner join u_student s ON p.id_group = s.id_group
         inner join u_teacher t ON p.id_teacher = t.id_teacher
group by t.first_name


