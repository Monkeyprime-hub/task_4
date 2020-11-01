package dao;

import domain.Student;
import domain.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class UniversityDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UniversityDao.class);


    //для выборки всех преподавателей на курсах, у которых учатся больше 2000 студентов;
    public List<Teacher> findTeacher() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
//        List resultList1 = em.createQuery("Select teacher FROM Course,Student WHERE studentList.size>=3 group by teacher").getResultList();
//        List resultList1 = em.createQuery(" SELECT teacher.firstName FROM Course c WHERE size(c.studentList)>3 ").getResultList();
        List resultList = em.createQuery(" SELECT teacher  FROM Course c WHERE size(c.studentList)>=5").getResultList();
        LOGGER.info("result : " + resultList);
        System.out.println(resultList.toString());
        em.close();
        return resultList;
    }


    //для выборки студентов, у которых по курсу 'Programming' оценка > 4, и отсортировать их по фамилии, имени;
    public List<Student> findStudents() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List resultList = em.createQuery("SELECT student FROM Marks m where m.mark = '4' and m.student.course.courseTitle='Programming' order by m.student.lastName, m.student.firstName").getResultList();
        LOGGER.info("result : " + resultList);

        return resultList;
    }

    //запрос, который выведет количество отличников, количество студентов и преподавателя в каждой группе.
    public List findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List resultList = em.createQuery("SELECT g.nameGroup,size(g.studentList), count(s.idStudent), g.teacher.lastName,g.teacher.firstName,g.teacher.email FROM Groups g, Marks m, Student s " +
                "Where s.idStudent = m.student.idStudent " +
                "and s.group.idGroup = g.idGroup " +
                "and m.mark='5'" +
                "group by g.idGroup,g.teacher.lastName,g.teacher.firstName,g.teacher.email").getResultList();
        LOGGER.info("result : " + resultList);

        return resultList;
    }


    public List<Student> createStudents() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Student> resultList = new ArrayList<>();

        List resultList1 = em.createQuery("from Student where idStudent=10").getResultList();
        List resultList2 = em.createQuery("from Student where idStudent=3").getResultList();
        List resultList3 = em.createQuery("from Student where idStudent=5").getResultList();

        resultList.addAll(resultList1);
        resultList.addAll(resultList2);
        resultList.addAll(resultList3);


        LOGGER.info("result : " + resultList);

        return resultList;
    }


//g.nameGroup, size(g.studentList),g.teacher.lastName,g.teacher.firstName,

    // Запрос, который выведет количество отличников, количество студентов и преподавателя в каждой группе.

//        em.getTransaction().begin();
//        em.createQuery("FROM Student WHERE Student .idStudent=1" );

//    }
//    public void hibernateJpa() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//
//        Student student = new Student();
////        student.setIdStudent(1L);
//        student.setFirstName("Vlad");
//        student.setLastName("Dudko");
//        student.setIdGroup(1);
//
//        em.persist(student);
//        em.getTransaction().commit();
//        em.close();
//    }
}
