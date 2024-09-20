package com.example.cosmeticapi.repository;

import com.example.cosmeticapi.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cosmeticapi.model.Productdetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDTLRepository extends JpaRepository<Productdetails, Long> {
    List<Productdetails> findAll();

    Optional<Productdetails> findProductdetailsById(int id);

    void deleteById(int id);
}
