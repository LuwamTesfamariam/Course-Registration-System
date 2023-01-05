package registration.registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registration.registrationsystem.domain.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {


}
