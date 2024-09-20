package com.example.cosmeticapi.services;


import com.example.cosmeticapi.config.SecurtityConfig;
import com.example.cosmeticapi.dtos.UserDTO;
import com.example.cosmeticapi.dtos.UserUpdateDTO;
import com.example.cosmeticapi.dtos.WishlistDTO;
import com.example.cosmeticapi.model.User;
import com.example.cosmeticapi.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cosmeticapi.model.Wishlist;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;

    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    public Wishlist createWishlist(WishlistDTO wishlistDTO){
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(wishlistDTO.getUser_id());
        wishlist.setPrdId(wishlistDTO.getPrd_id());
        System.out.println(wishlistDTO.toString());;
        return wishlistRepository.save(wishlist);
    }

    public Optional<Wishlist> getWishlistByID(int wishListID){
        Optional<Wishlist> wishlist = wishlistRepository.findWishlistById(wishListID);
        return wishlist;
    }


    public Optional<Wishlist> updateWishlist(WishlistDTO wishlistDTO, int id){
        Optional<Wishlist> wishlistOptional = wishlistRepository.findWishlistById(id);
        if (wishlistOptional.isPresent()){
            wishlistOptional
                    .map(w -> {
                        w.setUserId(wishlistDTO.getUser_id());
                        w.setPrdId(wishlistDTO.getPrd_id());
                        return w;
                    });
        }

        Wishlist wishlist = wishlistOptional.get();
        wishlistRepository.save(wishlist);

        return wishlistOptional;
    }

    public Optional<Wishlist> deleteWishlist(int id){
        Optional<Wishlist> wishlistOptional = wishlistRepository.findWishlistById(id);

        if (wishlistOptional.isPresent()){
            wishlistRepository.deleteById(id);

            return wishlistOptional;
        }

        return null;
    }

    public Optional<List<Wishlist>> getWishlistByUserId(int userId){
        Optional<List<Wishlist>> wishListOptional = wishlistRepository.findWishlistByUserId(userId);

        return  wishListOptional;
    }

}
