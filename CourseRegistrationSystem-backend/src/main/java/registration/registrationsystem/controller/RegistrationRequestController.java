package registration.registrationsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registration.registrationsystem.domain.RegistrationRequest;
import registration.registrationsystem.service.IRegistrationRequestService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration-requests")
public class RegistrationRequestController {


    private final IRegistrationRequestService requestService;

    @PostMapping("{studentId}")
    public ResponseEntity<String> saveRequest(@RequestBody List<RegistrationRequest> registrationRequests, @PathVariable long studentId) {
        return requestService.save(registrationRequests, studentId);
    }
}
