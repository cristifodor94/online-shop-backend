package ro.msg.learning.shop.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.exceptions.NotFoundException;
import ro.msg.learning.shop.repositories.ProductCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor

public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategory createCategory(ProductCategory inputProductCategory) {
        return productCategoryRepository.save(inputProductCategory);
    }

    public ProductCategory updateCategory(Integer id, ProductCategory updatedCategory) {
        Optional<ProductCategory> categoryToUpdate = productCategoryRepository.findById(id);
        if (categoryToUpdate.isPresent()) {
            updatedCategory.setId(id);
            return productCategoryRepository.save(updatedCategory);
        }
        throw new NotFoundException("Category not found!");
    }

    public void deleteCategoryById(Integer id) {
        productCategoryRepository.deleteById(id);
    }

    public ProductCategory findCategoryById(Integer id) {
        Optional<ProductCategory> searchedCategory = productCategoryRepository.findById(id);
        if (searchedCategory.isPresent()) {
            return searchedCategory.get();
        }
        throw new NotFoundException("Category not found");
    }

    public List<ProductCategory> getAllCategories() {
        return productCategoryRepository.findAll();
    }

    public ProductCategory checkCategoryPresence(ProductCategory inputProductCategory) {
        Optional<ProductCategory> searchedCategory = productCategoryRepository.findByName(inputProductCategory.getName());
        ProductCategory productCategory;
        if (searchedCategory.isPresent()) {
            productCategory = searchedCategory.get();
        } else {
            productCategory = new ProductCategory();
            productCategory.setName(inputProductCategory.getName());
            productCategory.setDescription(inputProductCategory.getDescription());
            productCategoryRepository.save(productCategory);
        }
        return productCategory;
    }
}
