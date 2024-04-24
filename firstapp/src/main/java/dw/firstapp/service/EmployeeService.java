package dw.firstapp.service;

import dw.firstapp.exception.ResourceNotFoundException;
import dw.firstapp.model.Employee;
import dw.firstapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    // 의존성 주입
    @Autowired
    EmployeeRepository employeeRepository;
    public Employee saveEmployee(Employee employee) { // 컨트롤러가 던져준 employee 정보
        // repository code  - save
        employeeRepository.save(employee); // Insert 작업
        return employee;
    }

    public List<Employee> getAllEmployees() {
        // find => select
        // 기본생성자 만든 후에 세터로 입력하는 기능,
        // 생성자 혹은 세터가 없으면 에러 발생
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) { // 컨트롤러가 던져준 employee 정보
        Optional<Employee> employee = employeeRepository.findById(id); // 중요, 많이 사용
        if (employee.isEmpty()) {
            // 예외처리 case를 만듬
            throw new ResourceNotFoundException("Employee","ID",id);
        }  else {
            return employee.get();
        }
    }

    public Employee updateEmployeeById(Long id, Employee employee) { // 컨트롤러가 던져준 employee 정보
        // id, employee => 업데이트 할 아이디와 데이터
        // employee1 => id 로 조회해서 데이터가 이미 있는지 여부 확인
        // ID로 해당 데이터 찾기
        Optional<Employee> employee1 = employeeRepository.findById(id);
        if (employee1.isPresent()) {
            // 데이터 업데이트
            employee1.get().setEmail(employee.getEmail());
            employee1.get().setFirstName(employee.getFirstName());
            employee1.get().setLastName(employee.getLastName());

            // 실제로 DB에 저장하기
            // id가 있으면 update, 없으면 insert 실행
            // 객체 안에 id 값이 있으면 update
            employeeRepository.save(employee1.get());

            return employee1.get();
        }
        else {
            throw new ResourceNotFoundException("Employee", "update", id);
        }
    }

    public Employee deleteEmployeeById(Long id) {
        Optional<Employee> employee1 = employeeRepository.findById(id);
        if (employee1.isPresent()) {
            // id가 있으면 delete
            employeeRepository.deleteById(id);
            return employee1.get();
        }
        else {
            // 예외처리
            throw new ResourceNotFoundException("Employee", "delete", id);
        }
    }






}
