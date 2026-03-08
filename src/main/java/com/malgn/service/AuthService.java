package com.malgn.service;

import com.malgn.domain.entity.User;
import com.malgn.domain.repository.UserRepository;
import com.malgn.dto.request.LoginRequest;
import com.malgn.dto.response.LoginResponse;
import com.malgn.exception.InvalidCredentialsException;
import com.malgn.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new InvalidCredentialsException("사용자명 또는 비밀번호가 올바르지 않습니다"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("사용자명 또는 비밀번호가 올바르지 않습니다");
        }

        String token = jwtTokenProvider.createToken(user.getUsername(), user.getRole().name());

        log.info("User logged in successfully: {}", user.getUsername());

        return LoginResponse.builder()
            .accessToken(token)
            .tokenType("Bearer")
            .username(user.getUsername())
            .role(user.getRole().name())
            .build();
    }

}
