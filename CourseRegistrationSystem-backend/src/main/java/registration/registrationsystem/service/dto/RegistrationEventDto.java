package registration.registrationsystem.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationEventDto {
    private long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String status;
}
