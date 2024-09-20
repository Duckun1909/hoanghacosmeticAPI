package com.example.cosmeticapi.controller;

import com.example.cosmeticapi.dtos.UserDTO;
import com.example.cosmeticapi.dtos.UserUpdateDTO;
import com.example.cosmeticapi.dtos.WishlistDTO;
import com.example.cosmeticapi.model.User;
import com.example.cosmeticapi.model.Wishlist;
import com.example.cosmeticapi.services.UserService;
import com.example.cosmeticapi.services.WishlistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Wishlist>> getAll(){
        List<Wishlist> wishlists = wishlistService.getAllWishlists();
        return new ResponseEntity<>(wishlists, HttpStatus.OK);
    }

    @PostMapping("/createWishlist")
    public ResponseEntity<?> createWishlist(@RequestBody @Valid WishlistDTO wishlistDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // Xử lý lỗi validation ở đây nếu cần
            return ResponseEntity.badRequest().body("Invalid input");
        }

        // Xử lý khi DTO hợp lệ
        Wishlist wishlist = wishlistService.createWishlist(wishlistDTO);
        return new ResponseEntity<>(wishlist, HttpStatus.CREATED);
    }

    @GetMapping("/getWishlistById")
    public ResponseEntity<?> getWishlistById(@RequestParam("id") int id){
        Optional<Wishlist> wishlistOptional = wishlistService.getWishlistByID(id);

        return wishlistOptional
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(( ) -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getWishlistByUserId")
    public ResponseEntity<?> getWishlistByUserId(@RequestParam("userId") int userId){
        Optional<List<Wishlist>> wishlists = wishlistService.getWishlistByUserId(userId);

        return wishlists
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(( ) -> ResponseEntity.notFound().build());
    }

    @PutMapping("/updateWishlist")
    public ResponseEntity<?> updateWishlist(
            @RequestBody @Valid WishlistDTO wishlistDTO,
            @RequestParam("id") int id){
        Optional<Wishlist> wishlistOptional = wishlistService.updateWishlist(wishlistDTO, id);

        try {
            if (wishlistOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(wishlistOptional.get());
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String message = "Not found wishlist by id " + id;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @DeleteMapping("/deleteWishlist")
    public ResponseEntity<?> deleteWishlist(@RequestParam("id") int id){
        Optional<Wishlist> wishlistOptional = wishlistService.deleteWishlist(id);
        String message = "Delete Successfully";

        try {
            if (wishlistOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        message = "Not found wishlist by id " + id;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
