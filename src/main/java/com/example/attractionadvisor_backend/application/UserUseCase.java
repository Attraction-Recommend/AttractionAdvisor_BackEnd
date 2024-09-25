package com.example.attractionadvisor_backend.application;

import com.example.attractionadvisor_backend.application.dto.UserDto;
import com.example.attractionadvisor_backend.domain.entity.User;
import com.example.attractionadvisor_backend.domain.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUseCase {
    private final UserService userService;

    @Transactional
    public UserDto registerUser(String username, String email, String password) {
        User user = userService.createUser(username, email, password);
        return convertToDto(user);
    }

    public UserDto getUserById(Long userId) {
        User user = userService.getUserById(userId);
        return convertToDto(user);
    }

    @Transactional
    public UserDto updateUserPassword(Long userId, String newPassword) {
        User updatedUser = userService.updateUserPassword(userId, newPassword);
        return convertToDto(updatedUser);
    }

    @Transactional
    public void deleteUser(Long userId) {
        userService.deleteUser(userId);
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }
}