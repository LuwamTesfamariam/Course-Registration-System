package registration.registrationsystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicBlock {
    @Id
//    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String code;
    private String semester;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "block_id")
    List<CourseOffering> courseOfferings;

}
