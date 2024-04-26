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

    // JPQL 활용 Service 코드1 => JPQL 사용 원래의 테이블 Class 객체에 담기
    public List<Product> getProductsTopInvPriceJPQL1(int limitNum) {
        //List<ProductDto> allProductsInvPrice = productRepository.getAllProductsTopInvPriceJPQL(limitNum);
        List<Product> allProductsInvPrice = productRepository.getAllProductsTopInvPriceJPQL1(limitNum);

        // 수식에 대한 함수를 클래스 내에 만들고 정렬에서 이용
        return allProductsInvPrice;
    }

    // JPQL 활용 Service 코드 => JPQL 사용 Dto 에 담기
    public List<ProductDto> getProductsTopInvPriceJPQL2(int limitNum) {
        //List<ProductDto> allProductsInvPrice = productRepository.getAllProductsTopInvPriceJPQL(limitNum);
        List<ProductDto> allProductsInvPrice = productRepository.getAllProductsTopInvPriceJPQL2(limitNum);

        return allProductsInvPrice;
    }

    // JPQL 활용 Native Query 옵션 사용 예제
    public List<Product> getProductsTopInvPriceJPQL3(int limitNum) {
        //List<ProductDto> allProductsInvPrice = productRepository.getAllProductsTopInvPriceJPQL(limitNum);
        List<Product> allProductsInvPrice = productRepository.getAllProductsTopInvPriceJPQL3(limitNum);

        // 수식에 대한 함수를 클래스 내에 만들고 정렬에서 이용
        return allProductsInvPrice;
    }

    // Native Query 사용 Object 객체에 가져오는 함수
    @PersistenceContext
    private EntityManager entityManager;

    //public List<Object[]> executeNativeQuery(String param1, String param2) {
    public List<Object[]> executeNativeQueryPoductDto(int limitNum) {
        //String sqlQuery = "SELECT column1, column2 FROM your_table WHERE column3 = :param1 AND column4 = :param2";
        String sqlQuery = "select a.제품번호 " +
                          "     , a.제품명   " +
                          "     , a.포장단위 " +
                          "     , a.단가    " +
                          "     , a.재고    " +
                          "     , a.단가 * a.재고 as 재고금액" +
                          "  from 제품 a order by 6 desc limit :limitNum ";
        Query query = entityManager.createNativeQuery(sqlQuery);
        //query.setParameter("param1", param1);
        //query.setParameter("param2", param2);
        query.setParameter("limitNum", limitNum);

        //System.out.println("QueryResult: "+ query.getResultList().toString());

        return query.getResultList(); // Returns a list of Object arrays
    }

    // Native Query 사용2 => Dto 에 담는 예제
    public List<ProductDto> getProductsTopInvPriceJPQL4(int limitNum) {

        //YourRepository repository = new YourRepository();
        //List<Object[]> results = repository.executeNativeQuery("value1", "value2");

        List<ProductDto> targetProductDto = new ArrayList<>();
        List<Object[]> results = executeNativeQueryPoductDto(limitNum);

// [ProductDto]
//        private long productId;
//        private String productName;
//        private String pkgUnit;
//        private int unitPrice;
//        private int inventory;
//        private int inventoryPrice;

        // Process the results
        for (Object[] row : results) {
            // Access each column in the row
            System.out.println("productId: "+row[0].toString());
            System.out.println("productName: "+row[1].toString());
            System.out.println("pkgUnit: "+row[2].toString());
            System.out.println("unitPrice: "+row[3].toString());
            System.out.println("inventory: "+row[4].toString());
            System.out.println("inventoryPrice: "+row[5].toString());

            Long    column1Value = Long.valueOf(row[0].toString()); // Assuming column1 is of type String
            String  column2Value = row[1].toString(); // Assuming column2 is of type int
            String  column3Value = row[2].toString(); // Assuming column2 is of type int
            Integer column4Value = Integer.valueOf(row[3].toString()); // Assuming column2 is of type int
            Integer column5Value = Integer.valueOf(row[4].toString()); // Assuming column2 is of type int
            Integer column6Value = Integer.valueOf(row[5].toString()); // Assuming column2 is of type int

            System.out.println("column1Value: "+ column1Value);
            System.out.println("column2Value: "+ column2Value);
            System.out.println("column3Value: "+ column3Value);
            System.out.println("column4Value: "+ column4Value);
            System.out.println("column5Value: "+ column5Value);
            System.out.println("column6Value: "+ column6Value);

            ProductDto productDto = new ProductDto(column1Value, column2Value, column3Value, column4Value, column5Value, column6Value);
            targetProductDto.add(productDto);
            // Do something with the values...
        }

        return targetProductDto;
    }


}
