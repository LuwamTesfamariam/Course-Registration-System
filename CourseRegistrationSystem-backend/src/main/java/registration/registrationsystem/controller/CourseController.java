package registration.registrationsystem.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import registration.registrationsystem.service.ICourseService;
import registration.registrationsystem.service.dto.CourseDto;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final ICourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses(){
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getRegistrationEvent(@PathVariable long id){
        return new ResponseEntity<>(courseService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody CourseDto courseDto){
        courseService.save(courseDto);
        return ResponseEntity.ok("Successfully saved!");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody CourseDto courseDto){
        courseService.update(courseDto);
        return ResponseEntity.ok("Successfully updated!");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        courseService.delete(id);
        return ResponseEntity.ok("Successfully deleted!");
    }
}
