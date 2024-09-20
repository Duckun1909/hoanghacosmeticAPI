package com.example.cosmeticapi.repository;

import com.example.cosmeticapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    List<Product> findProductByPrdNameContainingIgnoreCase(String name);

    List<Product> findProductByCatId(int catID) ;
}
