package domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "groups")
public class Groups   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private Long idGroup;
    @Column(name = "name_group")
    private String nameGroup;
    @OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    private Teacher teacher;
    @OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY,mappedBy = "group")
    private List<Student> studentList;

    public Long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "idGroup=" + idGroup +
                ", nameGroup='" + nameGroup + '\'' +
                ", studentList=" + studentList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Groups groups = (Groups) o;
        return Objects.equals(idGroup, groups.idGroup) &&
                Objects.equals(nameGroup, groups.nameGroup);

    }

    @Override
    public int hashCode() {
        return Objects.hash(idGroup, nameGroup);
    }
}
