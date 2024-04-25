package dw.wholesale_company.service;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Mileage;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.repository.CustomerRepository;
import dw.wholesale_company.repository.MileageRepository;
import dw.wholesale_company.repository.OrderRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomerService {
    CustomerRepository customerRepository;
    OrderRepository orderRepository;
    MileageRepository mileageRepository;

    // @Autowired 생략
    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository, MileageRepository mileageRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.mileageRepository = mileageRepository;
    }

    // Save Method
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    // Load Method
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // 4. 고객 전체의 평균마일리지보다 마일리지가 큰 고객 정보
    public List<Customer> getOverAvgMileage() {
        List<Customer> overAvgMileage = new ArrayList<>();
        List<Customer> allCustomers = customerRepository.findAll();

        Double avgMileage = 0.0;
        long   totalSum = 0;
        for (int i=0; i<allCustomers.size(); i++) {
            totalSum += allCustomers.get(i).getMileage();
        }
        avgMileage = Double.valueOf(totalSum) / Double.valueOf(allCustomers.size());

        System.out.println("avgMileage: " + avgMileage);

        for (int i=0; i<allCustomers.size(); i++) {
            if (allCustomers.get(i).getMileage() > avgMileage) {
                overAvgMileage.add(allCustomers.get(i));
            }
        }

        return overAvgMileage;
    }

    // 선생님 코드
    // 고객 전체의 평균마일리지보다 마일리지가 큰 고객 정보
    public List<Customer> getCustomerWithHighMileThanAvg() {
        List<Customer> customers = customerRepository.findAll();
        int sum = 0;
        for (int i=0; i<customers.size(); i++) {
            sum = sum + customers.get(i).getMileage();
        }
        Double avg = (double)sum / (double)customers.size();
        return customers.stream()
                .filter(c->c.getMileage() > avg)
                .collect(Collectors.toList());
    }


    // 3. 2020년 4월 9일에 주문한 고객의 모든 정보를 보이시오.
    //    + 날짜를 매개변수로 처리
    public List<Customer> getCustomerByOrderDate(LocalDate orderDate) {

        List<Integer> numList = new ArrayList<>();
        numList.add(0);
        numList.add(1);
        numList.add(2);
        numList.add(3);
        numList.add(4);
        numList.add(5);

        // filter 와 map 의 차이 확인
        System.out.println("filter: "+numList.stream().filter(a->a<2).collect(Collectors.toList()));
        System.out.println("map: "+numList.stream().map(a->a<2).collect(Collectors.toList()));

        System.out.println("orderDate: "+orderDate);

        List<Order> orderList = orderRepository.findByOrderDate(orderDate); // 해당 주문일의 주문 데이터

        return orderList.stream()
                .map(Order::getCustomer).collect(Collectors.toList());

    }

    // 6. 마일리지 등급별로 고객수를 보이시오.
    public List<Customer> getCustomerByMileageGrade(String grade) {
        List<Customer> customers = customerRepository.findAll();
        List<Customer> targetCustomerList = new ArrayList<>();
        List<Mileage> mileages = mileageRepository.findAll();

        int lowLimit = 0;
        int highLimit = 0;
        String mileageGrade = "";

        for (int i=0; i<mileages.size(); i++) {
            if (mileages.get(i).getMileageGrade().equals(grade)) {
                mileageGrade = mileages.get(i).getMileageGrade();
                lowLimit = mileages.get(i).getLowLimit();
                highLimit = mileages.get(i).getHighLimit();
                break;
            }
        }

        System.out.println("mileageGrade: "+ mileageGrade + " " + "lowLimit: "+ lowLimit + " " + "highLimit: "+ highLimit);

        for (int i=0; i<customers.size(); i++) {
            if (customers.get(i).getMileage() >= lowLimit && customers.get(i).getMileage() <= highLimit) {
                targetCustomerList.add(customers.get(i));
            }
        }

        System.out.println("등급 [" +mileageGrade +"] 의 고객수는 ["+ targetCustomerList.size() + "] 입니다.");

        return targetCustomerList.stream()
                .sorted(Comparator.comparingInt(Customer::getMileage).reversed())
                .collect(Collectors.toList());
    }


}
