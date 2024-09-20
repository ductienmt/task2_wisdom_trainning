package com.task2_2.service;

import com.task2_2.dto.ProductDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Integer id);
    ProductDTO saveProduct(Integer id, ProductDTO productDTO);
    void deleteProduct(Integer id);
    Page<ProductDTO> getProductsByCategoryId(Integer categoryId, Integer page);
    ProductDTO addProductToCategory(Integer categoryId, ProductDTO productDTO);
    ProductDTO updateCategory(Integer id, Integer categoryId, Integer newCategoryId);
    Page<ProductDTO> getProductsWithPagination(Integer page);
}
