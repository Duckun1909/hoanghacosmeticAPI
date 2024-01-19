package com.example.cosmeticapi.controller;

import com.example.cosmeticapi.dtos.UserDTO;
import com.example.cosmeticapi.dtos.UserUpdateDTO;
import com.example.cosmeticapi.model.User;
import com.example.cosmeticapi.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/users")
public class UserController{
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll(){
        List<User> users= userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // Xử lý lỗi validation ở đây nếu cần
            return ResponseEntity.badRequest().body("Invalid input");
        }

        // Xử lý khi DTO hợp lệ
        User user = userService.createUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<?> getUserById(@RequestParam("id") int id){
        Optional<User> userOptional = userService.getUserByID(id);

        return userOptional
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(( ) -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getUserByName")
    public ResponseEntity<?> getUserByName(@RequestParam("name") String name){
        System.out.println(name);
        Optional<User> userOptional = userService.getUserByName(name);

        return userOptional
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(( ) -> ResponseEntity.notFound().build());
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(
            @RequestBody @Valid UserUpdateDTO userUpdateDTO,
            @RequestParam("id") int id){
        Optional<User> userOptional = userService.updateUser(userUpdateDTO, id);

        try {
            if (userOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String message = "Not found user by id " + id;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam("id") int id){
        Optional<User> userOptional = userService.deleteUser(id);
        String message = "Delete Successfully";

        try {
            if (userOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        message = "Not found user by id " + id;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
