package se.sandgrav.jakartaindividuelllab.services;

import org.springframework.http.ResponseEntity;
import se.sandgrav.jakartaindividuelllab.entities.Member;

import java.util.List;

public interface MemberServiceInterface {

    ResponseEntity<Member> addMember(Member member);

    List<Member> getAllMembers();

    ResponseEntity<Member> getMemberById(Long id);

    ResponseEntity<Member> updateMemberById(Long id, Member member);

    ResponseEntity<Member> deleteMemberById(Long id);
}
