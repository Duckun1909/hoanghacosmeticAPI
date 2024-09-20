package com.example.cosmeticapi.controller;

import com.example.cosmeticapi.dtos.CategoryDTO;
import com.example.cosmeticapi.model.Category;
import com.example.cosmeticapi.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categorys= categoryService.getAllCategory();
        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }

    @GetMapping("/getCategoryById")
    public ResponseEntity<?> getCategoryByID(@RequestParam("id") int id){
        Optional<Category> categoryOptional = Optional.empty();

        categoryOptional = categoryService.getCategoryById(id);
        System.out.println(categoryOptional.get().getCatName());

        return ResponseEntity.ok().body(categoryOptional.get());
    }

    @GetMapping("/getCategoryByName")
    public ResponseEntity<?> getCategoryByName(@RequestParam("name") String catName){
        System.out.println(catName);
        Optional<List<Category>> categoryOptional = categoryService.getCategoryByName(catName);


        return categoryOptional
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(( ) -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createCategory")
    public ResponseEntity<?> createCategory(
            @RequestBody @Valid CategoryDTO categoryDTO,
            BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // Xử lý lỗi validation ở đây nếu cần
            return ResponseEntity.badRequest().body("Invalid input");
        }

        // Xử lý khi DTO hợp lệ
        Category category = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<?> updateCategory(
            @RequestBody @Valid CategoryDTO categoryDTO,
            @RequestParam("id") int id){
        Optional<Category> categoryOptional = categoryService.updatecategory(categoryDTO, id);

        try {
            if (categoryOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(categoryOptional.get());
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String message = "Not found user by id " + id;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<?> deleteCategory(@RequestParam("id") int id){
        Optional<Category> categoryOptional = categoryService.deleteCategory(id);
        String message = "Delete Successfully";

        try {
            if (categoryOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        message = "Not found user by id " + id;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
