package registration.registrationsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registration.registrationsystem.service.IFacultyService;
import registration.registrationsystem.service.dto.FacultyDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/faculties")
public class FacultyController {


    private final IFacultyService facultyService;

    @GetMapping
    public ResponseEntity<List<FacultyDto>> getAll(){
        return new ResponseEntity<>(facultyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDto> getFaculty(@PathVariable long id){
        return new ResponseEntity<>(facultyService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody FacultyDto facultyDto){
        facultyService.save(facultyDto);
        return ResponseEntity.ok("Successfully saved!");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody FacultyDto facultyDto){
        facultyService.update(facultyDto);
        return ResponseEntity.ok("Successfully updated!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        facultyService.delete(id);
        return ResponseEntity.ok("Successfully deleted!");
    }
}
