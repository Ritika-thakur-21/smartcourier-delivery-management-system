package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.enums.Role;
import com.example.demo.exception.*;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // 🔐 SIGNUP (FIXED - ALWAYS CUSTOMER)
    public UserResponse signup(SignupRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException(
                    "Email already registered: " + request.getEmail());
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());

        // 🔥 FORCE ROLE (SECURITY FIX)
        user.setRole(Role.CUSTOMER);

        return mapToResponse(userRepository.save(user));
    }

    // 🔐 LOGIN
    public JwtResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found: " + request.getEmail()));

        if (!passwordEncoder.matches(
                request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new JwtResponse(
                token,
                user.getEmail(),
                user.getRole().name(),
                user.getName()
        );
    }

    // 👥 GET ALL USERS
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // 👤 GET USER BY ID
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id));
    }

    // 🔐 VALIDATE TOKEN (FIXED)
    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }

    // 👑 MAKE ADMIN (FIXED)
    public boolean makeAdmin(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) return false;

        User user = optionalUser.get();
        user.setRole(Role.ADMIN); // 🔥 ENUM FIX
        userRepository.save(user);

        return true;
    }

    // 🔄 MAPPER
    private UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole().name());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}