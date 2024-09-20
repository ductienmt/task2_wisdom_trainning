package com.task2_2.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Integer id;
    private String productName;
    private String description;
    private Double price;
    private Integer categoryId;
}
