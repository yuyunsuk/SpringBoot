package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Purchase;
import dw.gameshop.model.User;
import dw.gameshop.repository.PurchaseRepository;
import dw.gameshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    UserRepository userRepository;

    public Purchase savePurchase(Purchase purchase) {
        //구매확정 바로 직전, 현재시간을 저장함
        purchase.setPurchaseTime(LocalDateTime.now()); // PurchaseTime Setter 사용
        return purchaseRepository.save(purchase);
    }

    public List<Purchase> savePurchaseList(List<Purchase> purchaseList) {

        //구매확정 바로 직전, 현재시간을 저장함 => List 저장

//        List<Purchase> savedPurchaseList = purchaseList.stream()
//                .map((purchase) -> {
//                    //구매확정 바로 직전, 현재시간을 저장함
//                    purchase.setPurchaseTime(LocalDateTime.now()); // PurchaseTime Setter 사용
//                    return purchaseRepository.save(purchase);
//                }).collect(Collectors.toList());
//
//        return savedPurchaseList;

        for (int i=0; i<purchaseList.size(); i++) {
            purchaseList.get(i).setPurchaseTime(LocalDateTime.now()); // 각각 클래스 객체의 PurchaseTime Setter 사용
            purchaseRepository.save(purchaseList.get(i));             // 각각 클래스 객체 저장
        }

        return purchaseList;
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public List<Purchase> getPurchaseListByUser(String userId) {
        // 유저아이디로 유저객체 찾기
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User", "ID", userId);
        }
        return purchaseRepository.findByUser(userOptional.get());
    }

    //유저 이름으로 구매한 게임 찾기
    public List<Purchase> getPurchaseListByUserName(String userName) {
        // 유저이름으로 유저객체 찾기
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User", "Name", userName);
        }
        return purchaseRepository.findByUser(userOptional.get());
    }
}







