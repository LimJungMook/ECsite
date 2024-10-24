package com.mookShopping.mook.service;

import com.mookShopping.mook.domain.Product;
import com.mookShopping.mook.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Long saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    public Product findOneProduct(Long id) {
        return productRepository.findOneProduct(id);
    }

    public List<Product> findAllProduct() {
        return productRepository.findProduct();
    }


}
