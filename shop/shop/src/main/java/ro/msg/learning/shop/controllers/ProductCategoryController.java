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
    public ResponseEntity<ProductCategoryDTO> createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryService.createCategory(productCategoryMapper.categoryDtoToCategory(productCategoryDTO));
        ProductCategoryDTO dto = productCategoryMapper.categoryToCategoryDTO(productCategory);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> updateProductCategory(@PathVariable Integer id, @RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryService.updateCategory(id, productCategoryMapper.categoryDtoToCategory(productCategoryDTO));
        ProductCategoryDTO dto = productCategoryMapper.categoryToCategoryDTO(productCategory);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductCategory(@PathVariable Integer id) {
        productCategoryService.deleteCategoryById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> findProductCategoryById(@PathVariable("id") Integer id) {
        ProductCategory productCategory = productCategoryService.findCategoryById(id);
        ProductCategoryDTO dto = productCategoryMapper.categoryToCategoryDTO(productCategory);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryDTO>> getAllCategories() {
        List<ProductCategory> productCategories = productCategoryService.getAllCategories();
        List<ProductCategoryDTO> productCategoryDTOS = productCategories.stream().map(productCategoryMapper::categoryToCategoryDTO).collect(Collectors.toList());
        return new ResponseEntity<>(productCategoryDTOS, HttpStatus.OK);
    }
}
