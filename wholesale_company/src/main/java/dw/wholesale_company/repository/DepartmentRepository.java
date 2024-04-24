package dw.wholesale_company.repository;

import dw.wholesale_company.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
}
