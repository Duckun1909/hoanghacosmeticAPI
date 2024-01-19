package com.example.cosmeticapi.controller;

import com.example.cosmeticapi.dtos.AddressDTO;
import com.example.cosmeticapi.dtos.AddressUpdateDTO;
import com.example.cosmeticapi.dtos.UserUpdateDTO;
import com.example.cosmeticapi.model.Address;
import com.example.cosmeticapi.model.User;
import com.example.cosmeticapi.services.AddressService;
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
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Address>> getAll() {
        List<Address> addresses = addressService.getAll();
        return ResponseEntity.ok().body(addresses);
    }

    @GetMapping("/getAddressByUserId")
    public ResponseEntity<List<Address>> getAddressByUserId(@RequestParam("userID") int userId){
        Optional<List<Address>> addresses = addressService.getUserByUserId(userId);

        return addresses
                .map( a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createAddress")
    public ResponseEntity<?> createAddress(@RequestBody @Valid AddressDTO addressDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Invalid address");
        }

        Address address = addressService.createUser(addressDTO);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @PutMapping("/updateAddress")
    public ResponseEntity<?> updateUser(
            @RequestBody @Valid AddressUpdateDTO addressUpdateDTO,
            @RequestParam("id") int id)
    {
        Optional<Address> addressOptional = addressService.updateAddress(addressUpdateDTO, id);
        System.out.println(id);
        try {
            if (addressOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(addressOptional.get());
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String message = "Not found address by id " + id;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @DeleteMapping("/deleteAddress")
    public ResponseEntity<?> deleteUser(@RequestParam("id") int id){
        Optional<Address> addressOptional = addressService.deleteAddress(id);
        String message = "Delete Successfully";

        try {
            if (addressOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        message = "Not found address by id " + id;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
