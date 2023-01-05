package registration.registrationsystem.service;

import registration.registrationsystem.service.dto.CourseDto;


import java.util.List;

public interface ICourseService {


    public void save(CourseDto courseDto);
    public void update(CourseDto courseDto);
    public List<CourseDto> findAll();
    public CourseDto findById(long id);
    void delete(long id);
}
