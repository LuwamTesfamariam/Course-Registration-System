package registration.registrationsystem.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
//    @GeneratedValue
    long id;
    @Column(unique = true)
    String code;
    String name;
    String description;

    @ManyToMany
    List<Course> prerequisites;

}


