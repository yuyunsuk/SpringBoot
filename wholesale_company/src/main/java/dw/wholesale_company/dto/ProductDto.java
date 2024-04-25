package dw.wholesale_company.dto;

import dw.wholesale_company.model.Product;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter // DTO 에서는 잘 안사용함
public class ProductDto {
    private long productId;
    private String productName;
    private String pkgUnit;
    private int unitPrice;
    private int inventory;
    private int inventoryPrice;

    public ProductDto(long productId, String productName, String pkgUnit, int unitPrice, int inventory, int inventoryPrice) {
        this.productId = productId;
        this.productName = productName;
        this.pkgUnit = pkgUnit;
        this.unitPrice = unitPrice;
        this.inventory = inventory;
        this.inventoryPrice = inventoryPrice;
    }

    public ProductDto toProductDtoFromProduct(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setPkgUnit(product.getPkgUnit());
        productDto.setUnitPrice(product.getUnitPrice());
        productDto.setInventory(product.getInventory());
        productDto.setInventoryPrice(product.getUnitPrice() * product.getInventory());

        return productDto;
    }
}
