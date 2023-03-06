package ro.msg.learning.shop.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.exceptions.NotFoundException;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    private final SupplierService supplierService;

    public Product createProduct(Product inputProduct) {
        inputProduct.setSupplier(supplierService.checkSupplierPresence(inputProduct.getSupplier()));
        inputProduct.setProductCategory(productCategoryService.checkCategoryPresence(inputProduct.getProductCategory()));
        return productRepository.save(inputProduct);
    }

    public Product updateProduct(Integer id, Product updatedProduct) {
        Optional<Product> productToUpdate = productRepository.findById(id);
        if (productToUpdate.isPresent()) {
            updatedProduct.setId(id);
            updatedProduct.setSupplier(supplierService.checkSupplierPresence(updatedProduct.getSupplier()));
            updatedProduct.setProductCategory(productCategoryService.checkCategoryPresence(updatedProduct.getProductCategory()));
            return productRepository.save(updatedProduct);
        }
        throw new NotFoundException("Product not found!");
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    public Product findProductById(Integer id) {
        Optional<Product> searchedProduct = productRepository.findById(id);
        if (searchedProduct.isPresent()) {
            return searchedProduct.get();
        }
        throw new NotFoundException("Product not found!");
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
