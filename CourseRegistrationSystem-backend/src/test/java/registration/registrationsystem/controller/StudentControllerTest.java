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
import registration.registrationsystem.domain.Address;
import registration.registrationsystem.service.dto.StudentDto;
import registration.registrationsystem.service.impl.StudentService;

public class StudentControllerTest {

    @Test
    public void testGetAll() {


        StudentService studentService = mock(StudentService.class);
        when(studentService.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<StudentDto>> actualAll = (new StudentController(studentService)).getAll();
        assertTrue(actualAll.hasBody());
        assertEquals(200, actualAll.getStatusCodeValue());
        assertTrue(actualAll.getHeaders().isEmpty());
        verify(studentService).findAll();
    }

    @Test
    public void testGetById() {

        StudentService studentService = mock(StudentService.class);
        when(studentService.findById(anyLong())).thenReturn(new StudentDto(123L, "42", "Name", "jane.doe@example.org"));
        ResponseEntity<StudentDto> actualById = (new StudentController(studentService)).getById(123L);
        assertTrue(actualById.hasBody());
        assertTrue(actualById.getHeaders().isEmpty());
        assertEquals(200, actualById.getStatusCodeValue());
        verify(studentService).findById(anyLong());
    }


    @Test
    public void testSave() {


        StudentService studentService = mock(StudentService.class);
        doNothing().when(studentService).save((StudentDto) any());
        StudentController studentController = new StudentController(studentService);
        ResponseEntity<String> actualSaveResult = studentController
                .save(new StudentDto(123L, "42", "Name", "jane.doe@example.org"));
        assertEquals("Successfully saved!", actualSaveResult.getBody());
        assertEquals(200, actualSaveResult.getStatusCodeValue());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        verify(studentService).save((StudentDto) any());
    }



    @Test
    public void testUpdate2() {

        StudentService studentService = mock(StudentService.class);
        doNothing().when(studentService).update((StudentDto) any());
        StudentController studentController = new StudentController(studentService);
        ResponseEntity<String> actualUpdateResult = studentController
                .update(new StudentDto(123L, "42", "Name", "jane.doe@example.org"));
        assertEquals("Successfully updated!", actualUpdateResult.getBody());
        assertEquals(200, actualUpdateResult.getStatusCodeValue());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(studentService).update((StudentDto) any());
    }


    @Test
    public void testDeleteById2() {

        StudentService studentService = mock(StudentService.class);
        doNothing().when(studentService).deleteById(anyLong());
        ResponseEntity<String> actualDeleteByIdResult = (new StudentController(studentService)).deleteById(123L);
        assertEquals("Successfully deleted!", actualDeleteByIdResult.getBody());
        assertEquals(200, actualDeleteByIdResult.getStatusCodeValue());
        assertTrue(actualDeleteByIdResult.getHeaders().isEmpty());
        verify(studentService).deleteById(anyLong());
    }


    @Test
    public void testUpdateAddress2() {

        StudentService studentService = mock(StudentService.class);
        doNothing().when(studentService).addAddress(anyLong(), (Address) any(), (String) any());
        StudentController studentController = new StudentController(studentService);
        ResponseEntity<String> actualUpdateAddressResult = studentController.updateAddress(123L,
                new Address(123L, "Street", "Oxford", "Postal Code", "MD", "us-east-2"), "Type");
        assertEquals("Address successfully saved!", actualUpdateAddressResult.getBody());
        assertEquals(200, actualUpdateAddressResult.getStatusCodeValue());
        assertTrue(actualUpdateAddressResult.getHeaders().isEmpty());
        verify(studentService).addAddress(anyLong(), (Address) any(), (String) any());
    }
}

