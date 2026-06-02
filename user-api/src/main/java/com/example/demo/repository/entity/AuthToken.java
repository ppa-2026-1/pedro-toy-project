package com.example.demo.repository.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "auth_tokens")
public class AuthToken {

    @Id
    @Column(nullable = false, unique = true)
    private String token;

    @Column(name = "user_handle", nullable = false)
    private String userHandle;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    public AuthToken() {}

    public AuthToken(String token, String userHandle, Instant expiresAt) {
        this.token = token;
        this.userHandle = userHandle;
        this.expiresAt = expiresAt;
    }

    public String getToken()      { return token; }
    public String getUserHandle() { return userHandle; }
    public boolean isExpired()    { return Instant.now().isAfter(expiresAt); }
}