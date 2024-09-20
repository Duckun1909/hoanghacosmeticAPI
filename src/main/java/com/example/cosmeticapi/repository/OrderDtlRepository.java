package com.example.cosmeticapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cosmeticapi.model.Orderdetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDtlRepository extends JpaRepository<Orderdetails, Long> {
    List<Orderdetails> findAll();

    Optional<Orderdetails> findOrderdetailsById(int id);

    void deleteById(int id);
}
