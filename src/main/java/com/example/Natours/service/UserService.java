package com.example.Natours.service;

import com.example.Natours.model.User;
import com.example.Natours.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(String id) {
        return userRepo.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public User updateUser(String id, User updatedUser) {
        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser == null) return null;

        if (updatedUser.getName() != null) existingUser.setName(updatedUser.getName());
        if (updatedUser.getEmail() != null) existingUser.setEmail(updatedUser.getEmail());
        if (updatedUser.getPassword() != null) existingUser.setPassword(updatedUser.getPassword());
        if (updatedUser.getPhoto() != null) existingUser.setPhoto(updatedUser.getPhoto());
        if (updatedUser.getRole() != null) existingUser.setRole(updatedUser.getRole());
        if (updatedUser.getActive() != null) existingUser.setActive(updatedUser.getActive());
        if (updatedUser.getUpdatedAt() != null) existingUser.setUpdatedAt(updatedUser.getUpdatedAt());

        return userRepo.save(existingUser);
    }

    public boolean deleteUser(String id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
