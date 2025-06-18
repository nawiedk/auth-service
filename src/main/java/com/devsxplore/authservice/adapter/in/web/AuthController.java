package com.devsxplore.authservice.adapter.in.web;


import com.devsxplore.authservice.adapter.in.web.dto.UserCreateDto;
import com.devsxplore.authservice.adapter.in.web.mapper.request.UserRequestMapper;
import com.devsxplore.authservice.adapter.in.web.mapper.response.UserResponse;
import com.devsxplore.authservice.adapter.in.web.mapper.response.UserResponseMapper;
import com.devsxplore.authservice.application.port.in.usecase.CreateUserUseCase;
import com.devsxplore.authservice.application.port.in.usecase.DeleteUserUseCase;
import com.devsxplore.authservice.application.port.in.usecase.GetUserUseCase;
import com.devsxplore.authservice.application.port.in.usecase.UpdateUserUseCase;
import com.devsxplore.authservice.domain.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    private final UserRequestMapper requestMapper;
    private final UserResponseMapper responseMapper;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserCreateDto dto) {

        User createdUser = createUserUseCase.create(
                requestMapper.generateCreateUserCommand(dto)
        );

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{userId}")
                        .buildAndExpand(createdUser.getUserId().map(User.UserId::userId).orElseThrow())
                        .toUri()
        ).body(responseMapper.mapToResponse(createdUser));
    }
}
