package dw.wholesale_company.service;

import dw.wholesale_company.model.Employee;
import dw.wholesale_company.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // save Method
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // load Method
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // 3. 사원의 직위가 '사원'인 사람들 중에서 가장 최근에 입사한 사원의 정보
    public Employee getPositionEmpl() {
        List<Employee> allEmplList = employeeRepository.findAll();
        List<Employee> positionEmplList = new ArrayList<>();

        for(int i=0; i<allEmplList.size(); i++) {
            if (allEmplList.get(i).getPosition().equals("사원")) {
                positionEmplList.add(allEmplList.get(i));
            }
        }

        positionEmplList.sort(Comparator.comparing(Employee::getHireDate).reversed());
        return positionEmplList.get(0);
    }

    // 선생님 코드
    // 사원의 직위가 '사원'인 사람들 중에서 가장 최근에 입사한 사원의 정보
    public Employee getEmployeeByHireLatest() {
        return employeeRepository.findAll()
                .stream()
                .filter(e->e.getPosition().equals("사원")) // 사원만 뽑아서
                .sorted(Comparator.comparing(Employee::getHireDate).reversed()) // 정렬한후 내림차순으로 바꿈
                .findFirst().get(); // 처음 데이터만 뽑아옴
    }



}
