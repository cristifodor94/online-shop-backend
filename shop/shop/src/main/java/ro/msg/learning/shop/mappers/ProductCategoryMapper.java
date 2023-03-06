package ro.msg.learning.shop.mappers;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.ProductCategoryDTO;
import ro.msg.learning.shop.entities.ProductCategory;

@Component
@NoArgsConstructor
public class ProductCategoryMapper {

    public ProductCategory categoryDtoToCategory(ProductCategoryDTO productCategoryDTO) {
        return ProductCategory.builder()
                .id(productCategoryDTO.getId())
                .name(productCategoryDTO.getName())
                .description(productCategoryDTO.getDescription())
                .build();
    }

    public ProductCategoryDTO categoryToCategoryDTO(ProductCategory productCategory) {
        return ProductCategoryDTO.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .description(productCategory.getDescription())
                .build();
    }
}
