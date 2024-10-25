package com.example.StudyJava.DTOs;

import lombok.Data;

@Data
public class ProductDTO {
    private Long productId;
    private String productName;
    private String cpu;
    private int memory;
    private String image;
}
