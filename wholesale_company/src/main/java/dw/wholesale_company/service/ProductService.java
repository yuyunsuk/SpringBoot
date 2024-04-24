package dw.wholesale_company.service;

import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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



}
