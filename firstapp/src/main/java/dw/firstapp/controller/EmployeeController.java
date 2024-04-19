package dw.firstapp.controller;

import dw.firstapp.model.Employee;
import dw.firstapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    // 의존성 주입

    //@Autowired // => 의존객체 필드 주입
    //EmployeeService employeeService;

    EmployeeService employeeService;

    @Autowired // => 의존객체 생성자 주입(권장하는 방법!!! @Autowired 생략해도 의존성주입 됨)
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/api/employee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        // Service code 필요
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.OK);
    }

    @GetMapping("/api/employee")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/api/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @PutMapping("/api/employee/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable long id,
                                       @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateEmployeeById(id, employee), HttpStatus.OK);
    }

    @DeleteMapping("/api/employee/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable long id) {
        return new ResponseEntity<>(employeeService.deleteEmployeeById(id), HttpStatus.OK);
    }

}
