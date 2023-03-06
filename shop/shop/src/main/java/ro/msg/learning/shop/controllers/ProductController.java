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
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.createProduct(productMapper.productDtoToProduct(productDTO));
        ProductDTO dto = productMapper.productToProductDTO(product);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        Product product = productService.updateProduct(id, productMapper.productDtoToProduct(productDTO));
        ProductDTO dto = productMapper.productToProductDTO(product);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProductById(id);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ProductDTO> readProductById(@PathVariable("id") Integer id) {
        Product product = productService.findProductById(id);
        ProductDTO dto = productMapper.productToProductDTO(product);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOS = products.stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }
}
