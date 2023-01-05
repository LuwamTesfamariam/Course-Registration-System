package registration.registrationsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registration.registrationsystem.domain.Course;
import registration.registrationsystem.repository.CourseRepository;
import registration.registrationsystem.service.ICourseService;
import registration.registrationsystem.service.dto.CourseDto;
import registration.registrationsystem.Util.ListMapper;

import java.util.List;

@Service

public class CourseService implements ICourseService {



    @Autowired
    private  CourseRepository courseRepository;


    @Autowired
    private ListMapper<Course, CourseDto> listMapper;


    @Override
    public void save(CourseDto courseDto) {
        courseRepository.save(listMapper.mapClassFromDto(courseDto, new Course()));
    }


    @Override
    public void update(CourseDto courseDto) {
        Course courseUpdate = courseRepository.findById(courseDto.getId()).get();
        courseUpdate.setId(courseDto.getId());
        courseUpdate.setCode(courseDto.getCode());
        courseUpdate.setName(courseDto.getName());
        courseRepository.save(courseUpdate);

    }

    @Override
    public List<CourseDto> findAll() {
        List<Course> courses = courseRepository.findAll();
        return (List<CourseDto>) listMapper.mapListToDto(courses, new CourseDto());
    }

    @Override
    public CourseDto findById(long id) {
        return listMapper.mapClassToDto(courseRepository.findById(id).get(), new CourseDto());
    }

    @Override
    public void delete(long id) {
        courseRepository.deleteById(id);
    }

//    @Override
//    public List<CourseDto> findAll() {
////        List<Course> accountList = courseRepository.findAll();
////        return courseAdapter.getCourseDTOListFromCourseList(courseList);
//    }

//    @Override
//    public CourseDto findById(long id) {
////        Course course = courseRepository.findById(id).get();
////        return courseAdapter.getCourseDTOFromCourse(course);;
//    }
}
