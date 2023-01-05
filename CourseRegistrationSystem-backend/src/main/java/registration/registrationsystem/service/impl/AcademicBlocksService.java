package registration.registrationsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registration.registrationsystem.domain.AcademicBlock;
import registration.registrationsystem.domain.CourseOffering;
import registration.registrationsystem.repository.AcademicBlockRepository;
import registration.registrationsystem.service.IAcademicBlocks;
import registration.registrationsystem.service.dto.AcademicBlockDto;
import registration.registrationsystem.Util.ListMapper;

import java.util.List;

@Service
public class AcademicBlocksService implements IAcademicBlocks {
    @Autowired
    ListMapper<AcademicBlock, AcademicBlockDto> listMapper;

    @Autowired
    AcademicBlockRepository academicBlockRepository;
    @Override
    public void saveBlocks(AcademicBlockDto academicBlockDto) {
        academicBlockRepository.save(listMapper.mapClassFromDto(academicBlockDto, new AcademicBlock()));
    }

    @Override
    public void updateBlocks(AcademicBlockDto academicBlockDto) {
        AcademicBlock academicBlock = academicBlockRepository.findById(academicBlockDto.getId()).get();
        academicBlock.setCode(academicBlockDto.getCode());
        academicBlock.setSemester(academicBlockDto.getSemester());
        academicBlock.setStartDate(academicBlockDto.getStartDate());
        academicBlock.setEndDate(academicBlockDto.getEndDate());
        academicBlockRepository.save(academicBlock);
    }

    @Override
    public void updateCourseOffering(CourseOffering courseOffering, long id){
        AcademicBlock academicBlock = academicBlockRepository.findById(id).get();
        academicBlock.getCourseOfferings().add(courseOffering);
        academicBlockRepository.save(academicBlock);
    }

    @Override
    public AcademicBlockDto findBlock(long id) {
        return listMapper.mapClassToDto(academicBlockRepository.findById(id).get(), new AcademicBlockDto());
    }

    @Override
    public List<AcademicBlockDto> findAllBlocks() {
        return (List<AcademicBlockDto>)listMapper.mapListToDto(academicBlockRepository.findAll(), new AcademicBlockDto());
    }
}
