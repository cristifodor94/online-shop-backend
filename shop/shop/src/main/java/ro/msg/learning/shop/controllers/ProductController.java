package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.ProductDTO;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.mappers.ProductMapper;
import ro.msg.learning.shop.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.createProduct(productMapper.productDtoToProduct(productDTO));
        return productMapper.productToProductDTO(product);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        return productMapper.productToProductDTO(productService.updateProduct(id, productMapper.productDtoToProduct(productDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProductById(id);
    }

    @GetMapping({"/{id}"})
    public ProductDTO readProductById(@PathVariable("id") Integer id) {
        return productMapper.productToProductDTO(productService.findProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOS = products.stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }
}
