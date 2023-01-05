package registration.registrationsystem.controller;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import registration.registrationsystem.service.dto.RegistrationResponseDto;
import registration.registrationsystem.service.impl.RegistrationService;

public class RegistrationControllerTest {

    @Test
    public void testGetAll2() {


        RegistrationService registrationService = mock(RegistrationService.class);
        ArrayList<RegistrationResponseDto> registrationResponseDtoList = new ArrayList<>();
        when(registrationService.findAllById(anyLong())).thenReturn(registrationResponseDtoList);
        List<RegistrationResponseDto> actualAll = (new RegistrationController(registrationService)).getAll(123L);
        assertSame(registrationResponseDtoList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(registrationService).findAllById(anyLong());
    }
}

