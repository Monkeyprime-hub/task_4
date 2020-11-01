package domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private Long idCourse;
    @Column(name = "course_title")
    private String courseTitle;

    @OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    private Teacher teacher;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "course")
    private List<Student> studentList;

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(idCourse, course.idCourse) &&
                Objects.equals(courseTitle, course.courseTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCourse, courseTitle);
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + idCourse +
                ", courseTitle='" + courseTitle + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
