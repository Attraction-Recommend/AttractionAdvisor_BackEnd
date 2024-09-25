package com.example.attractionadvisor_backend.presentation;


import com.example.attractionadvisor_backend.application.UserUseCase;
import com.example.attractionadvisor_backend.application.dto.UserDto;
import com.example.attractionadvisor_backend.presentation.request.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserUseCase userUseCase;

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegistrationRequest request) {
        UserDto user = userUseCase.registerUser(request.getUsername(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        UserDto user = userUseCase.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<UserDto> updateUserPassword(@PathVariable Long userId, @RequestBody String newPassword) {
        UserDto updatedUser = userUseCase.updateUserPassword(userId, newPassword);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userUseCase.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
