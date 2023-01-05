package registration.registrationsystem.service;

import org.springframework.http.ResponseEntity;
import registration.registrationsystem.domain.RegistrationRequest;

import java.util.List;

public interface IRegistrationRequestService {
    ResponseEntity<String> save(List<RegistrationRequest> registrationRequests, long studentId);

}
