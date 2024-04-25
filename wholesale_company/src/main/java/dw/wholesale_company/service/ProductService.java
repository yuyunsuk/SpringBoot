package dw.wholesale_company.service;

import dw.wholesale_company.dto.ProductDto;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.ProductRepository;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // save Method
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // load Method
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 2. 제품의 재고가 50개 미만인 제품 정보 얻기
    public List<Product> getInventoryUnder50(){
        List<Product> allProductList = productRepository.findAll();
        List<Product> under50List = new ArrayList<>();
        for (int i=0; i<allProductList.size(); i++) {
            if (allProductList.get(i).getInventory() < 50) {
                under50List.add(allProductList.get(i));
            }
        }

        return under50List;
    }

    // 선생님 코드
    // 제품의 재고가 50개 미만인 제품 정보 얻기
    public List<Product> getProductByInventoryUnder(int num) {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .filter(p->p.getInventory() < num)
                .collect(Collectors.toList());
    }

    // 1. 제품 중에서 제품명에 '주스'가 들어간 제품에 대한 모든 정보를 검색하시오.
    public List<Product> getAllProductsByName(String productname) {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .filter(a -> a.getProductName().contains(productname))
                .collect(Collectors.toList());
    }

    // 2. 제품 단가가 5,000원 이상 10,000원 이하인 제품에는 무엇이 있는지 검색하시오.
    public List<Product> getAllProductsByLowHiLimit(int lowLimit, int hiLimit) {
        List<Product> productList = productRepository.findAll();

        System.out.println("lowLimit: "+ lowLimit +" "+"hiLimit: " + hiLimit);

        return productList.stream()
                .filter(a -> a.getUnitPrice() >= lowLimit && a.getUnitPrice() <= hiLimit)
                .collect(Collectors.toList());
    }

    public List<Product> getAllProductsByArray(List<Long> productList) {
//        List<Product> allProducts = productRepository.findAll();
//        List<Product> targetProducts = new ArrayList<>();
//
//        System.out.println("productList: "+ productList.toString());
//
//        for (int i=0; i<productList.size(); i++) {
//            for (int j = 0; j < allProducts.size(); j++) {
//                if (allProducts.get(j).getProductId() == productList.get(i)) {
//                    targetProducts.add(allProducts.get(i));
//                    break;
//                }
//            }
//        }
//
//        return targetProducts;

        return productRepository.findAll().stream()
                .filter(a->productList.contains(a.getProductId()))
                .collect(Collectors.toList());
    }

    // Product 클래스 내에 함수 추가 활용
    public List<Product> getProductsTopInvPrice(int limitNum) {
        List<Product> allProducts = productRepository.findAll();

//        // 선생님 코드
//        return productRepository.findAll().stream()
//                .sorted(Comparator.comparingInt((Product p)->p.getUnitPrice() * p.getInventory()).reversed())
//                .limit(limitNum)
//                .collect(Collectors.toList());

        // 수식에 대한 함수를 클래스 내에 만들고 정렬에서 이용
        return productRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Product::getInventoryPrice).reversed())
                .limit(limitNum)
                .collect(Collectors.toList());

    }

    // Dto 활용 Service 코드
    public List<ProductDto> getProductsTopInvPriceDto(int limitNum) {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> allProductsInvPrice = new ArrayList<>();

        for (int i=0; i<allProducts.size(); i++) {
            ProductDto productDto = new ProductDto(); // NoArgConstructor 사용 객체 생성
            allProductsInvPrice.add(productDto.toProductDtoFromProduct(allProducts.get(i))); // 함수를 이용 Dto 로 데이터 변환
        }

        // 수식에 대한 함수를 클래스 내에 만들고 정렬에서 이용
        return allProductsInvPrice.stream()
                .sorted(Comparator.comparingInt(ProductDto::getInventoryPrice).reversed())
                .limit(limitNum)
                .collect(Collectors.toList());
    }

    // JPQL 활용 Service 코드
    public List<Product> getProductsTopInvPriceJPQL1(int limitNum) {
        //List<ProductDto> allProductsInvPrice = productRepository.getAllProductsTopInvPriceJPQL(limitNum);
        List<Product> allProductsInvPrice = productRepository.getAllProductsTopInvPriceJPQL1(limitNum);

        // 수식에 대한 함수를 클래스 내에 만들고 정렬에서 이용
        return allProductsInvPrice;
    }

    // JPQL 활용 Service 코드
    public List<ProductDto> getProductsTopInvPriceJPQL2(int limitNum) {
        //List<ProductDto> allProductsInvPrice = productRepository.getAllProductsTopInvPriceJPQL(limitNum);
        List<ProductDto> allProductsInvPrice = productRepository.getAllProductsTopInvPriceJPQL2(limitNum);

        return allProductsInvPrice;
    }

    public List<Product> getProductsTopInvPriceJPQL3(int limitNum) {
        //List<ProductDto> allProductsInvPrice = productRepository.getAllProductsTopInvPriceJPQL(limitNum);
        List<Product> allProductsInvPrice = productRepository.getAllProductsTopInvPriceJPQL3(limitNum);

        // 수식에 대한 함수를 클래스 내에 만들고 정렬에서 이용
        return allProductsInvPrice;
    }

}
