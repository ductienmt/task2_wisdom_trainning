package com.task2_2.service.impl;

import com.task2_2.dto.CategoryDTO;
import com.task2_2.dto.ProductDTO;
import com.task2_2.entities.Category;
import com.task2_2.entities.Product;
import com.task2_2.exception.CustomException;
import com.task2_2.repositories.CategoryRepository;
import com.task2_2.repositories.ProductRepository;
import com.task2_2.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<CategoryDTO> getAllCategories() {
        return this.categoryRepository.findAll().stream().map(category -> this.modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) {
        return this.categoryRepository.findById(id).map(category -> this.modelMapper.map(category, CategoryDTO.class)).orElse(null);
    }

    @Override
    public CategoryDTO saveCategory(Integer id, CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            throw new CustomException("Thông tin danh mục không được để trống");
        }

        if (id != null) {
            // Update existing category
            Category category = this.categoryRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Không tìm thấy danh mục với ID: " + id));

            if (!id.equals(categoryDTO.getId())) {
                throw new CustomException("ID không trùng khớp");
            }

            category.setCategoryName(categoryDTO.getCategoryName());
            category.setDescription(categoryDTO.getDescription());

            this.categoryRepository.save(category);
            return this.modelMapper.map(category, CategoryDTO.class);
        } else {
            // Create new category
            Category newCategory = this.modelMapper.map(categoryDTO, Category.class);
            this.categoryRepository.save(newCategory);
            return this.modelMapper.map(newCategory, CategoryDTO.class);
        }
    }


    @Override
    public void deleteCategory(Integer id) {
        if (id != null) {
            Category category = this.categoryRepository.findById(id).orElse(null);
            if (category != null) {
                this.categoryRepository.delete(category);
            } else {
                throw new CustomException("Không tìm thấy danh mục");
            }
        } else {
            throw new CustomException("Không tìm thấy danh mục");
        }
    }

}
