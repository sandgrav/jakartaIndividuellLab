package se.sandgrav.jakartaindividuelllab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.sandgrav.jakartaindividuelllab.entities.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
