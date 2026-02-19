package com.project.fitness.security;

import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username)
                );

        return User.builder()
                .username(user.getEmail())   // must match JWT username
                .password(user.getPassword())
                .authorities("USER")         // change if you have roles
                .build();
    }
}
