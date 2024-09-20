package com.task2_2.controller;

import com.task2_2.dto.ProductDTO;
import com.task2_2.service.impl.ProductServiceImpl;
import com.task2_2.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;


    @GetMapping("/list")
    public ResponseEntity<?> getProducts(@RequestParam(value = "id", required = false)Integer id,
                                         @RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
        try {
            if(id == null){
                Page<ProductDTO> products = this.productService.getProductsWithPagination(page);
                if (products.isEmpty()) {
                    return ResponseUtil.fail("Không có sản phẩm nào");
                }
                return ResponseUtil.success(products);
            } else{
                return ResponseUtil.success(this.productService.getProductById(id));
            }
        } catch (Exception e) {
            return ResponseUtil.fail(e.getMessage());
        }
    }

    @PostMapping("save")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDTO productDTO){
        try {
            return ResponseUtil.success(this.productService.saveProduct(productDTO.getId(), productDTO));
        } catch (Exception e) {
            return ResponseUtil.fail(e.getMessage());
        }
    }

    @PutMapping("update")
    public ResponseEntity<?> updateProduct(@RequestParam("id") Integer id, @RequestBody ProductDTO productDTO) {
        try {
            return ResponseUtil.success(this.productService.saveProduct(id, productDTO));
        } catch (Exception e) {
            return ResponseUtil.fail(e.getMessage());
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> deleteProduct(@RequestParam("id") Integer id) {
        try {
            this.productService.deleteProduct(id);
            return ResponseUtil.success("Xóa sản phẩm thành công");
        } catch (Exception e) {
            return ResponseUtil.fail(e.getMessage());
        }
    }

    @PatchMapping("/category")
    public ResponseEntity<?> updateCategory(@RequestParam("id") Integer id, @RequestParam("categoryId") Integer categoryId, @RequestBody ProductDTO updateCategory_product) {
        try {
            return ResponseUtil.success(this.productService.updateCategory(id, categoryId, updateCategory_product.getCategoryId()));
        } catch (Exception e) {
            return ResponseUtil.fail(e.getMessage());
        }
    }
}
