package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.ProductCategoryDTO;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.mappers.ProductCategoryMapper;
import ro.msg.learning.shop.services.ProductCategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/product-categories")

public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;
    private final ProductCategoryMapper productCategoryMapper;

    @PostMapping
    public ProductCategoryDTO createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryService.createCategory(productCategoryMapper.categoryDtoToCategory(productCategoryDTO));
        return productCategoryMapper.categoryToCategoryDTO(productCategory);
    }

    @PutMapping("/{id}")
    public ProductCategoryDTO updateProductCategory(@PathVariable Integer id, @RequestBody ProductCategoryDTO productCategoryDTO) {
        return productCategoryMapper.categoryToCategoryDTO(productCategoryService.updateCategory(id, productCategoryMapper.categoryDtoToCategory(productCategoryDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteProductCategory(@PathVariable Integer id) {
        productCategoryService.deleteCategoryById(id);
    }

    @GetMapping("/{id}")
    public ProductCategoryDTO findProductCategoryById(@PathVariable("id") Integer id) {
        return productCategoryMapper.categoryToCategoryDTO(productCategoryService.findCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryDTO>> getAllCategories() {
        List<ProductCategory> productCategories = productCategoryService.getAllCategories();
        List<ProductCategoryDTO> productCategoryDTOS = productCategories.stream().map(productCategoryMapper::categoryToCategoryDTO).collect(Collectors.toList());
        return new ResponseEntity<>(productCategoryDTOS, HttpStatus.OK);
    }
}
