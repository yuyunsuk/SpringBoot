package dw.wholesale_company.repository;

import dw.wholesale_company.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByOrderDate(LocalDate orderDate);
}
