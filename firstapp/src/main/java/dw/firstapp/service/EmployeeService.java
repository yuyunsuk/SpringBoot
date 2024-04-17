package dw.firstapp.service;

import dw.firstapp.model.Employee;
import dw.firstapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    public Employee saveEmployee(Employee employee) { // 컨트롤러가 던져준 employee 정보
        // repository code 필요
        employeeRepository.save(employee); // Insert 작업

        return employee;
    }
}
