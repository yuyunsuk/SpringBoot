package dw.gameshop.repository;

import dw.gameshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String userId); // 한개 데이터 조회, Optional
    Optional<User> findByUserName(String userName);
}
