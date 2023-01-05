package registration.registrationsystem.repository;

import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import registration.registrationsystem.domain.RegistrationRequest;
import registration.registrationsystem.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//    @Query(value = "update student set registrationRequest ")
//    void updateByRegistrationRequests(RegistrationRequest request, long studentId);
}
