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


import org.junit.Test;
import org.springframework.http.ResponseEntity;
import registration.registrationsystem.domain.CourseOffering;
import registration.registrationsystem.service.dto.AcademicBlockDto;
import registration.registrationsystem.service.impl.AcademicBlocksService;

public class AcademicBlocksControllerTest {

    @Test
    public void testSave() {

        AcademicBlocksService academicBlocksService = mock(AcademicBlocksService.class);
        doNothing().when(academicBlocksService).saveBlocks((AcademicBlockDto) any());
        AcademicBlocksController academicBlocksController = new AcademicBlocksController(academicBlocksService);
        ResponseEntity<String> actualSaveResult = academicBlocksController.save(new AcademicBlockDto());
        assertEquals("Successfully saved!", actualSaveResult.getBody());
        verify(academicBlocksService).saveBlocks((AcademicBlockDto) any());
    }


    @Test
    public void testUpdate() {


        AcademicBlocksService academicBlocksService = mock(AcademicBlocksService.class);
        doNothing().when(academicBlocksService).updateBlocks((AcademicBlockDto) any());
        AcademicBlocksController academicBlocksController = new AcademicBlocksController(academicBlocksService);
        ResponseEntity<?> actualUpdateResult = academicBlocksController.update(new AcademicBlockDto());
        verify(academicBlocksService).updateBlocks((AcademicBlockDto) any());
    }


        @Test
    public void testAddCourseOffering() {

        AcademicBlocksService academicBlocksService = mock(AcademicBlocksService.class);
        doNothing().when(academicBlocksService).updateCourseOffering((CourseOffering) any(), anyLong());
        AcademicBlocksController academicBlocksController = new AcademicBlocksController(academicBlocksService);
        ResponseEntity<?> actualAddCourseOfferingResult = academicBlocksController.addCourseOffering(new CourseOffering(),
                123L);
        verify(academicBlocksService).updateCourseOffering((CourseOffering) any(), anyLong());
    }


    @Test
    public void testGetAll() {

        AcademicBlocksService academicBlocksService = mock(AcademicBlocksService.class);
        when(academicBlocksService.findAllBlocks()).thenReturn(new ArrayList<>());
        ResponseEntity<?> actualAll = (new AcademicBlocksController(academicBlocksService)).getAll();
        verify(academicBlocksService).findAllBlocks();
    }


    @Test
    public void testGetById() {

        AcademicBlocksService academicBlocksService = mock(AcademicBlocksService.class);
        when(academicBlocksService.findBlock(anyLong())).thenReturn(new AcademicBlockDto());
        ResponseEntity<?> actualById = (new AcademicBlocksController(academicBlocksService)).getById(123L);
        verify(academicBlocksService).findBlock(anyLong());
    }
}

