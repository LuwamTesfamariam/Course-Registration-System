package registration.registrationsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registration.registrationsystem.domain.Address;
import registration.registrationsystem.service.IStudentService;
import registration.registrationsystem.service.dto.RegistrationEventDto;
import registration.registrationsystem.service.dto.StudentDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {


   private final IStudentService studentService;

    @GetMapping()
    public ResponseEntity<List<StudentDto>> getAll(){
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable("id") long id){
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody StudentDto studentDto){
        studentService.save(studentDto);
        return ResponseEntity.ok("Successfully saved!");

    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody StudentDto studentDto){
        studentService.update(studentDto);
        return ResponseEntity.ok("Successfully updated!");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id){
        studentService.deleteById(id);
        return ResponseEntity.ok("Successfully deleted!");
    }
    @PutMapping("/addAddress/{id}")
    public ResponseEntity<String> updateAddress(@PathVariable long id, @RequestBody Address address, @RequestParam String type){
        studentService.addAddress(id, address, type);
        return ResponseEntity.ok("Address successfully saved!");
    }

}
