package dw.wholesale_company.controller;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customers/overAvgMileage")
    public ResponseEntity<List<Customer>> getOverAvgMileage() {
        return new ResponseEntity<>(customerService.getOverAvgMileage(), HttpStatus.OK);
    }

    // 선생님 코드
    @GetMapping("/customers/highMileThanAvg")
    public ResponseEntity<List<Customer>> getCustomerWithHighMileThanAvg() {
        return new ResponseEntity<>(customerService.getCustomerWithHighMileThanAvg(),
                HttpStatus.OK);
    }

    @GetMapping("/customers/orderdate/{orderDate}")
    public ResponseEntity<List<Customer>> getCustomerByOrderDate(@PathVariable LocalDate orderDate) {
        return new ResponseEntity<>(customerService.getCustomerByOrderDate(orderDate), HttpStatus.OK);
    }

    @GetMapping("/customers/mileage/{mileageGrade}")
    public ResponseEntity<List<Customer>> getCustomerByMileageGrade(@PathVariable String mileageGrade){
        return new ResponseEntity<>(customerService.getCustomerByMileageGrade(mileageGrade), HttpStatus.OK);
    }

    @GetMapping("/customers/mileage2/{mileageGrade}")
    public ResponseEntity<List<Customer>> getCustomerByMileageGrade2(@PathVariable String mileageGrade){
        return new ResponseEntity<>(customerService.getCustomerByMileageGrade2(mileageGrade), HttpStatus.OK);
    }



}
