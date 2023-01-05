package registration.registrationsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
//    @GeneratedValue
    long id;
    String studentId;
    String name;
    String email;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "student")
    @JsonIgnore
    private List<RegistrationRequest> registrationRequests = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address homeAddress;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address mailingAddress;

    public Student(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
