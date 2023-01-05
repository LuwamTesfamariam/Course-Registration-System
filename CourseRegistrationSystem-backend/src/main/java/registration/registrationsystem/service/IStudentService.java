package registration.registrationsystem.service;

import registration.registrationsystem.domain.Address;
import registration.registrationsystem.domain.Student;
import registration.registrationsystem.service.dto.RegistrationEventDto;
import registration.registrationsystem.service.dto.StudentDto;

import java.util.List;

public interface IStudentService {

    List<StudentDto> findAll();
    StudentDto findById(long id);
    void save(StudentDto studentDto);
    void update(StudentDto studentDto);
    void deleteById(long id);
    public void addAddress(long id, Address address, String type);
}
