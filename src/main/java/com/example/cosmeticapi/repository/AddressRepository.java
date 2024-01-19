package com.example.cosmeticapi.repository;

import com.example.cosmeticapi.model.Address;
import com.example.cosmeticapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAll();

    @Query("select a from Address a where a.userId  = :userID")
    Optional<List<Address>> findAddressByUserId(@Param("userID") int userId);
}
