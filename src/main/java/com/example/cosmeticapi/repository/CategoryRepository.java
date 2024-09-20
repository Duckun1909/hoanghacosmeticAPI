package com.example.cosmeticapi.repository;

import com.example.cosmeticapi.model.Address;
import com.example.cosmeticapi.model.Brand;
import com.example.cosmeticapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAll();

    Optional<Category> findCategoriesById(int id);

    @Query("select c from Category c where c.catName like %:name%")
    Optional<List<Category>> findByCatName(@Param("name") String catName);

    void deleteById(int id);
}

