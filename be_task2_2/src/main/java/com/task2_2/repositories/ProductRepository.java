package com.task2_2.repositories;

import com.task2_2.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAllByCategoryId(Integer categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.id = :productId AND p.category.id = :categoryId")
    Product findByIdAndCategoryId(@Param("productId") Integer productId, @Param("categoryId") Integer categoryId);

}
