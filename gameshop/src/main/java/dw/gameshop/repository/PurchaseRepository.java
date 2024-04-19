package dw.gameshop.repository;

import dw.gameshop.model.Purchase;
import dw.gameshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    // JPA method 명명법에 의거하여 올바른 작명을 해야 함!!!
    // 스펙에 명시된 명명법을 제대로 따르기만 하면 JPA 가 스펙의 규칙대로 구동됨
    // 배열이 아니고 Purchase 인 경우 처음 찾아진 데이터를 리턴함
    List<Purchase> findByUser(User user);
}
