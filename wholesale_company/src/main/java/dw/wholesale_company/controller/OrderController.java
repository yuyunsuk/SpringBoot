package dw.wholesale_company.controller;

import dw.wholesale_company.model.Order;
import dw.wholesale_company.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class OrderController {
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> saveOrders(Order order) {
        return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/orders/210501After")
    public ResponseEntity<List<Order>> get210501AfterOrderList() {
        return new ResponseEntity<>(orderService.get210501AfterOrderList(), HttpStatus.OK);
    }

    // 선생님 코드
    @GetMapping("/orders/date/after/{date}")
    public ResponseEntity<List<Order>> getOrderByDateAfter(@PathVariable LocalDate date) {
        return new ResponseEntity<>(orderService.getOrderByDateAfter(date),
                HttpStatus.OK);
    }

    @GetMapping("/TopCitiesByTotalOrderAmount/{intNum}")
    public ResponseEntity<List<Object[]>> getTopCitiesByTotalOrderAmount(@PathVariable int intNum) {
        return new ResponseEntity<>(orderService.getTopCitiesByTotalOrderAmount(intNum), HttpStatus.OK);
    }

    @GetMapping("/TopCitiesByTotalOrderAmount2/{intNum}")
    public ResponseEntity<List<Object[]>> getTopCitiesByTotalOrderAmount2(@PathVariable int intNum) {
        return new ResponseEntity<>(orderService.getTopCitiesByTotalOrderAmount2(intNum), HttpStatus.OK);
    }



}
