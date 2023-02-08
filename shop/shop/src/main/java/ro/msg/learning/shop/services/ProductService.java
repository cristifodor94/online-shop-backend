package ro.msg.learning.shop.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.mappers.ProductMapper;
import ro.msg.learning.shop.repositories.ProductCategoryRepository;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    public Product createProduct(Product inputProduct) {
        Product product = Product.builder()
                .name(inputProduct.getName())
                .description(inputProduct.getDescription())
                .price(inputProduct.getPrice())
                .weight(inputProduct.getWeight())
                .imgUrl(inputProduct.getImgUrl())
                .productCategory(checkCategoryPresence(inputProduct.getProductCategory()))
                .build();
        return productRepository.save(product);
    }
    public ProductCategory checkCategoryPresence(ProductCategory inputProductCategory) {
        Optional<ProductCategory> searchedCategory = productCategoryRepository.findByName(inputProductCategory.getName());
        ProductCategory productCategory;
        if(searchedCategory.isPresent()) {
            productCategory = searchedCategory.get();
        } else {
            productCategory = new ProductCategory();
            productCategory.setName(inputProductCategory.getName());
            productCategory.setDescription(inputProductCategory.getDescription());
            productCategoryRepository.save(productCategory);
        }
        return productCategory;
    }

    public Product updateProduct(Integer id, Product updatedProduct) {
        Product resultedProduct;
    Optional<Product> productToUpdate = productRepository.findById(id);
        if (productToUpdate.isPresent()) {
            Product updated = productToUpdate.get();
            updated.setName(updatedProduct.getName());
            updated.setDescription(updatedProduct.getDescription());
            updated.setPrice(updatedProduct.getPrice());
            updated.setWeight(updatedProduct.getWeight());
            updated.setImgUrl(updatedProduct.getImgUrl());
            updated.setProductCategory(checkCategoryPresence(updatedProduct.getProductCategory()));
            return productRepository.save(updated);
        }
        throw new ProductNotFoundException("Product not found!");

    }

    public void deleteProductById(Integer id) {productRepository.deleteById(id);}

    public Product findProductById(Integer id) {
        Optional<Product> searchedProduct = productRepository.findById(id);
        if (searchedProduct.isPresent()) {
            return searchedProduct.get();
        } else {
            throw new ProductNotFoundException("Product not found!");
        }
    }

    public List<Product> getAllProducts() {
        List<Product> productsInStock = new ArrayList<>();
try {
    List<Product> products = productRepository.findAll();
    if (products.isEmpty()) {
        throw new ProductNotFoundException("Products not found!");
    } else {
        System.out.println("Products:\n" + products);
    }
} catch (ProductNotFoundException ex) {
    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
        return productsInStock;
}
    }

