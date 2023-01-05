package registration.registrationsystem.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequest {

    @Id
//    @GeneratedValue
    private long id;
    private int priorityNumber;
    private boolean processed;

    @OneToOne
    CourseOffering courseOffering;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId")
    Student student;
}
