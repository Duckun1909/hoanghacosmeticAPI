package com.example.cosmeticapi.services;

import com.example.cosmeticapi.dtos.AddressDTO;
import com.example.cosmeticapi.dtos.AddressUpdateDTO;
import com.example.cosmeticapi.dtos.UserDTO;
import com.example.cosmeticapi.dtos.UserUpdateDTO;
import com.example.cosmeticapi.model.Address;
import com.example.cosmeticapi.model.User;
import com.example.cosmeticapi.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAll() {
        List<Address> addresses = addressRepository.findAll();
        return addresses;
    }

    public Optional<List<Address>> getUserByUserId(int useID){
        Optional<List<Address>> addresses = addressRepository.findAddressByUserId(useID);
        return addresses;
    }

    public Address createUser(AddressDTO addressDTO){
        Address address = new Address();
        address.setFullname(addressDTO.getFullname());
        address.setAdrPhone(addressDTO.getAdr_phone());
        address.setAdrShipping(addressDTO.getAdr_shipping());
        address.setUserId(addressDTO.getUser_id());
        System.out.println(addressDTO.toString());;
        return addressRepository.save(address);
    }

    public Optional<Address> updateAddress(AddressUpdateDTO addressUpdateDTO, int id){
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()){
            addressOptional
                    .map(a -> {
                        a.setFullname(addressUpdateDTO.getFullname());
                        a.setAdrPhone(addressUpdateDTO.getAdr_phone());
                        a.setAdrShipping(addressUpdateDTO.getAdr_shipping());
                        a.setActive(addressUpdateDTO.getActive());
                        return a;
                    });
        }

        Address address = addressOptional.get();
        addressRepository.save(address);

        return addressOptional;
    }

    public Optional<Address> deleteAddress(int id){
        Optional<Address> addressOptional = addressRepository.findById(id);

        if (addressOptional.isPresent()){
            addressRepository.deleteById(id);

            return addressOptional;
        }

        return null;
    }
}
