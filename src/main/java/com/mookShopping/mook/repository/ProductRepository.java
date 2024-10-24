package com.mookShopping.mook.repository;

import com.mookShopping.mook.domain.Product;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private EntityManager em;

    public Long saveProduct(Product product) {
        em.persist(product);
        return product.getId();
    }

    public Product findOneProduct(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findProduct() {
        return em.createQuery("select p from Product p", Product.class).getResultList();
    }

    public void deleteAll() {
        List<Product> product = findProduct();
        for (Product productOne : product) {
            em.remove(productOne);
        }
    }

    // 데이터 수정은 병합(merge) 보다는 변경 감지 사용
    // 찾은데이터 값 변경 후, 커밋
//    public Long alterProduct(Long id, Product product) {
//        return em.merge(Product.class,)
//    }

}
