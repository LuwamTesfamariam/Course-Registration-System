package registration.registrationsystem.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registration.registrationsystem.Util.ListMapper;
import registration.registrationsystem.domain.Faculty;
import registration.registrationsystem.repository.FacultyRepository;
import registration.registrationsystem.service.IFacultyService;
import registration.registrationsystem.service.dto.FacultyDto;

import java.util.List;

@Service
public class FacultyService implements IFacultyService {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    ListMapper<Faculty, FacultyDto> listMapper;

    @Override
    public List<FacultyDto> findAll() {
        List<Faculty> faculties = facultyRepository.findAll();
        return (List<FacultyDto>) listMapper.mapListToDto(faculties, new FacultyDto());
    }

    @Override
    public FacultyDto findById(long id) {
        return listMapper.mapClassToDto(facultyRepository.findById(id).get(), new FacultyDto());
    }

    @Override
    public void save(FacultyDto facultyDto) {
        facultyRepository.save(listMapper.mapClassFromDto(facultyDto, new Faculty()));
    }

    @Override
    public void update(FacultyDto facultyDto) {
        Faculty facultyToBeUpdated = facultyRepository.findById(facultyDto.getId()).get();
        facultyToBeUpdated.setEmail(facultyDto.getEmail());
        facultyToBeUpdated.setName(facultyDto.getName());
        facultyToBeUpdated.setTitle(facultyDto.getTitle());
        facultyRepository.save(facultyToBeUpdated);
    }

    @Override
    public void delete(long id) {
        facultyRepository.deleteById(id);
    }
}
