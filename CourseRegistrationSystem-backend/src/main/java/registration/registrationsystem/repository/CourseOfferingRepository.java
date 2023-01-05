package registration.registrationsystem.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import registration.registrationsystem.domain.CourseOffering;

@Repository
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {
    @Modifying
    @Transactional
    @Query("update CourseOffering c set c.availableSeats = :availableSeats")
    void updateAvailableSeats(@Param("availableSeats") int    availableSeats);
}
