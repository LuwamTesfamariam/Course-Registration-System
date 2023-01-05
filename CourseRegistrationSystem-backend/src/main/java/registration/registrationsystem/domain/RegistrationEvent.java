package registration.registrationsystem.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationEvent {
    @Id
//    @GeneratedValue
    long id;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;

    @OneToMany(fetch = FetchType.EAGER)
    private List<RegistrationGroup> registrationGroups = new ArrayList<>();

    public RegistrationEvent(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
