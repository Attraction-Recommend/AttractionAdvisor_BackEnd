package com.example.attractionadvisor_backend.application;

import com.example.attractionadvisor_backend.domain.entity.User;
import com.example.attractionadvisor_backend.infrastructure.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUseCase {
    private final UserRepository userRepository;

    @Transactional
    public User registerUser(String username, String email, String password) {
        User user = User.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
        return userRepository.save(user);
    }
}