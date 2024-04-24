package dw.wholesale_company.service;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    CustomerRepository customerRepository;

    // @Autowired 생략
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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



}
