package client;

import java.util.Collection;
import java.util.List;

public class Students {
    private Collection<Student> students;
    public Students(){}

    public Students(Collection<Student> students) {
        this.students = students;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Students{" +
                "students=" + students +
                '}';
    }
}
