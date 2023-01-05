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
import registration.registrationsystem.service.dto.CourseDto;
import registration.registrationsystem.service.impl.CourseService;

public class CourseControllerTest {

    @Test
    public void testGetAllCourses() {


        CourseService courseService = mock(CourseService.class);
        when(courseService.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<CourseDto>> actualAllCourses = (new CourseController(courseService)).getAllCourses();
        assertTrue(actualAllCourses.hasBody());
        assertEquals(200, actualAllCourses.getStatusCodeValue());
        verify(courseService).findAll();
    }


    @Test
    public void testGetRegistrationEvent() {

        CourseService courseService = mock(CourseService.class);
        when(courseService.findById(anyLong()))
                .thenReturn(new CourseDto(123L, "Code", "Name", "The characteristics of someone or something"));
        ResponseEntity<CourseDto> actualRegistrationEven = (new CourseController(courseService))
                .getRegistrationEvent(123L);
        assertTrue(actualRegistrationEven.hasBody());
        assertEquals(200, actualRegistrationEven.getStatusCodeValue());
        verify(courseService).findById(anyLong());
    }



    @Test
    public void testSave() {

        CourseService courseService = mock(CourseService.class);
        doNothing().when(courseService).save((CourseDto) any());
        CourseController courseController = new CourseController(courseService);
        ResponseEntity<String> actualSaveResult = courseController
                .save(new CourseDto(123L, "Code", "Name", "The characteristics of someone or something"));
        assertEquals("Successfully saved!", actualSaveResult.getBody());
        assertEquals(200, actualSaveResult.getStatusCodeValue());
        verify(courseService).save((CourseDto) any());
    }


    @Test
    public void testUpdate() {

        CourseService courseService = mock(CourseService.class);
        doNothing().when(courseService).update((CourseDto) any());
        CourseController courseController = new CourseController(courseService);
        ResponseEntity<String> actualUpdateResult = courseController
                .update(new CourseDto(123L, "Code", "Name", "The characteristics of someone or something"));
        assertEquals("Successfully updated!", actualUpdateResult.getBody());
        assertEquals(200, actualUpdateResult.getStatusCodeValue());
        verify(courseService).update((CourseDto) any());
    }



    @Test
    public void testDelete() {

        CourseService courseService = mock(CourseService.class);
        doNothing().when(courseService).delete(anyLong());
        ResponseEntity<String> actualDeleteResult = (new CourseController(courseService)).delete(123L);
        assertEquals("Successfully deleted!", actualDeleteResult.getBody());
        assertEquals(200, actualDeleteResult.getStatusCodeValue());
        verify(courseService).delete(anyLong());
    }
}

