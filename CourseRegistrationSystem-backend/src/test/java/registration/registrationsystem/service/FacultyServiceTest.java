package registration.registrationsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import registration.registrationsystem.Util.ListMapper;
import registration.registrationsystem.domain.Faculty;
import registration.registrationsystem.repository.FacultyRepository;
import registration.registrationsystem.service.dto.FacultyDto;
import registration.registrationsystem.service.impl.FacultyService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
@RunWith(SpringRunner.class)
class FacultyServiceTest {

    @InjectMocks
    FacultyService facultyService;

    @Mock
    FacultyRepository facultyRepository;

    @Mock
    ListMapper<Faculty, FacultyDto> listMapper;


    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void findAll() {
        Faculty faculty = Faculty.builder().build();
        when(facultyRepository.findAll()).thenReturn(List.of(faculty));
        when(listMapper.mapListToDto(List.of(faculty), new FacultyDto())).thenReturn(List.of());
        List<FacultyDto> result = facultyService.findAll();
        assertEquals(0, result.size());
    }

    @Test
    void findById() {
        Faculty faculty = Faculty.builder().build();
        FacultyDto facultyDto = FacultyDto
                .builder()
                .id(1)
                .name("Sanad")
                .email("sanad@miu.edu")
                .title("assistant professor")
                .build();
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
        when(listMapper.mapClassToDto(faculty, new FacultyDto())).thenReturn(facultyDto);
        FacultyDto result = facultyService.findById(1L);
        assertEquals(facultyDto, result);

    }

    @Test
    void saveTest() {
        Faculty faculty = Faculty.builder().build();
        FacultyDto facultyDto = FacultyDto.builder().build();
        when(listMapper.mapClassFromDto(facultyDto, new Faculty())).thenReturn(faculty);
        facultyService.save(facultyDto);
        verify(facultyRepository, times(1)).save(faculty);

    }

    @Test
    void updateTest() {
        Faculty faculty = Faculty
                .builder()
                .id(1)
                .name("Lerman")
                .email("lerman@miu.edu")
                .title("professor")
                .build();
        FacultyDto facultyDto = FacultyDto.builder()
                .id(1)
                .name("Sanad")
                .email("sanad@miu.edu")
                .title("assistant professor")
                .build();

        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
        facultyService.update(facultyDto);
        verify(facultyRepository, times(1)).save(faculty);
        assertEquals(facultyDto.getName(), faculty.getName());
    }

    @Test
    void deleteTest() {
        doNothing().when(facultyRepository).deleteById(1L);
        facultyService.delete(1L);
        verify(facultyRepository, times(1)).deleteById(1L);
    }
}