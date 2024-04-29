package dw.jdbcproject;

import dw.jdbcproject.model.Member;
import dw.jdbcproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping("/member/new")
    public ResponseEntity<Member> saveMember(@RequestBody Member member) {
        // 방법 1
        return ResponseEntity.ok(memberService.saveMember(member));
        // 방법 2
        //return new ResponseEntity<>(menberService.saveMember(member), HttpStatus.OK);
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/members/id/{id}")
    public ResponseEntity<Optional<Member>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @GetMapping("/members/name/{name}")
    public ResponseEntity<Optional<Member>> findById(@PathVariable String name) {
        return ResponseEntity.ok(memberService.findByName(name));
    }




}
