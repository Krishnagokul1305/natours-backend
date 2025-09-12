package com.example.Natours.controller;

import com.example.Natours.model.Review;
import com.example.Natours.model.User;
import com.example.Natours.service.ReviewService;
import com.example.Natours.service.UserService;
import com.example.Natours.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        return ResponseEntity.ok(new ApiResponse<>("success", userService.getAllUsers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", null,"User not found"));
        }
        return ResponseEntity.ok(new ApiResponse<>("success", user));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<User>> getUserByEmail(@PathVariable String email) {
        User user=userService.getUserByEmail(email);
        if (user==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", null,"email not found"));
        }
        return ResponseEntity.ok(new ApiResponse<>("success", user));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("success", userService.createUser(user)));
    }

    @PatchMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<User>> updateUser(
            @PathVariable String id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "active", required = false) Boolean active,
            @RequestPart(value = "photo", required = false) MultipartFile photo) {
        try {
            User updated = userService.updateUser(id, name, active, photo);
            if (updated == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>("error", null, "User not found"));
            }
            return ResponseEntity.ok(new ApiResponse<>("success", updated));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("error", null, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{userId}/reviews")
    public ResponseEntity<ApiResponse<List<Review>>> getReviewsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(new ApiResponse<>("success", reviewService.findByUser(userId)));
    }
}
