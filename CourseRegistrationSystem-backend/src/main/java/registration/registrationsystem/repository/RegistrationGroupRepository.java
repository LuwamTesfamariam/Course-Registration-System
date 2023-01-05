package registration.registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registration.registrationsystem.domain.RegistrationGroup;

@Repository
public interface RegistrationGroupRepository extends JpaRepository<RegistrationGroup, Long> {
}
