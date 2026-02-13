package com.project.fitness.controller;

import com.project.fitness.dto.LoginRequestDto;
import com.project.fitness.dto.LoginResponseDto;
import com.project.fitness.dto.RegisterRequestDto;
import com.project.fitness.dto.UserResponseDto;
import com.project.fitness.model.User;
import com.project.fitness.security.JwtUtil;
import com.project.fitness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        return ResponseEntity.ok(userService.register(registerRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        try {
            User user =  userService.authenticate(request);
            String token = jwtUtil.generateToken(user.getId(), user.getRole().name());

            return ResponseEntity.ok(new LoginResponseDto(token, userService.mapToDto(user)));

        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }
    }
}
