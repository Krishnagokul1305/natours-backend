package com.example.Natours.service;

import com.example.Natours.model.User;
import com.example.Natours.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(String id) {
        return userRepo.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepo.findUserByEmail(email).orElse(null);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User updateUser(String id, String name, Boolean active, MultipartFile photo) throws IOException {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) return null;

        if (name != null) user.setName(name);
        if (active != null) user.setActive(active);

        if (photo != null && !photo.isEmpty()) {
            String photoUrl = s3Service.uploadFile(photo);
            user.setPhoto(photoUrl);
        }

        user.setUpdatedAt();
        return userRepo.save(user);
    }


    public boolean deleteUser(String id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
