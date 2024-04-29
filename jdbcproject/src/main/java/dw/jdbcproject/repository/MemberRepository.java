package dw.jdbcproject.repository;

import dw.jdbcproject.model.Member;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MemberRepository { // JPA 와 비교하면 JDBC 는 상속이 없음
    Member save(Member member); // JPA 는 Method 명 규칙이 있으나(구현은 Hibernate 가 실행), JDBC 는 따로 규칙이 없음, 그냥 친숙한 Method 명 사용 테스트
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
