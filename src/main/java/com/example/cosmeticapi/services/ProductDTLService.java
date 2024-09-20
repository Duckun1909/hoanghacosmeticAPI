package com.example.cosmeticapi.services;

import com.example.cosmeticapi.dtos.WishlistDTO;
import com.example.cosmeticapi.model.Wishlist;
import com.example.cosmeticapi.repository.ProductDTLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cosmeticapi.model.Productdetails;
import com.example.cosmeticapi.dtos.ProductDetailsDTO;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDTLService {
    @Autowired
    private ProductDTLRepository productDTLRepository;

    public List<Productdetails> getAllProductDtl() {
        return productDTLRepository.findAll();
    }

    public Productdetails createWishlist(ProductDetailsDTO productDetailsDTO){
        Productdetails productdetails = new  Productdetails();
        productdetails.setPrdId(productDetailsDTO.getPrd_id());
        productdetails.setPrdsColor(productDetailsDTO.getPrds_color());
        productdetails.setPrdsImage(productDetailsDTO.getPrds_image());
        productdetails.setPrdsType(productDetailsDTO.getPrds_type());
        System.out.println(productDetailsDTO.toString());;
        return productDTLRepository.save(productdetails);
    }

    public Optional<Productdetails> getProductDtlByID(int productDtlID){
        Optional<Productdetails> productdetails = productDTLRepository.findProductdetailsById(productDtlID);
        return productdetails;
    }


    public Optional<Productdetails> updateProductDtl(ProductDetailsDTO productDetailsDTO, int id){
        Optional<Productdetails> productdetailsOptional = productDTLRepository.findProductdetailsById(id);
        if (productdetailsOptional.isPresent()){
            productdetailsOptional
                    .map(w -> {
                        w.setPrdId(productDetailsDTO.getPrd_id());
                        w.setPrdsColor(productDetailsDTO.getPrds_color());
                        w.setPrdsImage(productDetailsDTO.getPrds_image());
                        w.setPrdsType(productDetailsDTO.getPrds_type());
                        return w;
                    });
        }

        Productdetails productdetails = productdetailsOptional.get();
        productDTLRepository.save(productdetails);

        return productdetailsOptional;
    }

    public Optional<Productdetails> deleteProductDtl(int id){
        Optional<Productdetails> productdetailsOptional = productDTLRepository.findProductdetailsById(id);

        if (productdetailsOptional.isPresent()){
            productDTLRepository.deleteById(id);

            return productdetailsOptional;
        }

        return null;
    }
}
