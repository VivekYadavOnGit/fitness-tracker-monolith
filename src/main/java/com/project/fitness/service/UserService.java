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

    private final  UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto register(RegisterRequestDto registerRequestDto) {

        UserRole role = registerRequestDto.getRole() != null ? registerRequestDto.getRole() : UserRole.USER;

        User user = User.builder()
                .email(registerRequestDto.getEmail())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .firstName(registerRequestDto.getFirstName())
                .lastName(registerRequestDto.getLastName())
                .role(role)
                .build();

        User savedUser = userRepository.save(user);

        return mapToDto(savedUser);
    }

    public UserResponseDto mapToDto(User savedUser) {
        UserResponseDto response = new UserResponseDto();
        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setPassword(savedUser.getPassword());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setRole(savedUser.getRole());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());
        return response;
    }

    public User authenticate(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail());

        if(user == null)
            throw new RuntimeException("Invalid Credentials");

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }
        return user;
    }
}