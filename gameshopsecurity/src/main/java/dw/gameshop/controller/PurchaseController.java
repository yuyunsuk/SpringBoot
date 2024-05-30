package dw.gameshop.controller;

import dw.gameshop.model.Purchase;
import dw.gameshop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/products/purchase")
    public Purchase savePurchase(@RequestBody Purchase purchase) {
        return purchaseService.savePurchase(purchase);
    }

    // List 형태로 Post Save 추가
    @PostMapping("/products/purchaselist")
    public List<Purchase> savePurchase(@RequestBody List<Purchase> purchaseList) {
        return purchaseService.savePurchaseList(purchaseList);
    }

    // 모든 구매상품 조회는 관리자만 가능하도록 구현해야 함 (권한 사용)
    @GetMapping("/products/purchase")
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/products/purchase/id/{userId}")
    public List<Purchase> getPurchaseListByUser(@PathVariable String userId) {
        return purchaseService.getPurchaseListByUser(userId);
    }

    @GetMapping("/products/purchase/name/{userName}")
    public ResponseEntity<List<Purchase>> getPurchaseListByUserName(
            @PathVariable String userName) {
        return new ResponseEntity<>(purchaseService.getPurchaseListByUserName(userName),
                HttpStatus.OK);
    }
}