package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.ProductDTO;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.mappers.ProductMapper;
import ro.msg.learning.shop.services.ProductService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;


    //create
    @PostMapping
    public Product createProduct (@RequestBody ProductDTO productDTO) {
        Product product = productMapper.productDtoToProduct(productDTO);
       return productService.createProduct(product);
    }

    //update
    @PutMapping("/{id}")
    public Product updateProduct (@PathVariable Integer id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    //delete
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Integer id) {productService.deleteProductById(id);}

    //read by id
    @GetMapping({"/{id}"})
    public void readProductById(@PathVariable("id") Integer id) {productService.findProductById(id);}

    //read all products
    @GetMapping
    public List<Product> listProducts() {return productService.getAllProducts();}
}
