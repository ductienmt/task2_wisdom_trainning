package com.task2_2.service;

import com.task2_2.dto.CategoryDTO;
import com.task2_2.dto.ProductDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Integer id);
    CategoryDTO saveCategory(Integer id, CategoryDTO categoryDTO);
    void deleteCategory(Integer id);
}
