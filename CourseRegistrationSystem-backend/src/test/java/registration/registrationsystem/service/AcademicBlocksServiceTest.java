package registration.registrationsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import registration.registrationsystem.Util.ListMapper;
import registration.registrationsystem.domain.AcademicBlock;
import registration.registrationsystem.domain.Faculty;
import registration.registrationsystem.repository.AcademicBlockRepository;
import registration.registrationsystem.service.dto.AcademicBlockDto;
import registration.registrationsystem.service.dto.FacultyDto;
import registration.registrationsystem.service.impl.AcademicBlocksService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class AcademicBlocksServiceTest {

    @Mock
    ListMapper<AcademicBlock, AcademicBlockDto> listMapper;

    @Mock
    AcademicBlockRepository academicBlockRepository;

    @InjectMocks
    AcademicBlocksService academicBlocksService;

    @BeforeEach
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void updateBlocksTest(){
        AcademicBlock academicBlock = AcademicBlock
                .builder()
                .id(1)
                .code("AUG")
                .build();
        AcademicBlockDto academicBlockDto = AcademicBlockDto
                .builder()
                .id(1)
                .code("SEP")
                .build();
        when(academicBlockRepository.findById(1L)).thenReturn(Optional.of(academicBlock));
        academicBlocksService.updateBlocks(academicBlockDto);
        verify(academicBlockRepository, times(1)).save(academicBlock);
        assertEquals(academicBlockDto.getCode(), academicBlock.getCode());
    }

    @Test
    void findBlockTest() {
        AcademicBlock academicBlock = AcademicBlock.builder().build();
        AcademicBlockDto academicBlockDto = AcademicBlockDto
                .builder()
                .id(1)
                .code("SEP")
                .build();
        when(academicBlockRepository.findById(1L)).thenReturn(Optional.of(academicBlock));
        when(listMapper.mapClassToDto(academicBlock, new AcademicBlockDto())).thenReturn(academicBlockDto);
        AcademicBlockDto result = academicBlocksService.findBlock(1L);
        assertEquals(academicBlockDto, result);
    }

    @Test
    void findAllBlocksTest() {
        AcademicBlock academicBlock = AcademicBlock.builder().build();
        when(academicBlockRepository.findAll()).thenReturn(List.of(academicBlock));
        when(listMapper.mapListToDto(List.of(academicBlock), new AcademicBlockDto())).thenReturn(List.of());
        List<AcademicBlockDto> result = academicBlocksService.findAllBlocks();
        assertEquals(0, result.size());
    }

    @Test
    public void saveBlocksTest(){
        AcademicBlockDto academicBlockDto = AcademicBlockDto.builder().build();
        AcademicBlock academicBlock = AcademicBlock.builder().build();
        when(listMapper.mapClassFromDto(academicBlockDto, new AcademicBlock())).thenReturn(academicBlock);
        academicBlocksService.saveBlocks(academicBlockDto);
        verify(academicBlockRepository, times(1)).save(academicBlock);
    }


}