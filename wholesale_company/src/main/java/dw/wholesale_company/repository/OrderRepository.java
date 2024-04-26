package dw.wholesale_company.repository;

import dw.wholesale_company.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByOrderDate(LocalDate orderDate);

    //    [OrderDetail]
//    private long orderDetailId;
//    private Order order;
//    private Product product;
//    private int unitPrice;
//    private int orderQuantity;
//    private double discountRate;
//
//    [Order]
//    private String orderId;
//    private Customer customer;
//    private Employee employee;
//    private LocalDate orderDate;
//    private LocalDate requestDate;
//    private LocalDate shippingDate;
//
//    [Customer]
//    private  String customerId;
//    private  String customerName;
//    private  String customerEmployee;
//    private  String employeeTitle;
//    private  String address;
//    private  String city;
//    private  String area;
//    private  String phoneNumber;
//    private  int mileage;

    // JPQL로 작성
    @Query("SELECT c.city, SUM(a.unitPrice * a.orderQuantity) AS totalOrderAmount " +
            "  FROM OrderDetail a " +
            "  JOIN a.order b" +
            "  JOIN b.customer c" +
            " GROUP BY c.city" +
            " ORDER BY totalOrderAmount DESC")
    List<Object[]> getTopCitiesByTotalOrderAmount();



}
