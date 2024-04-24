package dw.wholesale_company.service;

import dw.wholesale_company.model.Mileage;
import dw.wholesale_company.repository.MileageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MileageService {
    MileageRepository mileageRepository;

    public MileageService(MileageRepository mileageRepository) {
        this.mileageRepository = mileageRepository;
    }

    // save Method
    public Mileage saveMileage(Mileage mileage) {
        return mileageRepository.save(mileage);
    }

    // load Method
    public List<Mileage> getAllMileages() {
        return mileageRepository.findAll();
    }

}
