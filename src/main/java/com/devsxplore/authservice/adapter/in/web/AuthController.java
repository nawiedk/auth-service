package com.devsxplore.authservice.adapter.in.web;


import com.devsxplore.authservice.adapter.in.web.dto.LoginUserDto;
import com.devsxplore.authservice.adapter.in.web.dto.RegisterUserDto;
import com.devsxplore.authservice.adapter.in.web.dto.UserUpdateDto;
import com.devsxplore.authservice.adapter.in.web.mapper.request.LoginRequestMapper;
import com.devsxplore.authservice.adapter.in.web.mapper.request.UserRequestMapper;
import com.devsxplore.authservice.adapter.in.web.mapper.response.*;
import com.devsxplore.authservice.application.port.in.command.user.DeleteUserCommand;
import com.devsxplore.authservice.application.port.in.command.user.GetUserCommand;
import com.devsxplore.authservice.application.port.in.usecase.LoginUseCase;
import com.devsxplore.authservice.application.port.in.usecase.RegisterUseCase;
import com.devsxplore.authservice.application.port.in.usecase.user.DeleteUserUseCase;
import com.devsxplore.authservice.application.port.in.usecase.user.GetUserUseCase;
import com.devsxplore.authservice.application.port.in.usecase.user.UpdateUserUseCase;
import com.devsxplore.authservice.application.service.JwtService;
import com.devsxplore.authservice.domain.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterUseCase registerUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final LoginUseCase loginUseCase;

    private final UserRequestMapper requestMapper;
    private final UserResponseMapper responseMapper;

    private final LoginRequestMapper loginRequestMapper;
    private final LoginResponseMapper loginResponseMapper;

    private final AuthResponseMapper authResponseMapper;
    private final JwtService jwtService;

    @PostConstruct
    public void init() {
        var dto = new RegisterUserDto();
        dto.setUsername("admin");
        dto.setEmail("admin@mail.com");
        dto.setPassword("admin123");
        registerUseCase.registerAdmin(
                requestMapper.generateCreateUserCommand(dto)
        );
    }


    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> user = getUserUseCase.getAllUsers()
                .stream()
                .map(responseMapper::mapToResponse)
                .toList();

        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterUserDto dto) {

        String jwtToken = registerUseCase.register(
                requestMapper.generateCreateUserCommand(dto)
        );

        return ResponseEntity.ok(authResponseMapper.toAuthResponse(jwtToken));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginUserDto dto) {
        String jwtToken = loginUseCase.login(
                loginRequestMapper.generateLoginUserCommand(dto)
        );

        return ResponseEntity.ok(authResponseMapper.toAuthResponse(jwtToken));
    }


    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        User user = getUserUseCase.getUserByUserId(new GetUserCommand(userId));
        return ResponseEntity.ok(responseMapper.mapToResponse(user));
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        User updatedUser = updateUserUseCase.update(requestMapper.generateUpdateUserCommand(userUpdateDto));
        return ResponseEntity.ok(responseMapper.mapToResponse(updatedUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        deleteUserUseCase.deleteUserByUserId(new DeleteUserCommand(userId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
