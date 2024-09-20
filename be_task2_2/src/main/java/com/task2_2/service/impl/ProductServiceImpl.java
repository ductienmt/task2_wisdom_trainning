package com.task2_2.service.impl;

import com.task2_2.dto.ProductDTO;
import com.task2_2.entities.Category;
import com.task2_2.entities.Product;
import com.task2_2.exception.CustomException;
import com.task2_2.repositories.ProductRepository;
import com.task2_2.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Override
    public List<ProductDTO> getAllProducts() {
        return this.productRepository.findAll().stream().map(product -> this.modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Integer id) {
        return this.productRepository.findById(id).map(product -> this.modelMapper.map(product, ProductDTO.class)).orElse(null);
    }

    @Override
    public ProductDTO saveProduct(Integer id, ProductDTO productDTO) {
        if (productDTO == null) {
            throw new CustomException("Thông tin sản phẩm không được để trống");
        }

        if (id != null) {
            // Update existing product
            Product product = this.productRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Không tìm thấy sản phẩm với ID: " + id));

            if (!id.equals(productDTO.getId())) {
                throw new CustomException("ID không trùng khớp");
            }

            product.setProductName(productDTO.getProductName());
            product.setPrice(BigDecimal.valueOf(productDTO.getPrice()));
            product.setDescription(productDTO.getDescription());
            product.setCategory(this.modelMapper.map(this.categoryService.getCategoryById(productDTO.getCategoryId()), Category.class));

            this.productRepository.save(product);
            return this.modelMapper.map(product, ProductDTO.class);
        } else {
            // Create new product
            Product newProduct = this.modelMapper.map(productDTO, Product.class);
            this.productRepository.save(newProduct);
            return this.modelMapper.map(newProduct, ProductDTO.class);
        }
    }


    @Override
    public void deleteProduct(Integer id) {
        if (id != null) {
            Product product = this.productRepository.findById(id).orElse(null);
            if (product != null) {
                this.productRepository.delete(product);
            } else {
                throw new CustomException("Không tìm thấy sản phẩm");
            }
        } else {
            throw new CustomException("Kiểm tra lại id");
        }
    }

    @Override
    public Page<ProductDTO> getProductsByCategoryId(Integer categoryId, Integer page) {
        int pageSize = 5;
        if (page < 1) {
            throw new CustomException("Trang không hợp lệ phải từ 1 trở lên");
        }
        Page<Product> products = this.productRepository.findAllByCategoryId(categoryId, PageRequest.of(page - 1 , pageSize));
        return products.map(product -> this.modelMapper.map(product, ProductDTO.class));
    }

    @Override
    public ProductDTO addProductToCategory(Integer categoryId, ProductDTO productDTO) {
        if (productDTO == null) {
            throw new CustomException("Thông tin sản phẩm không được để trống");
        }
        if (categoryId == null) {
            throw new CustomException("Vui lòng chọn danh mục");
        }
        Category category = this.modelMapper.map(this.categoryService.getCategoryById(categoryId), Category.class);
        if (category == null) {
            throw new CustomException("Không tìm thấy danh mục");
        }
        Product product = this.modelMapper.map(productDTO, Product.class);
        if (product.getCategory() == null) {
            product.setCategory(category);
        }
        this.productRepository.save(product);
        return this.modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO updateCategory(Integer productId, Integer categoryId, Integer newCategoryId) {
        if (productId == null || categoryId == null || newCategoryId == null) {
            throw new CustomException("Kiểm tra lại id sản phẩm hoặc id danh mục");
        }

        Category oldCategory = this.modelMapper.map(this.categoryService.getCategoryById(categoryId), Category.class);

        if (oldCategory == null) {
            throw new CustomException("Không tìm thấy danh mục cũ");
        }


        Category newCategory = this.modelMapper.map(this.categoryService.getCategoryById(newCategoryId), Category.class);

        if (newCategory == null) {
            throw new CustomException("Không tìm thấy danh mục mới");
        }

        Product product = this.productRepository.findByIdAndCategoryId(productId, categoryId);

        if (product == null) {
            throw new CustomException("Không tìm thấy sản phẩm có id " + productId + " trong danh mục có id " + categoryId);
        }

        product.setCategory(newCategory);

        this.productRepository.save(product);

        return this.modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public Page<ProductDTO> getProductsWithPagination(Integer page) {
        int pageSize = 5;
        if (page < 1) {
            throw new CustomException("Trang không hợp lệ phải từ 1 trở lên");
        }
        Page<Product> products = this.productRepository.findAll(PageRequest.of(page - 1, pageSize));
        return products.map(product -> this.modelMapper.map(product, ProductDTO.class));
    }

}
