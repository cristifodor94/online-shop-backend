package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.ProductCategoryDTO;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.mappers.ProductCategoryMapper;
import ro.msg.learning.shop.services.ProductCategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/product-categories")

public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;
    private final ProductCategoryMapper productCategoryMapper;

    @PostMapping
    public ProductCategory createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryMapper.categoryDtoToCategory(productCategoryDTO);
        return productCategoryService.createCategory(productCategory);
    }

    @PutMapping("/{id}")
    public ProductCategory updateProductCategory(@PathVariable Integer id, @RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryMapper.categoryDtoToCategory(productCategoryDTO);
        return productCategoryService.updateCategory(id, productCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteProductCategory(@PathVariable Integer id) {productCategoryService.deleteCategoryById(id);}

    @GetMapping("/{id}")
    public ProductCategory findProductCategoryById(@PathVariable("id") Integer id) {return productCategoryService.findCategoryById(id);}

    @GetMapping
    public List<ProductCategory> findAllCategories() {return productCategoryService.getAllCategories();}

}
