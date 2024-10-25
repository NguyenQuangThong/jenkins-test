package com.example.StudyJava.Controllers;

import com.example.StudyJava.DTOs.ProductDTO;
import com.example.StudyJava.Services.ProductService;
import com.example.StudyJava.Services.S3Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    private S3Service s3Service;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts().stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList()));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(modelMapper.map(productService.getProductById(id), ProductDTO.class));
    }

//    @PostMapping("/uploads")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        String bucketName = "bucket-thongnguyen";
//        s3Service.uploadFile(bucketName, file);
//        return ResponseEntity.ok("File uploaded successfully!");
//    }
}
