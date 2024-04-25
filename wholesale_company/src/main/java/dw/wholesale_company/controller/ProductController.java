package dw.wholesale_company.controller;

import dw.wholesale_company.dto.ProductDto;
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

    @GetMapping("/products/targetList")
    public ResponseEntity<List<Product>> getAllProductsByArray(@RequestBody List<Long> targetList) {
        //System.out.println(targetList.toString());
        return new ResponseEntity<>(productService.getAllProductsByArray(targetList), HttpStatus.OK);
    }

    // PostMan Get => Body [1,2,4,7,11,20]

    @GetMapping("/products/productsTopInvPrice/{limitNum}")
    public ResponseEntity<List<Product>> getProductsTopInvPrice(@PathVariable int limitNum) {
        //System.out.println(targetList.toString());
        return new ResponseEntity<>(productService.getProductsTopInvPrice(limitNum), HttpStatus.OK);
    }

    // Dto 활용
    @GetMapping("/products/productsTopInvPriceDto/{limitNum}")
    public ResponseEntity<List<ProductDto>> getProductsTopInvPriceDto(@PathVariable int limitNum) {
        //System.out.println(targetList.toString());
        return new ResponseEntity<>(productService.getProductsTopInvPriceDto(limitNum), HttpStatus.OK);
    }

    @GetMapping("/products/productsTopInvPriceJPQL1/{limitNum}")
    public ResponseEntity<List<Product>> getProductsTopInvPriceJPQL1(@PathVariable int limitNum) {
        //System.out.println(targetList.toString());
        return new ResponseEntity<>(productService.getProductsTopInvPriceJPQL1(limitNum), HttpStatus.OK);
    }

    // JPQL 활용
    @GetMapping("/products/productsTopInvPriceJPQL2/{limitNum}")
    public ResponseEntity<List<ProductDto>> getProductsTopInvPriceJPQL2(@PathVariable int limitNum) {
        //System.out.println(targetList.toString());
        return new ResponseEntity<>(productService.getProductsTopInvPriceJPQL2(limitNum), HttpStatus.OK);
    }

    @GetMapping("/products/productsTopInvPriceJPQL3/{limitNum}")
    public ResponseEntity<List<Product>> getProductsTopInvPriceJPQL3(@PathVariable int limitNum) {
        //System.out.println(targetList.toString());
        return new ResponseEntity<>(productService.getProductsTopInvPriceJPQL3(limitNum), HttpStatus.OK);
    }

}
