package com.cosmetic.demo.repository;

import com.cosmetic.demo.dtos.UserDTO;
import com.cosmetic.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
}
