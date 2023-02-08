package ro.msg.learning.shop.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.ProductDTO;
import ro.msg.learning.shop.entities.Product;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ProductCategoryMapper categoryMapper;
    public ProductDTO productToProductDTO(Product product) {
        return ProductDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .weight(product.getWeight())
                .imgUrl(product.getImgUrl())
                .productCategory(categoryMapper.categoryToCategoryDTO(product.getProductCategory()))
                .build();
    }

    public Product productDtoToProduct(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .weight(productDTO.getWeight())
                .imgUrl(productDTO.getImgUrl())
                .productCategory(categoryMapper.categoryDtoToCategory(productDTO.getProductCategory()))
                .build();
    }

}
