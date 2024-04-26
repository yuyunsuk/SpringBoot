package dw.wholesale_company.service;

import dw.wholesale_company.exception.ResourceNotFoundException;
import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Employee;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.repository.CustomerRepository;
import dw.wholesale_company.repository.EmployeeRepository;
import dw.wholesale_company.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.hibernate.dialect.function.TimestampdiffFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TestService {

    CustomerRepository customerRepository;
    EmployeeRepository employeeRepository;
    OrderRepository orderRepository;

    // @Autowired 생략
    public TestService(CustomerRepository customerRepository, EmployeeRepository employeeRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.orderRepository = orderRepository;
    }

    // 1. 도시이름(city)을 매개변수로 받아 그 도시출신의 사원 정보를 보이시오.
    public List<Employee> getEmployeeByCity(String city) {

        //System.out.println("city: "+city);

        //return null;
        return employeeRepository.findAll().stream()
                .filter(a->a.getCity().contains(city))
                .collect(Collectors.toList());

//        // 선생님 코드
//        return employeeRepository.findByCity(city);

    }

    // 2. 주문번호를 매개변수(orderId)로 받아 주문한 고객의 정보를 보이시오.
    public Customer getCustomerByOrderId(String orderId) {

        //System.out.println("orderId: "+orderId);

//        return null;
        return orderRepository.findAll().stream()
                .filter(a->a.getOrderId().equals(orderId))
                .findFirst().get().getCustomer();

//        // 선생님 코드
//        Optional<Order> orderOptional = orderRepository.findById(orderId); // Order 를 가져옴
//        if (orderOptional.isEmpty()) {
//            throw new ResourceNotFoundException("getCustomerByOrderId", "Order", orderId);
//        } else {
//            return orderOptional.get().getCustomer();
//        }

    }

    // 3. 주문년도(orderYear)를 매개변수로 받아 그 해의 주문건수(int)를 보이시오.
    public int getOrderNumByOrderYear(int orderYear) {

        //System.out.println("orderYear: "+orderYear);

        int orderCount = 0;

        List<Order> orderList = orderRepository.findAll();

        for (int i=0; i<orderList.size(); i++) {
            if (orderList.get(i).getOrderDate().getYear() == orderYear) {
                orderCount = orderCount + 1;
            }
        }

        //return 0;
        return orderCount;

//        // 선생님 코드
//        return (int)orderRepository.findAll().stream()
//                .filter(a->a.getOrderDate().getYear() == orderYear)
//                .count();

    }

    // 4. 직위(position)와 나이대(year)를 매개변수로 받아 해당정보에 맞는 사원들의 정보를 보이시오.
    // 예를 들어 20대는 매개변수 year=20 이고 나이가 20살이상 30살미만을 의미함.
    // 나이계산은 (올해 - 태어난해)로 계산.
    public List<Employee> getEmployeeByPositionAndYear(String position, int year) {

        // year 는 10,20,30,40,50 등 10 단위로 입력 가정.
        //System.out.println("position: "+position+" "+"year: "+year);

        List<Employee> allEmployList = employeeRepository.findAll();
        List<Employee> targetEmployeeList = new ArrayList<>();

        int yearOfToday = LocalDate.now().getYear(); // 오늘의 년도

        //System.out.println("yearOfToday: "+ yearOfToday);

        for (int i=0; i<allEmployList.size(); i++) {
            if (allEmployList.get(i).getPosition().equals(position) &&
                    ( yearOfToday - allEmployList.get(i).getBirthDate().getYear() >= year &&
                      yearOfToday - allEmployList.get(i).getBirthDate().getYear() < (year + 10))
               ) {
                targetEmployeeList.add(allEmployList.get(i));
            }
        }

        //return null;

        return targetEmployeeList;

//        // 선생님 코드
//        return employeeRepository.findAll().stream()
//                .filter(a->a.getPosition().equals(position)
//                        && (LocalDate.now().getYear() - a.getBirthDate().getYear()) >= year
//                        && (LocalDate.now().getYear() - a.getBirthDate().getYear()) >= (year+10))
//                .collect(Collectors.toList());

    }
}
