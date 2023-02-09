package ro.msg.learning.shop.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.exceptions.NotFoundException;
import ro.msg.learning.shop.repositories.ProductCategoryRepository;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryService productCategoryService;

    private final SupplierService supplierService;

    public Product createProduct(Product inputProduct) {
        Product product = Product.builder()
                .name(inputProduct.getName())
                .description(inputProduct.getDescription())
                .price(inputProduct.getPrice())
                .weight(inputProduct.getWeight())
                .imgUrl(inputProduct.getImgUrl())
                .supplier(supplierService.checkSupplierPresence(inputProduct.getSupplier()))
                /*.productCategory(productCategoryService.checkCategoryPresence(inputProduct.getProductCategory())*/
                .build();
        return productRepository.save(product);
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
            updated.setSupplier(supplierService.checkSupplierPresence(updatedProduct.getSupplier()));
            updated.setProductCategory(productCategoryService.checkCategoryPresence(updatedProduct.getProductCategory()));
            return productRepository.save(updated);
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
        } else {
            throw new NotFoundException("Product not found!");
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategoryId(Integer id) {
        ProductCategory searchedCategoryById = productCategoryService.findCategoryById(id);
        if (searchedCategoryById != null) {
            return productRepository.findAllProductsByProductCategory(searchedCategoryById);
        }
         else {
            throw new NotFoundException("Category not found");
        }
    }
}
