package dw.wholesale_company.repository;

import dw.wholesale_company.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {

    @Query("select a from Department a")
    public List<Department> getAllDepartmentJPQL1();

    //@Query("select a.departName, a.departId from Department a")
    @Query(value = "select a.부서번호, a.부서명 from 부서 a", nativeQuery = true)
    public List<Department> getAllDepartmentJPQL2();

    Optional<Department> getDepartmentByDepartId(String drpartId);
}
