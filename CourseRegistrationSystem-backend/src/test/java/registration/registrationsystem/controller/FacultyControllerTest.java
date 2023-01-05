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
import registration.registrationsystem.service.dto.FacultyDto;
import registration.registrationsystem.service.impl.FacultyService;

public class FacultyControllerTest {


    @Test
    public void testGetAll() {
        FacultyService facultyService = mock(FacultyService.class);
        when(facultyService.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<FacultyDto>> actualAll = (new FacultyController(facultyService)).getAll();
        assertTrue(actualAll.hasBody());
        assertEquals(200, actualAll.getStatusCodeValue());
        verify(facultyService).findAll();
    }

    @Test
    public void testGetFaculty() {

        FacultyService facultyService = mock(FacultyService.class);
        when(facultyService.findById(anyLong())).thenReturn(new FacultyDto(123L, "Name", "jane.doe@example.org", "Dr"));
        ResponseEntity<FacultyDto> actualFaculty = (new FacultyController(facultyService)).getFaculty(123L);
        assertTrue(actualFaculty.hasBody());
        assertEquals(200, actualFaculty.getStatusCodeValue());
        verify(facultyService).findById(anyLong());
    }

    @Test
    public void testSave() {
        FacultyService facultyService = mock(FacultyService.class);
        doNothing().when(facultyService).save((FacultyDto) any());
        FacultyController facultyController = new FacultyController(facultyService);
        ResponseEntity<String> actualSaveResult = facultyController
                .save(new FacultyDto(123L, "Name", "jane.doe@example.org", "Dr"));
        assertEquals("Successfully saved!", actualSaveResult.getBody());
        assertEquals(200, actualSaveResult.getStatusCodeValue());
        verify(facultyService).save((FacultyDto) any());
    }

    @Test
    public void testUpdate() {
        FacultyService facultyService = mock(FacultyService.class);
        doNothing().when(facultyService).update((FacultyDto) any());
        FacultyController facultyController = new FacultyController(facultyService);
        ResponseEntity<String> actualUpdateResult = facultyController
                .update(new FacultyDto(123L, "Name", "jane.doe@example.org", "Dr"));
        assertEquals("Successfully updated!", actualUpdateResult.getBody());
        assertEquals(200, actualUpdateResult.getStatusCodeValue());
        verify(facultyService).update((FacultyDto) any());
    }

    @Test
    public void testDelete() {

        FacultyService facultyService = mock(FacultyService.class);
        doNothing().when(facultyService).delete(anyLong());
        ResponseEntity<String> actualDeleteResult = (new FacultyController(facultyService)).delete(123L);
        assertEquals("Successfully deleted!", actualDeleteResult.getBody());
        assertEquals(200, actualDeleteResult.getStatusCodeValue());
        verify(facultyService).delete(anyLong());
    }
}

