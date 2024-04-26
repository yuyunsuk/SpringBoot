package dw.wholesale_company.service;

import dw.wholesale_company.model.Order;
import dw.wholesale_company.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    OrderRepository orderRepository;

    EntityManager entityManager;

    public OrderService(OrderRepository orderRepository, EntityManager entityManager) {
        this.orderRepository = orderRepository;
        this.entityManager = entityManager;
    }

    // save Method
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    // load Method
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 1. 주문일이 2021년 5월 1일 이후인 주문 정보 얻기
    public List<Order> get210501AfterOrderList(){
        List<Order> orderList = orderRepository.findAll(); // OrderList
        List<Order> orderAfterList = new ArrayList<>(); // OrderAfterList
        LocalDate baseDate = LocalDate.of(2021, 05, 01);
        LocalDate orderDate;

        for (int i=0; i<orderList.size(); i++) {
            orderDate = orderList.get(i).getOrderDate();
            if (orderDate.compareTo(baseDate) > 0) { // 양수 이후, 음수 이전
                System.out.println("baseDate: "+baseDate+" "+"orderDate: "+orderDate);
                orderAfterList.add(orderList.get(i));
            }
        }

        return orderAfterList;
    }

    // 선생님 코드
    // 주문일이 2021년 5월 1일 이후인 주문 정보 얻기
    public List<Order> getOrderByDateAfter(LocalDate date) {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().filter(a -> a.getOrderDate().compareTo(date) > 0)
                .collect(Collectors.toList());
    }

    public List<Object[]> getTopCitiesByTotalOrderAmount(int intNum) {
        return orderRepository.getTopCitiesByTotalOrderAmount().stream()
                .limit(intNum)
                .collect(Collectors.toList());
    }

    public List<Object[]> getTopCitiesByTotalOrderAmount2(int limit) {
        return entityManager.createQuery("SELECT c.city, SUM(a.unitPrice * a.orderQuantity) AS totalOrderAmount " +
                        "  FROM OrderDetail a " +
                        "  JOIN a.order b" +
                        "  JOIN b.customer c" +
                        " GROUP BY c.city" +
                        " ORDER BY totalOrderAmount DESC")
                .setMaxResults(limit).getResultList();



    }



}
