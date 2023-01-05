package registration.registrationsystem.service;

import registration.registrationsystem.domain.AcademicBlock;
import registration.registrationsystem.domain.CourseOffering;
import registration.registrationsystem.service.dto.AcademicBlockDto;

import java.util.List;

public interface IAcademicBlocks {
    public void saveBlocks(AcademicBlockDto academicBlockDto);
    public void updateBlocks(AcademicBlockDto academicBlockDto);
    public AcademicBlockDto findBlock(long id);
    public void updateCourseOffering(CourseOffering courseOffering, long id);
    public List<AcademicBlockDto> findAllBlocks();
}
