package com.example.cosmeticapi.repository;

import com.example.cosmeticapi.model.User;
import com.example.cosmeticapi.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findAll();

    Optional<Wishlist> findWishlistById(int id);

    void deleteById(int id);

   Optional<List<Wishlist>> findWishlistByUserId(int id);
}
