package registration.registrationsystem.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import registration.registrationsystem.domain.RegistrationEvent;
import registration.registrationsystem.service.dto.RegistrationEventDto;
import registration.registrationsystem.service.impl.RegistrationEventService;

public class RegistrationEventControllerTest {

    @Test
    public void testGetLatestRegistrationEvent() {


        RegistrationEventService registrationEventService = mock(RegistrationEventService.class);
        when(registrationEventService.getLatestRegistrationEvent()).thenReturn(new RegistrationEventDto());
        ResponseEntity<RegistrationEventDto> actualLatestRegistrationEvent = (new RegistrationEventController(
                registrationEventService)).getLatestRegistrationEvent();
        assertTrue(actualLatestRegistrationEvent.hasBody());
        assertTrue(actualLatestRegistrationEvent.getHeaders().isEmpty());
        assertEquals(200, actualLatestRegistrationEvent.getStatusCodeValue());
        verify(registrationEventService).getLatestRegistrationEvent();
    }


    @Test
    public void testGetAllRegistrationEvents() {

        RegistrationEventService registrationEventService = mock(RegistrationEventService.class);
        when(registrationEventService.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<RegistrationEventDto>> actualAllRegistrationEvents = (new RegistrationEventController(
                registrationEventService)).getAllRegistrationEvents();
        assertTrue(actualAllRegistrationEvents.hasBody());
        assertEquals(200, actualAllRegistrationEvents.getStatusCodeValue());
        assertTrue(actualAllRegistrationEvents.getHeaders().isEmpty());
        verify(registrationEventService).findAll();
    }


    @Test
    public void testSend() {


        RegistrationEventService registrationEventService = mock(RegistrationEventService.class);
        doNothing().when(registrationEventService).sendEmail(anyLong());
        (new RegistrationEventController(registrationEventService)).send(123L);
        verify(registrationEventService).sendEmail(anyLong());
    }

    @Test
    public void testGetRegistrationEvent2() {

        RegistrationEventService registrationEventService = mock(RegistrationEventService.class);
        when(registrationEventService.findById(anyLong())).thenReturn(new RegistrationEventDto());
        ResponseEntity<RegistrationEventDto> actualRegistrationEvent = (new RegistrationEventController(
                registrationEventService)).getRegistrationEvent(123L);
        assertTrue(actualRegistrationEvent.hasBody());
        assertTrue(actualRegistrationEvent.getHeaders().isEmpty());
        assertEquals(200, actualRegistrationEvent.getStatusCodeValue());
        verify(registrationEventService).findById(anyLong());
    }



    @Test
    public void testSave() {


        RegistrationEventService registrationEventService = mock(RegistrationEventService.class);
        doNothing().when(registrationEventService).save((RegistrationEventDto) any());
        RegistrationEventController registrationEventController = new RegistrationEventController(
                registrationEventService);
        ResponseEntity<String> actualSaveResult = registrationEventController.save(new RegistrationEventDto());
        assertEquals("Successfully saved!", actualSaveResult.getBody());
        assertEquals(200, actualSaveResult.getStatusCodeValue());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        verify(registrationEventService).save((RegistrationEventDto) any());
    }


    @Test
    public void testUpdate2() {

        RegistrationEventService registrationEventService = mock(RegistrationEventService.class);
        doNothing().when(registrationEventService).update((RegistrationEventDto) any());
        RegistrationEventController registrationEventController = new RegistrationEventController(
                registrationEventService);
        ResponseEntity<String> actualUpdateResult = registrationEventController.update(new RegistrationEventDto());
        assertEquals("Successfully updated!", actualUpdateResult.getBody());
        assertEquals(200, actualUpdateResult.getStatusCodeValue());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(registrationEventService).update((RegistrationEventDto) any());
    }

    @Test
    public void testGetRegistrationEvenByStudentId2() {

        RegistrationEventService registrationEventService = mock(RegistrationEventService.class);
        when(registrationEventService.getRegistrationEventByStudentId(anyLong())).thenReturn(new ArrayList<>());
        ResponseEntity<List<RegistrationEvent>> actualRegistrationEvenByStudentId = (new RegistrationEventController(
                registrationEventService)).getRegistrationEventByStudentId(123L);
        assertTrue(actualRegistrationEvenByStudentId.hasBody());
        assertEquals(200, actualRegistrationEvenByStudentId.getStatusCodeValue());
        assertTrue(actualRegistrationEvenByStudentId.getHeaders().isEmpty());
        verify(registrationEventService).getRegistrationEventByStudentId(anyLong());
    }

    @Test
    public void testProcessRegistrations3() {

        RegistrationEventService registrationEventService = mock(RegistrationEventService.class);
        when(registrationEventService.processRegistrations(anyLong())).thenReturn("Process Registrations");
        ResponseEntity<String> actualProcessRegistrationsResult = (new RegistrationEventController(
                registrationEventService)).processRegistrations(123L, true);
        assertEquals("Process Registrations", actualProcessRegistrationsResult.getBody());
        assertEquals(200, actualProcessRegistrationsResult.getStatusCodeValue());
        assertTrue(actualProcessRegistrationsResult.getHeaders().isEmpty());
        verify(registrationEventService).processRegistrations(anyLong());
    }
}

