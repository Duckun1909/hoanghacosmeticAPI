package com.example.cosmeticapi.services;

import com.example.cosmeticapi.components.JwtTokenUtil;
import com.example.cosmeticapi.config.SecurtityConfig;
import com.example.cosmeticapi.dtos.UserDTO;
import com.example.cosmeticapi.dtos.UserUpdateDTO;
import com.example.cosmeticapi.exceptions.DataNotFoundException;
import com.example.cosmeticapi.model.Role;
import com.example.cosmeticapi.model.User;
import com.example.cosmeticapi.repository.RoleRepository;
import com.example.cosmeticapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private SecurtityConfig securtityConfig;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setFullname(userDTO.getFullname());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        System.out.println(userDTO.toString());

        Optional<Role> role = roleRepository.findById(userDTO.getRoleId());

        role.orElseThrow(() -> new DataNotFoundException("Role not found"));

        user.setRole(role.get());
        return userRepository.save(user);
    }

    public Optional<User> getUserByID(int userID) {
        Optional<User> user = userRepository.findUserById(userID);
        return user;
    }

    public Optional<List<User>> getUserByName(String username) {
        return userRepository.findUserByUsername(username);
    }

    public Optional<User> updateUser(UserUpdateDTO userUpdateDTO, int id) {
        Optional<User> userOptional = userRepository.findUserById(id);
        if (userOptional.isPresent()) {
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

    public Optional<User> deleteUser(int id) {
        Optional<User> userOptional = userRepository.findUserById(id);

        if (userOptional.isPresent()) {
            userRepository.deleteById(id);

            return userOptional;
        }

        return null;
    }

    public String login(String username, String password) throws Exception {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new DataNotFoundException("Invalid username or password");
        }

        User existingUser = userOptional.get();

        // check password
        if (!passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new BadCredentialsException("Wrong username or password");
        }

        // authenticate with spring security
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                password,
                existingUser.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);

        return jwtTokenUtil.generateToken(userOptional.get()); // trả về token
    }


}
