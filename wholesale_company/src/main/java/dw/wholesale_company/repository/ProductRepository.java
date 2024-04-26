package dw.wholesale_company.repository;

import dw.wholesale_company.dto.ProductDto;
import dw.wholesale_company.model.Product;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    //@Query("select a from Product a order by 5 desc limit :limitNum") // order by 5 는 에러
    @Query("select a from Product a order by a.inventory desc limit :limitNum")
    public List<Product> getAllProductsTopInvPriceJPQL1(@Param("limitNum") Integer limitNum);

// public class Product {
//        private long productId;
//        private String productName;
//        private String pkgUnit;
//        private int unitPrice;
//        private int inventory;

    @Query("select new dw.wholesale_company.dto.ProductDto" +
           "     ( a.productId" +
           "     , a.productName" +
           "     , a.pkgUnit" +
           "     , a.unitPrice" +
           "     , a.inventory" +
           "     , a.unitPrice * a.inventory" +
           "     ) from Product a" +
           " order by 6 desc" +
           " limit :limitNum" )
    public List<ProductDto> getAllProductsTopInvPriceJPQL2(@Param("limitNum") Integer limitNum);

    @Query(value = "select a.제품번호 " +
            "     , a.제품명  " +
            "     , a.포장단위 " +
            "     , a.단가    " +
            "     , a.재고    " +
            "  from 제품 a order by 5 desc limit :limitNum ", nativeQuery = true)
    public List<Product> getAllProductsTopInvPriceJPQL3(@Param("limitNum") Integer limitNum);

}
