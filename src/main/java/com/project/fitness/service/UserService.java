package com.project.fitness.service;

import com.project.fitness.dto.LoginRequestDto;
import com.project.fitness.dto.RegisterRequestDto;
import com.project.fitness.dto.UserResponseDto;
import com.project.fitness.model.User;
import com.project.fitness.model.UserRole;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto register(RegisterRequestDto dto) {

        UserRole role = dto.getRole() != null ? dto.getRole() : UserRole.USER;

        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .role(role)
                .build();

        User savedUser = userRepository.save(user);

        return mapToDto(savedUser);
    }

    public UserResponseDto mapToDto(User user) {

        UserResponseDto response = new UserResponseDto();

        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setRole(user.getRole());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }

    public User authenticate(LoginRequestDto request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }

        return user;
    }
}
