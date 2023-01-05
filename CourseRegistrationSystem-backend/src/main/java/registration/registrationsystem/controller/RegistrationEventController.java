package registration.registrationsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registration.registrationsystem.domain.RegistrationEvent;
import registration.registrationsystem.service.IRegistrationEventService;
import registration.registrationsystem.service.dto.RegistrationEventDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration-events")
public class RegistrationEventController {

   private final IRegistrationEventService registrationEventService;

    @GetMapping("/latest")
    public ResponseEntity<RegistrationEventDto> getLatestRegistrationEvent(){
        return new ResponseEntity<>(registrationEventService.getLatestRegistrationEvent(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RegistrationEventDto>> getAllRegistrationEvents(){
        return new ResponseEntity<>(registrationEventService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/sendEmail/{id}")
    public void send(@PathVariable long id){
        registrationEventService.sendEmail(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationEventDto> getRegistrationEvent(@PathVariable long id){
        return new ResponseEntity<>(registrationEventService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody RegistrationEventDto eventDto){
        registrationEventService.save(eventDto);
        return ResponseEntity.ok("Successfully saved!");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody RegistrationEventDto eventDto){
        registrationEventService.update(eventDto);
        return ResponseEntity.ok("Successfully updated!");
    }

    @GetMapping("/byStudent/{studentId}")
    public ResponseEntity<List<RegistrationEvent>> getRegistrationEventByStudentId(@PathVariable long studentId){
        return new ResponseEntity<>(registrationEventService.getRegistrationEventByStudentId(studentId), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> processRegistrations(@PathVariable long id, @RequestParam boolean processed ){
        System.out.println("begin process...");
        if(processed){
            return new ResponseEntity<>(registrationEventService.processRegistrations(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Registration cannot be processed!", HttpStatus.OK);
        }
    }
}
