package se.sandgrav.jakartaindividuelllab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.sandgrav.jakartaindividuelllab.entities.Member;
import se.sandgrav.jakartaindividuelllab.services.MemberService;

import java.util.List;

@Controller
public class AdminController {
    private MemberService memberService;

    @Autowired
    public AdminController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/admin/members", method = RequestMethod.GET)
    @ResponseBody
    public List<Member> listMembers() {
        return memberService.getAllMembers();
    }

    @RequestMapping(value = "/admin/member/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Member> getMember(@PathVariable Long id){
        return memberService.getMemberById(id);
    }

    @RequestMapping (value = "/admin/updatemember/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member member){
        return memberService.updateMemberById(id, member);
    }

    @RequestMapping(value = "/admin/addmember", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Member> addMember(@RequestBody Member member){
        return memberService.addMember(member);
    }

    @RequestMapping(value = "/admin/deletemember/{id}", method = RequestMethod.GET)
    public String deleteMemberById(@PathVariable Long id){
        ResponseEntity<Member> responseEntity = memberService.deleteMemberById(id);
        return "redirect:/admin/deletemember";
    }
    @RequestMapping(value = "/admin/deletemember", method = RequestMethod.GET)
    public String deleteMember(Model model){
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        return "deletemember";
    }
}
