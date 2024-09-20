package com.example.cosmeticapi.repository;

import com.example.cosmeticapi.dtos.UserDTO;
import com.example.cosmeticapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserById(int id);

    @Query("select u from User u where u.username like %:name%")
    Optional<List<User>> findUserByUsername(@Param("name") String username);

    void deleteById(int id);

    Optional<User> findByUsername(String username);
}
