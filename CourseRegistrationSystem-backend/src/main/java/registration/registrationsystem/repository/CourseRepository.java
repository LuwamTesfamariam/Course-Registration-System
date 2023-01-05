package registration.registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registration.registrationsystem.domain.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {





}