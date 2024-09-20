package com.example.cosmeticapi.controller;

import com.example.cosmeticapi.dtos.BrandDTO;
import com.example.cosmeticapi.dtos.UserDTO;
import com.example.cosmeticapi.dtos.UserUpdateDTO;
import com.example.cosmeticapi.model.Brand;
import com.example.cosmeticapi.model.User;
import com.example.cosmeticapi.services.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brand")
@Validated
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Brand>> getAll(){
        List<Brand> brands= brandService.getAllBrand();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/getBrandById")
    public ResponseEntity<?> getUserByID(@RequestParam("id") int id){
        Optional<Brand> brandOptional = brandService.getBrandById(id);

        return brandOptional
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(( ) -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getBrandByName")
    public ResponseEntity<?> getBrandByName(@RequestParam("name") String name){
        System.out.println(name);
        Optional<List<Brand>> brandOptional = brandService.getBrandByName(name);


        return brandOptional
                .map(brand -> ResponseEntity.ok().body(brand))
                .orElseGet(( ) -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createBrand")
    public ResponseEntity<?> createBrand(
            @RequestBody @Valid BrandDTO brandDTO,
            BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // Xử lý lỗi validation ở đây nếu cần
            return ResponseEntity.badRequest().body("Invalid input");
        }

        // Xử lý khi DTO hợp lệ
        Brand brand = brandService.createBrand(brandDTO);
        return new ResponseEntity<>(brand, HttpStatus.CREATED);
    }

    @PutMapping("/updateBrand")
    public ResponseEntity<?> updateBrand(
            @RequestBody @Valid BrandDTO brandDTO,
            @RequestParam("id") int id){
        Optional<Brand> brandOptional = brandService.updateBrand(brandDTO, id);

        try {
            if (brandOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(brandOptional.get());
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String message = "Not found user by id " + id;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam("id") int id){
        Optional<Brand> brandOptional = brandService.deleteBrand(id);
        String message = "Delete Successfully";

        try {
            if (brandOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        message = "Not found user by id " + id;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
