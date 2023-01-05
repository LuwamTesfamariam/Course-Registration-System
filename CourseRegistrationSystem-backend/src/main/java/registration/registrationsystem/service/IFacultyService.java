package registration.registrationsystem.service;

import registration.registrationsystem.service.dto.FacultyDto;

import java.util.List;

public interface IFacultyService {
    List<FacultyDto> findAll();
    FacultyDto findById(long id);
    void save(FacultyDto facultyDto);
    void update(FacultyDto facultyDto);
    void delete(long id);
}
