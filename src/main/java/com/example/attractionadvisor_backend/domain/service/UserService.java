package com.example.attractionadvisor_backend.domain.service;

import com.example.attractionadvisor_backend.domain.entity.User;
import com.example.attractionadvisor_backend.domain.exception.DuplicateUserException;
import com.example.attractionadvisor_backend.domain.exception.UserNotFoundException;
import com.example.attractionadvisor_backend.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(String username, String email, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new DuplicateUserException("Username " + username + " is already taken.");
        }
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateUserException("Email " + email + " is already registered.");
        }

        User user = User.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();

        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found."));
    }

    // Add other methods as needed (update, delete, etc.)
    public User updateUserPassword(Long userId, String newPassword) {
        User user = getUserById(userId);
        user.updatePassword(newPassword);
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
        userRepository.deleteById(userId);
    }
}