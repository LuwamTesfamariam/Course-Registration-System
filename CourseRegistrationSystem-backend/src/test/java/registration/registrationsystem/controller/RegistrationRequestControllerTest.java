package registration.registrationsystem.controller;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import registration.registrationsystem.domain.RegistrationRequest;
import registration.registrationsystem.service.IRegistrationRequestService;

public class RegistrationRequestControllerTest {

    @Test
    public void testSaveRequest() {

        IRegistrationRequestService iRegistrationRequestService = mock(IRegistrationRequestService.class);
        when(iRegistrationRequestService.save((List<RegistrationRequest>) any(), anyLong())).thenReturn(null);
        RegistrationRequestController registrationRequestController = new RegistrationRequestController(
                iRegistrationRequestService);
        assertNull(registrationRequestController.saveRequest(new ArrayList<>(), 123L));
        verify(iRegistrationRequestService).save((List<RegistrationRequest>) any(), anyLong());
    }
}

