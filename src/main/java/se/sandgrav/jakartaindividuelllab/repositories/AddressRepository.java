package se.sandgrav.jakartaindividuelllab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.sandgrav.jakartaindividuelllab.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
