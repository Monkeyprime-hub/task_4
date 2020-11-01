package dao;

import domain.Student;
import domain.Teacher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UniversityDaoTest {

    private static final Logger LOGGER =  LoggerFactory.getLogger(UniversityDaoTest.class);

    private List<Teacher> teacherExpectedList = new ArrayList();
    private List<Student> studentExpectedList = new ArrayList();

    @Before
    public void init() {
        Teacher teacher = new Teacher();
        teacher.setIdTeacher(1L);
        teacher.setFirstName("Vlad");
        teacher.setLastName("Vasilev");
        teacher.setEmail("Vlados@gmail.com");
        teacherExpectedList.add(teacher);
    }
    @Test
    public void findTeacherTest() {
        UniversityDao universityDao = new UniversityDao();
        List<Teacher> resultList = universityDao.findTeacher();
        Assert.assertEquals(teacherExpectedList, resultList);
        LOGGER.info("Expected: " + teacherExpectedList + "\n" + "Result: " + resultList);
    }

    @Test
    public  void findStudentsTest() {
        UniversityDao universityDao = new UniversityDao();
        List<Student> expectedList = universityDao.createStudents();
        List<Student> resultList = universityDao.findStudents();
        System.out.println(expectedList.toString());
        System.out.println(resultList.toString());
//        if (expectedList.equals(resultList)) {
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }
        Assert.assertEquals(expectedList, resultList);
        LOGGER.info("Expected: " + expectedList + "\n" + "Result: " + resultList);
    }

    @Test
    public void findAllTest() {
        UniversityDao universityDao = new UniversityDao();
        List result = universityDao.findAll();
//        Assert.assertEquals(studentExpectedList,result);
//        System.out.println(students);
    }
}