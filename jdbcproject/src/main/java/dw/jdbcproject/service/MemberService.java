package dw.jdbcproject.service;

import dw.jdbcproject.model.Member;
import dw.jdbcproject.repository.JdbcMemberRepository;
import dw.jdbcproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository; // Interface(약한 결합: 다른 구현제 선택 가능) 에 의존성 주입 사옹
    //JdbcMemberRepository jdbcMemberRepository; // 구현체(강한 결합: 다른 구현체 선택권한 없음) 에 의존성 주입을 하지 않음
    // Interface 항상 같은 기능 보장, 사용과 구현 완벽히 분리

    public Member saveMember(Member member) {
        // 레포지토리 save
        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Optional<Member> findById(Long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);

        if (memberOptional.isEmpty()) {
            throw new RuntimeException("findById");
        } else {
            return memberOptional;
        }
    }

    public Optional<Member> findByName(String name) {
        Optional<Member> memberOptional = memberRepository.findByName(name);

        if (memberOptional.isEmpty()) {
            throw new RuntimeException("findByName");
        } else {
            return memberOptional;
        }
    }



}
