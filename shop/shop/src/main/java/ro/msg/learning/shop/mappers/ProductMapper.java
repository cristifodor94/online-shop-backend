package ro.msg.learning.shop.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.ProductDTO;
import ro.msg.learning.shop.entities.Product;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ProductCategoryMapper categoryMapper;
    private final SupplierMapper supplierMapper;

    public Product productDtoToProduct(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .weight(productDTO.getWeight())
                .imgUrl(productDTO.getImgUrl())
                .supplier(supplierMapper.supplierDtoToSupplier(productDTO.getSupplier()))
                .productCategory(categoryMapper.categoryDtoToCategory(productDTO.getProductCategory()))
                .build();
    }

}
