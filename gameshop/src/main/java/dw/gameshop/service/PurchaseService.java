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
import java.util.*;

@Service
@Transactional
public class PurchaseService {

    PurchaseRepository purchaseRepository;
    UserRepository userRepository;

    // @Autowired 생략
    public PurchaseService(PurchaseRepository purchaseRepository, UserRepository userRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
    }

    public Purchase savePurchase(Purchase purchase)
    {
        // 구매확정 바로 직전, 현재시간을 저장함
        purchase.setPurchaseTime(LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public List<Purchase> getPurchaseListByUser(String userId) {
        // 유저아이디로 유저객체 찾기
        Optional<User> userOptional = userRepository.findByUserId(userId); // User 의 findByUserId 사용
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User", "ID", userId);
        }
        return purchaseRepository.findByUser(userOptional.get()); // Purchase 의 findByUser 사용
    }

    // 유저 이름으로 구매한 게임 찾기
    public List<Purchase> getPurchaseListByUserName(String userName) {
        // 유저이름으로 유저객체 찾기
        Optional<User> userNameOptional = userRepository.findByUserName(userName);
        if (userNameOptional.isEmpty()) {
            throw new ResourceNotFoundException("User", "Name", userName);
        }

        return purchaseRepository.findByUser(userNameOptional.get());
    }

}
