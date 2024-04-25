package dw.wholesale_company.service;

import dw.wholesale_company.model.Department;
import dw.wholesale_company.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // @Autowired 로 필드 주입 또는 생성자 주입(@Autowired 생략)을 하지 않으면 departmentRepository 가 null 이라는 에러가 뜸.

    // Save Method
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Load Method
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public List<Department> getAllDepartmentJPQL1() {
        return departmentRepository.getAllDepartmentJPQL1();
    }

    public List<Department> getAllDepartmentJPQL2()
    {
        return departmentRepository.getAllDepartmentJPQL2();
    }

}
