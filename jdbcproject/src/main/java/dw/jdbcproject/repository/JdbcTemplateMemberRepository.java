package dw.jdbcproject.repository;

import dw.jdbcproject.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@Primary // 구현체가 2개인데 이 구현체를 사용하시오(우선순위 지정) 사용하지 않으면 구현체가 2개이기 때문에 에러가 남
public class JdbcTemplateMemberRepository implements MemberRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate; // 반복 부분 자동으로 해줌(연결, 해제 등), 단점 => SQL을 직접 넣기 때문에 한곳에서 관리가 어려움, MyBatis 는 SQL 을 한곳에서 관리

    @Override
    public Member save(Member member) {
        //jdbcTemplate.update("insert into members(name) values (?)", member.getName()); // POST 사용시 id 값을 받아올수 없어서 다른 방법 사용
        //return member;

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder(); // id 값을 받아오기 위한 방법
        jdbcTemplate.update(connection ->{
            PreparedStatement ps = connection.prepareStatement(
                    "insert into members(name) values (?)", Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, member.getName());
            return ps;
        }, keyHolder);
        member.setId(keyHolder.getKey().longValue());
        return member;

        //return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //return Optional.empty();
        return jdbcTemplate.query("select  * from members where id = ?"
                , memberRowMapper(), id)
                .stream().findAny(); // findAny 는 Optional return 함
    }

    @Override
    public Optional<Member> findByName(String name) {
        //return Optional.empty();
        return jdbcTemplate.query("select  * from members where name = ?"
                , memberRowMapper(), name)
                .stream().findAny(); // findAny 는 Optional return 함
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from members", memberRowMapper());
        //return null;
    }

    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum) -> { // 함수를 리턴(방법을 리턴)
            Member member = new Member();
            member.setId(rs.getLong(1));
            member.setName(rs.getString(2));
            return member;
        };
    };



}
