package com.example.attractionadvisor_backend.presentation;


import com.example.attractionadvisor_backend.application.UserUseCase;
import com.example.attractionadvisor_backend.domain.entity.User;
import com.example.attractionadvisor_backend.presentation.dto.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserUseCase userUseCase;

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequest request) {
        User user = userUseCase.registerUser(request.getUsername(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok(user);
    }
}
