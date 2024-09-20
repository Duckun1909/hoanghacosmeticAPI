package com.example.cosmeticapi.controller;

import com.example.cosmeticapi.dtos.ProductDTO;
import com.example.cosmeticapi.model.Product;
import com.example.cosmeticapi.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getProductByName")
    public ResponseEntity<List<Product>> getProductByName(@RequestParam("name") String name) {
        List<Product> products = productService.getProductByName(name);

        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAll();

        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/getProductByCatID")
    public ResponseEntity<List<Product>> getProductByCatID(@RequestParam("catID") int catID) {
        List<Product> products = productService.getProductByCatID(catID);

        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/searchProductByName")
    public ResponseEntity<Page<Product>> searchProductByName(@RequestParam("name") String name, Pageable pageable) {
        Page<Product> products = productService.searchByName(name, pageable);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/getProductHighRating")
    public ResponseEntity<Page<Product>> getProductHighRating(Pageable pageable) {
        Page<Product> products = productService.getProductHighRating(pageable);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = productService.createProduct(productDTO);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDTO productDTO, @RequestParam int id) {
        try {
            Product product = productService.updateProduct(productDTO, id);

            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getProductHasMaxPrice")
    public ResponseEntity<Object> getProductHasMaxPrice() {
        Object result = productService.getProductHasMaxPrice();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
