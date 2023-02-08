package ro.msg.learning.shop.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.mappers.ProductCategoryMapper;
import ro.msg.learning.shop.repositories.ProductCategoryRepository;

@Service
@Transactional
@RequiredArgsConstructor

public class ProductCategoryService {
    private final ProductCategoryMapper productCategoryMapper;
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategory createCategory(ProductCategory inputProductCategory) {
        ProductCategory productCategory = ProductCategory.builder()
        .name(inputProductCategory.getName())
                .description(inputProductCategory.getDescription())
                .build();
        return productCategoryRepository.save(productCategory);
    }


}
