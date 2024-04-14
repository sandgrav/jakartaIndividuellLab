package se.sandgrav.jakartaindividuelllab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.sandgrav.jakartaindividuelllab.entities.Address;
import se.sandgrav.jakartaindividuelllab.entities.Member;
import se.sandgrav.jakartaindividuelllab.exceptions.ResourceNotFoundException;
import se.sandgrav.jakartaindividuelllab.repositories.MemberRepository;
import se.sandgrav.jakartaindividuelllab.repositories.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements MemberServiceInterface{
    private MemberRepository memberRepository;
    private AddressRepository addressRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, AddressRepository addressRepository) {
        this.memberRepository = memberRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public ResponseEntity<Member> addMember(Member member) {
        member.setId(null);
        if(member.getAddress() != null && member.getAddress().getId() != null){
            // Check if address exist, if it does, set the address to the one found else create new address
            Optional<Address> optionalAddress = addressRepository.findById(member.getAddress().getId());
            if(optionalAddress.isPresent()){
                member.setAddress(optionalAddress.get());
            }
            else{
                member.getAddress().setId(null);
            }
        }
        return new ResponseEntity<>(memberRepository.save(member), HttpStatus.OK);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public ResponseEntity<Member> getMemberById(Long id) {
        Member optionalMember = memberRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Member", "id", id));
        return new ResponseEntity<>(optionalMember, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Member> updateMemberById(Long id, Member member) {
        Member memberToUpdate = memberRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Member", "id", id));

        memberToUpdate.setFirstName(member.getFirstName());
        memberToUpdate.setLastName(member.getLastName());

        // Check if adress exists, if it does replace with the one found, shall not be possible to update address through member
        if(member.getAddress() != null && member.getAddress().getId() != null) {
            Address existingAddress = addressRepository.findById(member.getAddress().getId()).orElseThrow(()->new ResourceNotFoundException("Address", "id", member.getAddress().getId()));
            memberToUpdate.setAddress(existingAddress);
        }
        else {
            memberToUpdate.setAddress(member.getAddress());
        }

        memberToUpdate.setEmail(member.getEmail());
        memberToUpdate.setPhone(member.getPhone());
        memberToUpdate.setDateOfBirth(member.getDateOfBirth());
        return new ResponseEntity<>(memberRepository.save(memberToUpdate), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Member> deleteMemberById(Long id) {
        Member existingMember = memberRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Member", "id", id));
        memberRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
