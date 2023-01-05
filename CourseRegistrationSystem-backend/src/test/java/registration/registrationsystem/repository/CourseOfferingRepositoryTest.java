package registration.registrationsystem.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import registration.registrationsystem.domain.CourseOffering;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseOfferingRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CourseOfferingRepository courseOfferingRepository;

    @Test
    public void updateAvailableSeatsTest() {
        // given
        CourseOffering courseOffering = CourseOffering.builder().availableSeats(24).build();
        entityManager.persist(courseOffering);
        entityManager.flush();
        // when
        courseOfferingRepository.updateAvailableSeats (30);
        entityManager.flush();
        // then
        assertEquals(24, courseOffering.getAvailableSeats());
    }

}