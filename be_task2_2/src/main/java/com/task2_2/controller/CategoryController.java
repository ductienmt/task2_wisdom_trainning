package com.task2_2.controller;

import com.task2_2.dto.CategoryDTO;
import com.task2_2.dto.ProductDTO;
import com.task2_2.service.impl.CategoryServiceImpl;
import com.task2_2.service.impl.ProductServiceImpl;
import com.task2_2.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ProductServiceImpl productService;


    @GetMapping("list")
    public ResponseEntity<?> getCategories(@RequestParam(value = "id", required = false) Integer id) {
        try {
            if(id == null){
                return ResponseUtil.success(this.categoryService.getAllCategories());
            } else{
                return ResponseUtil.success(this.categoryService.getCategoryById(id));
            }
        } catch (Exception e) {
            return ResponseUtil.fail(e.getMessage());
        }
    }

//    @GetMapping("/{id}/products")
//    public ResponseEntity<?> getProductsByCategory(@PathVariable Integer id, @RequestParam(value = "page", defaultValue = "0") int page) {
//        try {
//            Page<ProductDTO> products = this.productService.getProductsByCategoryId(id, page);
//            if (products.isEmpty()) {
//                return ResponseUtil.fail("No products found for this category");
//            }
//            return ResponseUtil.success(this.productService.getProductsByCategoryId(id, page));
//        } catch (Exception e) {
//            return ResponseUtil.fail(e.getMessage());
//        }
//    }

    @GetMapping("/products")
    public ResponseEntity<?> getProductsByCategory(@RequestParam(value = "id") Integer id, @RequestParam(value = "page", defaultValue = "1") int page) {
        try {
            Page<ProductDTO> products = this.productService.getProductsByCategoryId(id, page);
            if (products.isEmpty()) {
                return ResponseUtil.fail("Không có sản phẩm nào trong danh mục này");
            }
            return ResponseUtil.success(products);
        } catch (Exception e) {
            return ResponseUtil.fail(e.getMessage());
        }
    }

//    @PostMapping("/{id}/products")
//    public ResponseEntity<?> addProductToCategory(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
//        try {
//            return ResponseUtil.success(this.productService.addProductToCategory(id, productDTO));
//        } catch (Exception e) {
//            return ResponseUtil.fail(e.getMessage());
//        }
//    }

    @PostMapping("/products")
    public ResponseEntity<?> addProductToCategory(@RequestParam(value = "id") Integer id, @RequestBody ProductDTO productDTO) {
        try {
            return ResponseUtil.success(this.productService.addProductToCategory(id, productDTO));
        } catch (Exception e) {
            return ResponseUtil.fail(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDTO categoryDTO){
        try {
            return ResponseUtil.success(this.categoryService.saveCategory(categoryDTO.getId(), categoryDTO));
        } catch (Exception e) {
            return ResponseUtil.fail(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestParam("id") Integer id, @RequestBody CategoryDTO categoryDTO) {
        try {
            return ResponseUtil.success(this.categoryService.saveCategory(id, categoryDTO));
        } catch (Exception e) {
            return ResponseUtil.fail(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCategory(@RequestParam("id") Integer id) {
        try {
            this.categoryService.deleteCategory(id);
            return ResponseUtil.success("Xóa danh mục thành công");
        } catch (Exception e) {
            return ResponseUtil.fail(e.getMessage());
        }
    }




}
