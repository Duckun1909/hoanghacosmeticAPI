package com.example.cosmeticapi.services;

import com.example.cosmeticapi.config.SecurtityConfig;
import com.example.cosmeticapi.dtos.BrandDTO;
import com.example.cosmeticapi.dtos.UserDTO;
import com.example.cosmeticapi.dtos.UserUpdateDTO;
import com.example.cosmeticapi.model.Brand;
import com.example.cosmeticapi.model.User;
import com.example.cosmeticapi.repository.BrandRepository;
import com.example.cosmeticapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    public Brand createBrand(BrandDTO brandDTO){
        Brand brand = new Brand();
        brand.setBrdName(brandDTO.getBrd_name());
        brand.setBrdImg(brandDTO.getBrd_img());
        brand.setBrdWebsite(brandDTO.getBrd_website());
        System.out.println(brandDTO.toString());;
        return brandRepository.save(brand);
    }

    public Optional<Brand> getBrandById(int brandId){
        return brandRepository.findBrandById(brandId);
    }

    public Optional<List<Brand>> getBrandByName(String brdName){
        return brandRepository.findBrandByBrdName(brdName);
    }

    public Optional<Brand> updateBrand(BrandDTO brandDTO, int id){
        Optional<Brand> brandOptional = brandRepository.findBrandById(id);
        if (brandOptional.isPresent()){
            brandOptional
                    .map(b -> {
                        b.setBrdName(brandDTO.getBrd_name());
                        b.setBrdImg(brandDTO.getBrd_img());
                        b.setBrdWebsite(brandDTO.getBrd_website());
                        return b;
                    });
        }

        Brand brand = brandOptional.get();
        brandRepository.save(brand);

        return brandOptional;
    }

    public Optional<Brand> deleteBrand(int id){
        Optional<Brand> brandOptional = brandRepository.findBrandById(id);

        if (brandOptional.isPresent()){
            brandRepository.deleteById(id);

            return brandOptional;
        }

        return null;
    }
}
