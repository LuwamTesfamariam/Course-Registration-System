package registration.registrationsystem.service.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicBlockDto {
    @Id
//    @GeneratedValue
    private long id;
    private String code;
    private String semester;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
