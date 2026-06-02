package com.example.demo.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import com.example.demo.repository.entity.User;
import com.example.demo.repository.entity.AuthToken;
import com.example.demo.repository.AuthTokenRepository;
import com.example.demo.repository.UserRepository;
import java.util.UUID;

@Service
public class AuthService {

    private static final long TOKEN_TTL_HOURS = 8;

    private final AuthTokenRepository authTokenRepository;
    private final UserRepository userRepository;

    public AuthService(AuthTokenRepository authTokenRepository,
                       UserRepository userRepository) {
        this.authTokenRepository = authTokenRepository;
        this.userRepository = userRepository;
    }

    /**
     * valida o token -> existe no banco e não ta expirado
     * retorna o handle do usuário ou null se inválido
     */
    public String validateToken(String token) {
        return authTokenRepository.findById(token)
                .map(authToken -> {
                    if (authToken.isExpired()) {
                        authTokenRepository.deleteById(token); // limpa expirado
                        return null;
                    }
                    return authToken.getUserHandle();
                })
                .orElse(null);
    }

    /**
     * faz o login do usuário e retorna o token gerado
     * ou null se o usuário não existir ou a senha estiver errada
     */
    public String login(String username, String password) {
        User user = userRepository.findByHandle(username).orElse(null);

        // Comparação direta — troque por BCrypt se as senhas forem hasheadas
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (user == null || !encoder.matches(password, user.getPassword())) {
            return null;
        }

        String token = UUID.randomUUID().toString();
        Instant expiresAt = Instant.now().plus(TOKEN_TTL_HOURS, ChronoUnit.HOURS);
        authTokenRepository.save(new AuthToken(token, user.getHandle(), expiresAt));
        return token;
    }

    /**
     * remove o token do banco.
     */
    public void logout(String token) {
        authTokenRepository.deleteById(token);
    }
}