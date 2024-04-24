package dw.wholesale_company.repository;

import dw.wholesale_company.model.Mileage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MileageRepository extends JpaRepository<Mileage, String> {
}
