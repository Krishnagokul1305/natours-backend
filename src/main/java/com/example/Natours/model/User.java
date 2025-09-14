package com.example.Natours.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @NotNull
    @NotBlank(message = "Name is required")
    private String name;

    @Indexed(unique = true)
    @NotNull
    @NotBlank(message = "Email is required")
    private String email;

    private String password;

    private String photo = "default.jpg";

    private LocalDateTime updatedAt=LocalDateTime.now();

    private Role role = Role.USER;

    private String passwordResetToken;

    private LocalDateTime tokenExpireTime;

    private Boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Role {
        USER,
        ADMIN,
        GUIDE,
        LEAD_GUIDE
    }

    public boolean isUpdated(Long tokenIssuedAt) {
        return this.updatedAt != null &&
                tokenIssuedAt < this.updatedAt.toEpochSecond(java.time.ZoneOffset.UTC);
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
