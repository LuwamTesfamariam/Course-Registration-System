package registration.registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registration.registrationsystem.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
