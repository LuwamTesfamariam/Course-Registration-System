package registration.registrationsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import registration.registrationsystem.Util.ListMapper;
import registration.registrationsystem.domain.*;
import registration.registrationsystem.repository.*;
import registration.registrationsystem.service.dto.RegistrationEventDto;
import registration.registrationsystem.service.impl.RegistrationEventService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
class RegistrationEventServiceTest {
    @Mock
    StudentRepository studentRepository;

    @Mock
    RegistrationEventRepository registrationEventRepository;

    @Mock
    RegistrationRepository registrationRepository;

    @Mock
    CourseOfferingRepository courseOfferingRepository;

    @Mock
    ListMapper<RegistrationEvent, RegistrationEventDto> listMapper;

    @InjectMocks
    RegistrationEventService registrationEventService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void updateTest() {
        RegistrationEvent registrationEvent= RegistrationEvent
                .builder()
                .id(1L)
                .build();
        RegistrationEventDto registrationEventDto = RegistrationEventDto
                .builder()
                .id(1L)
                .startDateTime(LocalDateTime.of(2022, 2, 28,0, 0))
                .endDateTime(LocalDateTime.of(2022, 1, 29, 0, 0, 0))
                .build();
        when(registrationEventRepository.findById(1L)).thenReturn(Optional.of(registrationEvent));
        registrationEventService.update(registrationEventDto);
        verify(registrationEventRepository, times(1)).save(registrationEvent);
    }

    @Test
    public void getLatestRegistrationEventTest(){
        RegistrationEvent registrationEvent = RegistrationEvent
                .builder()
                .startDateTime(LocalDateTime.of(2022, 12, 28,0, 0))
                .endDateTime(LocalDateTime.of(2022, 12, 29, 0, 0, 0))
                .build();
        RegistrationEventDto registrationEventDto = RegistrationEventDto
                .builder()
                .build();
        when(registrationEventRepository.findFirstByOrderByStartDateTimeDesc()).thenReturn(registrationEvent);
        when(listMapper.mapClassToDto(registrationEvent, new RegistrationEventDto())).thenReturn(registrationEventDto);
        RegistrationEventDto result = registrationEventService.getLatestRegistrationEvent();
        assertEquals(registrationEventDto, result);
    }

    @Test
    public void getRegistrationEvenByStudentIdTest(){
        RegistrationEvent registrationEvent = RegistrationEvent
                .builder()
                .id(1L)
                .startDateTime(LocalDateTime.of(2022, 12, 20,0, 0))
                .endDateTime(LocalDateTime.of(2022, 12, 23, 0, 0, 0))
                .build();
        when(registrationEventRepository.getRegistrationEvenByStudentId(1L)).thenReturn(List.of(registrationEvent));
        List<RegistrationEvent> events = registrationEventService.getRegistrationEventByStudentId(1L);
        verify(registrationEventRepository, times(1)).getRegistrationEvenByStudentId(1L);
    }

    @Test
    void findById() {
        RegistrationEvent registrationEvent = RegistrationEvent.builder().build();
        RegistrationEventDto registrationEventDto = RegistrationEventDto
                .builder()
                .id(1L)
                .startDateTime(LocalDateTime.of(2022, 12, 28,0, 0))
                .endDateTime(LocalDateTime.of(2022, 12, 29, 0, 0, 0))
                .build();
        when(registrationEventRepository.findById(1L)).thenReturn(Optional.of(registrationEvent));
        when(listMapper.mapClassToDto(registrationEvent, new RegistrationEventDto())).thenReturn(registrationEventDto);
        RegistrationEventDto result = registrationEventService.findById(1L);
        assertEquals(registrationEventDto, result);
    }

    @Test
    void findAll() {
        RegistrationEvent registrationEvent = RegistrationEvent
                .builder()
                .startDateTime(LocalDateTime.of(2022, 12, 28,0, 0))
                .endDateTime(LocalDateTime.of(2022, 12, 29, 0, 0, 0))
                .build();
        RegistrationEventDto registrationEventDto = RegistrationEventDto.builder().build();
        when(registrationEventRepository.findAll()).thenReturn(List.of(registrationEvent));
        when(listMapper.mapClassToDto(registrationEvent, new RegistrationEventDto())).thenReturn(registrationEventDto);
        List<RegistrationEventDto> result = registrationEventService.findAll();
        assertEquals(1, result.size());
        assertEquals(registrationEventDto.getId(), result.get(0).getId());
    }

    @Test
    public void processRegistrationTestWhenItIsProcessed(){
        CourseOffering courseOffering = CourseOffering
                .builder()
                .id(1L)
                .availableSeats(26)
                .capacity(30)
                .build();
        RegistrationRequest registrationRequest = RegistrationRequest
                .builder()
                .id(1L)
                .courseOffering(courseOffering)
                .build();
        Student student = Student
                .builder()
                .id(1L)
                .studentId("615660")
                .name("Robert")
                .email("robert@miu.edu")
                .registrationRequests(List.of(registrationRequest))
                .build();
        AcademicBlock academicBlock = AcademicBlock
                .builder()
                .courseOfferings(List.of(courseOffering))
                .build();
        RegistrationGroup registrationGroup =
                RegistrationGroup
                        .builder()
                        .academicBlocks(List.of(academicBlock))
                        .students(List.of(student))
                        .build();
        RegistrationEvent registrationEvent = RegistrationEvent
                .builder()
                .startDateTime(LocalDateTime.now().minusDays(1))
                .endDateTime(LocalDateTime.now().plusDays(1))
                .registrationGroups(List.of(registrationGroup))
                .build();
        Registration registration = Registration
                .builder()
                .student(student)
                .courseOffering(courseOffering)
                .build();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(registrationEventRepository.findFirstByOrderByStartDateTimeDesc()).thenReturn(registrationEvent);
        when(registrationRepository.findAll()).thenReturn(List.of(registration));
//        when(courseOfferingRepository.updateAvailableSeats(26))
//        registrationEventService.processRegistrations(1L);
//        verify(registrationRepository, times(1)).save(registration);
    }

}