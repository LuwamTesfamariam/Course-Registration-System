package registration.registrationsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registration.registrationsystem.Util.ListMapper;
import registration.registrationsystem.domain.Registration;
import registration.registrationsystem.repository.RegistrationRepository;
import registration.registrationsystem.service.dto.RegistrationResponseDto;

import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    ListMapper<Registration, RegistrationResponseDto> listMapper;

    public List<RegistrationResponseDto> findAllById( long studentId) {
        return (List<RegistrationResponseDto>) listMapper.mapListToDto(registrationRepository.getRegistrationByStudent_Id(studentId), new RegistrationResponseDto());
    }
}
