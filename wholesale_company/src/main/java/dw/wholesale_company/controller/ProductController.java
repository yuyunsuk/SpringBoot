package dw.wholesale_company.controller;

import dw.wholesale_company.model.Product;
import dw.wholesale_company.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/under50")
    public ResponseEntity<List<Product>> getInventoryUnder50() {
        return new ResponseEntity<>(productService.getInventoryUnder50(), HttpStatus.OK);
    }

    // 선생님 코드
    @GetMapping("/products/inventory/under/{num}")
    public ResponseEntity<List<Product>> getProductByInventoryUnder(@PathVariable int num) {
        return new ResponseEntity<>(productService.getProductByInventoryUnder(num),
                HttpStatus.OK);
    }

    @GetMapping("/products/{productname}")
    public ResponseEntity<List<Product>> getAllProductsByName(@PathVariable String productname) {
        return new ResponseEntity<>(productService.getAllProductsByName(productname), HttpStatus.OK);
    }

//    @GetMapping("/products/{lowLimit}/{highLimit}")
//    public ResponseEntity<List<Product>> getAllProductsByLowHiLimit(@PathVariable int lowLimit, @PathVariable int highLimit) {
//        return new ResponseEntity<>(productService.getAllProductsByLowHiLimit(lowLimit, highLimit), HttpStatus.OK);
//    }

    // 선생님 코드
    @GetMapping("/products/price")
    public ResponseEntity<List<Product>> getAllProductsByLowHiLimit(@RequestParam int lowLimit, @RequestParam int highLimit) {
        return new ResponseEntity<>(productService.getAllProductsByLowHiLimit(lowLimit, highLimit), HttpStatus.OK);
    }

    // /products/price?lowLimit=5000&highLimit=10000

}
