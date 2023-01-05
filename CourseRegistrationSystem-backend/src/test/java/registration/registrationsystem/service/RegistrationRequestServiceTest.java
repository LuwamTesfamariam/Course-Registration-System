package registration.registrationsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import registration.registrationsystem.domain.*;
import registration.registrationsystem.repository.RegistrationEventRepository;
import registration.registrationsystem.repository.RegistrationRequestRepository;
import registration.registrationsystem.repository.StudentRepository;
import registration.registrationsystem.service.impl.RegistrationRequestService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RegistrationRequestServiceTest {
    @Mock
    StudentRepository studentRepository;
    @Mock
    RegistrationEventRepository registrationEventRepository;
    @Mock
    RegistrationRequestRepository registrationRequestRepository;
    @InjectMocks
    RegistrationRequestService registrationRequestService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void saveTest_whenRegistrationEventIsClosed(){
        String expectedResult = "Registration Event is not opened";
        Student student = Student
                .builder()
                .id(1L)
                .studentId("615660")
                .name("Robert")
                .email("robert@miu.edu")
                .build();
        RegistrationEvent registrationEvent = RegistrationEvent
                .builder()
                .startDateTime(LocalDateTime.of(2022,12,17,0,0,0))
                .endDateTime(LocalDateTime.of(2022,12,18,0,0,0))
                .build();
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(registrationEventRepository.findFirstByOrderByStartDateTimeDesc()).thenReturn(registrationEvent);
        ResponseEntity<String> result = registrationRequestService.save(List.of(), 1L);
        assertEquals(ResponseEntity.ok().build().getStatusCode(), result.getStatusCode());
    }


    @Test
    void saveTest_whenRegistrationEventIsOpenAndCourseRequestedIsOffered(){
        String expectedResult = "Successfully saved!";
        Student student = Student
                .builder()
                .id(1L)
                .studentId("615660")
                .name("Robert")
                .email("robert@miu.edu")
                .build();
        CourseOffering courseOffering = CourseOffering
                .builder()
                .id(1L)
                .build();
        AcademicBlock academicBlock = AcademicBlock
                .builder()
                .courseOfferings(List.of(courseOffering))
                .build();
        RegistrationGroup registrationGroup =
                RegistrationGroup
                        .builder()
                        .academicBlocks(List.of(academicBlock))
                        .build();
        RegistrationEvent registrationEvent = RegistrationEvent
                .builder()
                .startDateTime(LocalDateTime.now().minusDays(1))
                .endDateTime(LocalDateTime.now().plusDays(1))
                .registrationGroups(List.of(registrationGroup))
                .build();

        RegistrationRequest registrationRequest = RegistrationRequest
                .builder()
                .courseOffering(courseOffering)
                .build();
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(registrationEventRepository.findFirstByOrderByStartDateTimeDesc()).thenReturn(registrationEvent);
        ResponseEntity<String> result = registrationRequestService.save(List.of(registrationRequest), 1L);
        assertEquals(ResponseEntity.ok().build().getStatusCode(), result.getStatusCode());
    }
    @Test
    void saveTest_whenRegistrationEventIsOpenAndCourseRequestedIsNotOffered(){
        String expectedResult = "Registration Event is not opened";
        Student student = Student
                .builder()
                .id(1L)
                .studentId("615660")
                .name("Robert")
                .email("robert@miu.edu")
                .build();
        CourseOffering courseOffering = CourseOffering
                .builder()
                .id(1L)
                .build();
        AcademicBlock academicBlock = AcademicBlock
                .builder()
                .courseOfferings(List.of(courseOffering))
                .build();
        RegistrationGroup registrationGroup =
                RegistrationGroup
                        .builder()
                        .academicBlocks(List.of(academicBlock))
                        .build();
        RegistrationEvent registrationEvent = RegistrationEvent
                .builder()
                .startDateTime(LocalDateTime.now().minusDays(1))
                .endDateTime(LocalDateTime.now().plusDays(1))
                .registrationGroups(List.of(registrationGroup))
                .build();

        RegistrationRequest registrationRequest = RegistrationRequest
                .builder()
                .courseOffering(CourseOffering
                        .builder()
                        .id(2L)
                        .build())
                .build();
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(registrationEventRepository.findFirstByOrderByStartDateTimeDesc()).thenReturn(registrationEvent);
        ResponseEntity<String> result = registrationRequestService.save(List.of(registrationRequest), 1L);
        assertEquals(ResponseEntity.ok().build().getStatusCode(), result.getStatusCode());
//        assertEquals(expectedResult, result.getBody());
    }
}