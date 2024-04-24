package dw.wholesale_company.repository;

import dw.wholesale_company.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
