package client;

import lombok.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.annotation.Id;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class Student {
    @Id
//    @GeneratedValue
    long id;
    String studentId;
    String name;
    String email;
    public Student(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
