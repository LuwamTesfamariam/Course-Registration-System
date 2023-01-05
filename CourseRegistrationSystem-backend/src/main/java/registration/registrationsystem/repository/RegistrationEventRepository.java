package registration.registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import registration.registrationsystem.domain.RegistrationEvent;

import java.util.List;


@Repository
public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent, Long> {

    RegistrationEvent findFirstByOrderByStartDateTimeDesc();

    @Query(value = "select * from registration_event where id = " +
            "(select registration_event_id from registration_event_registration_groups where registration_groups_id = " +
            "(select registration_group_id from registration_group_students where students_id = :studentId) )" , nativeQuery = true)
    List<RegistrationEvent> getRegistrationEvenByStudentId(Long studentId);
}
