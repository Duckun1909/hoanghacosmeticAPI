package com.example.cosmeticapi.services;

import com.example.cosmeticapi.dtos.CategoryDTO;
import com.example.cosmeticapi.model.Category;
import com.example.cosmeticapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category createCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setCatName(categoryDTO.getCat_name());
        category.setCatDesc(categoryDTO.getCat_desc());
        System.out.println(categoryDTO.toString());;
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(int categoryId){
        return categoryRepository.findCategoriesById(categoryId);
    }

    public Optional<List<Category>> getCategoryByName(String catName){
        return categoryRepository.findByCatName(catName);
    }

    public Optional<Category> updatecategory(CategoryDTO categoryDTO, int id){
        Optional<Category> categoryOptional = categoryRepository.findCategoriesById(id);
        if (categoryOptional.isPresent()){
            categoryOptional
                    .map(b -> {
                        b.setCatName(categoryDTO.getCat_name());
                        b.setCatDesc(categoryDTO.getCat_desc());
                        return b;
                    });
        }

        Category category = categoryOptional.get();
        categoryRepository.save(category);

        return categoryOptional;
    }

    public Optional<Category> deleteCategory(int id){
        Optional<Category> categoryOptional = categoryRepository.findCategoriesById(id);

        if (categoryOptional.isPresent()){
            categoryRepository.deleteById(id);

            return categoryOptional;
        }

        return null;
    }
}
