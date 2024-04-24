package dw.wholesale_company.service;

import dw.wholesale_company.model.Order;
import dw.wholesale_company.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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



}
