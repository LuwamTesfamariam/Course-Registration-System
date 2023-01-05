package registration.registrationsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import registration.registrationsystem.Util.ListMapper;
import registration.registrationsystem.domain.Registration;
import registration.registrationsystem.repository.RegistrationRepository;
import registration.registrationsystem.service.dto.RegistrationResponseDto;
import registration.registrationsystem.service.impl.RegistrationService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceTest {
    @Mock
    RegistrationRepository registrationRepository;

    @Mock
    ListMapper<Registration, RegistrationResponseDto> listMapper;

    @InjectMocks
    RegistrationService registrationService;

    @BeforeEach
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void findAllById(){
        Registration registration = Registration.builder().build();
        when(registrationRepository.getRegistrationByStudent_Id(1L)).thenReturn(List.of(registration));
        when(listMapper.mapListToDto(List.of(registration), new RegistrationResponseDto())).thenReturn(List.of());
        List<RegistrationResponseDto> registrations = registrationService.findAllById(1L);
        assertEquals(0, registrations.size());
    }
}