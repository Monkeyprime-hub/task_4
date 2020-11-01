package domain;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "marks")
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mark")
    private Long idMark;
    @Column(name = "mark")
    private String mark;
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_student")
    private Student student;

    public Marks() {
    }

    public Long getIdMark() {
        return idMark;
    }

    public void setIdMark(Long idMark) {
        this.idMark = idMark;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marks marks = (Marks) o;
        return Objects.equals(idMark, marks.idMark) &&
                Objects.equals(mark, marks.mark) &&
                Objects.equals(student, marks.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMark, mark, student);
    }

    @Override
    public String toString() {
        return "Marks{" +
                "idMark=" + idMark +
                ", mark='" + mark + '\'' +
                ", student=" + student +
                '}';
    }
}
