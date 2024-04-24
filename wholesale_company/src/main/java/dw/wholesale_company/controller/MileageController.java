package dw.wholesale_company.controller;

import dw.wholesale_company.model.Mileage;
import dw.wholesale_company.service.MileageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MileageController {
    MileageService mileageService;

    public MileageController(MileageService mileageService) {
        this.mileageService = mileageService;
    }

    @PostMapping("/mileages")
    public ResponseEntity<Mileage> saveMileages(Mileage mileage) {
        return new ResponseEntity<>(mileageService.saveMileage(mileage), HttpStatus.OK);
    }

    @GetMapping("/mileages")
    public ResponseEntity<List<Mileage>> getAllMileages() {
        return new ResponseEntity<>(mileageService.getAllMileages(), HttpStatus.OK);
    }
}
