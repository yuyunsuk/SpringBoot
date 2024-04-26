package dw.wholesale_company.controller;

import dw.wholesale_company.model.Department;
import dw.wholesale_company.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {
    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> saveDepartment(Department department){
        return new ResponseEntity<>(departmentService.saveDepartment(department), HttpStatus.OK);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/departments/JPQL1")
    public ResponseEntity<List<Department>> getAllDepartmentJPQL1() {
        return new ResponseEntity<>(departmentService.getAllDepartmentJPQL1(), HttpStatus.OK);
    }

    @GetMapping("/departments/JPQL2")
    public ResponseEntity<List<Department>> getAllDepartmentJPQL2() {
        return new ResponseEntity<>(departmentService.getAllDepartmentJPQL2(), HttpStatus.OK);
    }

    @GetMapping("/departments/{departId}")
    public ResponseEntity<Department> getAllDepartmentRepository(@PathVariable String departId) {
        return new ResponseEntity<>(departmentService.getAllDepartmentRepository(departId), HttpStatus.OK);
    }

}
