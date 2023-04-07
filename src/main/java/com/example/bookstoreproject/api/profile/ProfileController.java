package com.example.bookstoreproject.api.profile;

import com.example.bookstoreproject.api.user.UserRequestDTO;
import com.example.bookstoreproject.api.user.UserResponseDTO;
import com.example.bookstoreproject.domain.auths.AuthsProvider;
import com.example.bookstoreproject.domain.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.example.bookstoreproject.api.user.UserResponseDTOMapper.toUserResponseDTO;
import static com.example.bookstoreproject.domain.user.UserDomainMapper.toUser;

@RestController
@RequestMapping("api/v1/profile")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('CONTRIBUTOR', 'ADMIN')")
public class ProfileController {

    private final UserService userService;
    private final AuthsProvider authsProvider;

    @Operation(summary = "Get the current user's information")
    @GetMapping
    public UserResponseDTO findById() {
        return toUserResponseDTO(userService.findById(authsProvider.getCurrentUserId()));
    }

    @Operation(summary = "Update current user's profile")
    @PutMapping
    public UserResponseDTO update(final @RequestBody UserRequestDTO userDTO) {
        return toUserResponseDTO(userService.update(authsProvider.getCurrentUserId(), toUser(userDTO)));
    }
}
