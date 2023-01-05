package registration.registrationsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registration.registrationsystem.domain.CourseOffering;
import registration.registrationsystem.service.dto.AcademicBlockDto;
import registration.registrationsystem.service.impl.AcademicBlocksService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/academic-blocks")
public class AcademicBlocksController {

    private final AcademicBlocksService academicBlocksService;
    @PostMapping
    public ResponseEntity<String> save (@RequestBody AcademicBlockDto academicBlockDto){
        academicBlocksService.saveBlocks(academicBlockDto);
        return  ResponseEntity.ok("Successfully saved!");
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody AcademicBlockDto academicBlockDto){
        academicBlocksService.updateBlocks(academicBlockDto);
        return new ResponseEntity<AcademicBlockDto>(academicBlockDto, HttpStatus.OK);
    }

    @PutMapping("/addCourseOffering/{id}")
    public ResponseEntity<?> addCourseOffering(@RequestBody CourseOffering courseOffering, @PathVariable long id){
        academicBlocksService.updateCourseOffering(courseOffering, id);
        return new ResponseEntity<>(courseOffering, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<AcademicBlockDto> academicBlockDtos = academicBlocksService.findAllBlocks();
        return new ResponseEntity<>(academicBlockDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable   long id){
        AcademicBlockDto academicBlockDto = academicBlocksService.findBlock(id);
        return new ResponseEntity<>(academicBlockDto, HttpStatus.OK);
    }
}
