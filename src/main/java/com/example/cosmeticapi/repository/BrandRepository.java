package com.example.cosmeticapi.repository;

import com.example.cosmeticapi.model.Brand;
import com.example.cosmeticapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    List<Brand> findAll();

    Optional<Brand> findBrandById(int id);

    @Query("select b from Brand b where b.brdName like %:name%")
    Optional<List<Brand>> findBrandByBrdName(@Param("name") String brdName);

    void deleteById(int id);
}
