package dw.jdbcproject.repository;

import dw.jdbcproject.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemberRepository implements MemberRepository{ // 인터페이스를 상속한 클래스를 구현체 라고 함
    @Autowired
    DataSource dataSource; // Interface, JDBC 제공

    @Override
    public Member save(Member member) {
        String sql = "insert into members(name) values (?)"; // SQL 문 정의

        // SQL 패키지내의 클래스 정의
        Connection conn = null; // DB 연결 초기화
        PreparedStatement pstmt = null; // sql 을 싸서 보냄
        ResultSet rs = null; // SQL Result

        try {
            conn = DataSourceUtils.getConnection(dataSource); // 연결 실패시 예외처리
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // prepareStatement 에 담아서 보냄
            pstmt.setString(1, member.getName()); // 첫번째 입력 매개변수
            pstmt.executeUpdate(); // DB 에 데이터를 보냄

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                member.setId(rs.getLong(1)); // rs.getLong 첫번째 컬럼, Long 형, member 의 Id 에 씀
            } else {
                throw new SQLException("ID 조회실패");
            }

            return member;
        }
        catch (Exception e) {
            // 예외처리
            throw new IllegalStateException(e);
        } finally {
            // 연결 종료
            close(conn, pstmt, rs);

        }
        //return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {

        String sql = "select * from members"; // SQL 문 정의

        // SQL 패키지내의 클래스 정의
        Connection conn = null; // DB 연결 초기화
        PreparedStatement pstmt = null; // sql 을 싸서 보냄
        ResultSet rs = null; // SQL Result

        try {
            conn = DataSourceUtils.getConnection(dataSource); // 연결 실패시 예외처리
            pstmt = conn.prepareStatement(sql); // prepareStatement 에 담아서 보냄
            rs = pstmt.executeQuery();

            List<Member> members = new ArrayList<>();

            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong(1));
                member.setName(rs.getString(2));
                members.add(member);
            }

            return members;
        }
        catch (Exception e) {
            // 예외처리
            throw new IllegalStateException(e);
        } finally {
            // 연결 종료
            close(conn, pstmt, rs);
        }

        //return null;
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs){ // private 외부에서 이 함수를 사용하지 않음
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
