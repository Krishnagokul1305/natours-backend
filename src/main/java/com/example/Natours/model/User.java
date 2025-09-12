package com.example.Natours.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    private String name;

    @Indexed(unique = true)
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
