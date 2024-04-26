package dw.wholesale_company.controller;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Employee;
import dw.wholesale_company.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    TestService testService;

    // 1. 도시이름(city)을 매개변수로 받아 그 도시출신의 사원 정보를 보이시오.
    @GetMapping("/test/1/{city}")
    public ResponseEntity<List<Employee>> getCustomerByCity(@PathVariable String city) {
        return ResponseEntity.ok(testService.getEmployeeByCity(city));
    }

    // 2. 주문번호를 매개변수(orderId)로 받아 주문한 고객의 정보를 보이시오.
    @GetMapping("/test/2/{id}")
    public ResponseEntity<Customer> getCustomerByOrderId(@PathVariable String id) {
        return ResponseEntity.ok(testService.getCustomerByOrderId(id));
    }

    // 3. 주문년도(orderYear)를 매개변수로 받아 그 해의 주문건수(int)를 보이시오.
    @GetMapping("/test/3/{orderYear}")
    public ResponseEntity<Integer> getOrderNumByOrderYear(@PathVariable int orderYear) {
        return ResponseEntity.ok(testService.getOrderNumByOrderYear(orderYear));
    }

    // 4. 직위(position)와 나이대(year)를 매개변수로 받아 해당정보에 맞는 사원들의 정보를 보이시오.
    @GetMapping("/test/4/{position}/{year}")
    public ResponseEntity<List<Employee>> getEmployeeByPositionAndYear(@PathVariable String position, @PathVariable int year) {
        return ResponseEntity.ok(testService.getEmployeeByPositionAndYear(position, year));
    }
}
