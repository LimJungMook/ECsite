package com.mookShopping.mook.repository;

import com.mookShopping.mook.domain.Product;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void clean() {
        productRepository.deleteAll();
        System.out.println("dodod: " + productRepository.findProduct());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void saveProduct() {
        //given
        Product product = new Product();
        product.setName("tea");
        product.setPrice(22300L);
        product.setQuantity(100L);

        //when
        Long productId = productRepository.saveProduct(product);

        //then
        Assertions.assertThat(productId).isNotNull();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void findOne() {
        //given
        Product product = new Product();
        product.setName("tea");
        product.setPrice(22300L);
        product.setQuantity(100L);

        Long productId = productRepository.saveProduct(product);

        //when
        Product oneProduct = productRepository.findOneProduct(productId);

        //then
        Assertions.assertThat(oneProduct.getId()).isNotNull();
        Assertions.assertThat(oneProduct.getName()).isEqualTo(product.getName());
        Assertions.assertThat(oneProduct.getPrice()).isEqualTo(product.getPrice());
        Assertions.assertThat(oneProduct.getQuantity()).isEqualTo(product.getQuantity());
        Assertions.assertThat(oneProduct).isEqualTo(product);
    }

}