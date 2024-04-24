package dw.wholesale_company.controller;

import dw.wholesale_company.model.OrderDetail;
import dw.wholesale_company.service.OrderDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderDetailController {
    OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping("/orderdetails")
    public ResponseEntity<OrderDetail> saveOrderDetail(OrderDetail orderDetail) {
        return new ResponseEntity<>(orderDetailService.saveOrderDetail(orderDetail), HttpStatus.OK);
    }

    @GetMapping("/orderdetails")
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        return new ResponseEntity<>(orderDetailService.getAllOrderDetails(), HttpStatus.OK);
    }

}
