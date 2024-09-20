package com.task2_2;

import com.task2_2.entities.Product;
import com.task2_2.repositories.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class Task22ApplicationTests {

    ProductRepository productRepository;


    @Test
    void contextLoads() {
        Product product = productRepository.getReferenceById(4);
        System.out.println(product);
    }

}
