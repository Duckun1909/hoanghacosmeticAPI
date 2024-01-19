package com.example.cosmeticapi.services;

import com.example.cosmeticapi.config.SecurtityConfig;
import com.example.cosmeticapi.dtos.UserDTO;
import com.example.cosmeticapi.dtos.UserUpdateDTO;
import com.example.cosmeticapi.model.User;
import com.example.cosmeticapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private SecurtityConfig securtityConfig = new SecurtityConfig();

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(UserDTO userDTO){
        User user = new User();
        user.setFullname(userDTO.getFullname());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setUsername(userDTO.getUsername());
        user.setPassword(securtityConfig.passwordEncoder(userDTO.getPassword()));
        System.out.println(userDTO.toString());;
        return userRepository.save(user);
    }

    public Optional<User> getUserByID(int userID){
        Optional<User> user = userRepository.findUserById(userID);
        return user;
    }

    public Optional<User> getUserByName(String username){
        return userRepository.findUserByUsername(username);
    }

    public Optional<User> updateUser(UserUpdateDTO userUpdateDTO, int id){
        Optional<User> userOptional = userRepository.findUserById(id);
        if (userOptional.isPresent()){
            userOptional
                    .map(u -> {
                       u.setFullname(userUpdateDTO.getFullname());
                       u.setPhone(userUpdateDTO.getPhone());
                       u.setEmail(userUpdateDTO.getEmail());
                       return u;
                    });
        }

        User user = userOptional.get();
        userRepository.save(user);

        return userOptional;
    }

    public Optional<User> deleteUser(int id){
        Optional<User> userOptional = userRepository.findUserById(id);

        if (userOptional.isPresent()){
            userRepository.deleteById(id);

            return userOptional;
        }

        return null;
    }
}
