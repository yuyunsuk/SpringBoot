package dw.wholesale_company.service;

import dw.wholesale_company.model.OrderDetail;
import dw.wholesale_company.repository.OrderDetailRepository;
import dw.wholesale_company.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    // save Method
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    // load Method
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }
}
