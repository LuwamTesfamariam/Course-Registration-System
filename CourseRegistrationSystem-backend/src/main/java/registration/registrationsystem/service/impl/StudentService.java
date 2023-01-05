package registration.registrationsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registration.registrationsystem.domain.Address;
import registration.registrationsystem.domain.Student;
import registration.registrationsystem.repository.AddressRepository;
import registration.registrationsystem.repository.RegistrationEventRepository;
import registration.registrationsystem.repository.StudentRepository;
import registration.registrationsystem.service.IStudentService;
import registration.registrationsystem.Util.ListMapper;
import registration.registrationsystem.service.dto.StudentDto;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    @Autowired
    RegistrationEventRepository registrationEventRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;


    @Autowired
    ListMapper<Student, StudentDto> listMapper;

    @Override
    public List<StudentDto> findAll() {
        List<Student> students = studentRepository.findAll();
//        return students
//                .stream()
//                .map(s -> modelMapper.map(s, StudentDto.class))
//                .collect(Collectors.toList());
        return (List<StudentDto>) listMapper.mapListToDto(students, new StudentDto());
    }

    @Override
    public StudentDto findById(long id) {
//        return modelMapper.map(studentRepository.findById(id).get(), StudentDto.class);
        return listMapper.mapClassToDto(studentRepository.findById(id).get(), new StudentDto());
    }

    @Override
    public void save(StudentDto studentDto) {
        studentRepository.save(listMapper.mapClassFromDto(studentDto, new Student()));
    }

    @Override
    public void update(StudentDto studentDto) {
        Student studentToBeUpdated = studentRepository.findById(studentDto.getId()).get();
        studentToBeUpdated.setEmail(studentDto.getEmail());
        studentToBeUpdated.setName(studentDto.getName());
        studentToBeUpdated.setStudentId(studentDto.getStudentId());
        studentRepository.save(studentToBeUpdated);
    }

    @Override
    public void deleteById(long id ) {
        studentRepository.deleteById(id);
    }
    @Override
    public void addAddress(long id, Address address, String type) {
        Student student = studentRepository.findById(id).get();
        if (type.equalsIgnoreCase("home"))
            student.setHomeAddress(address);
        else
            student.setMailingAddress(address);
        studentRepository.save(student);
    }
}
