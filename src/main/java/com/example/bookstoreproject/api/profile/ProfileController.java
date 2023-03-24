package com.example.bookstoreproject.api.profile;

import com.example.bookstoreproject.api.user.UserDTO;
import com.example.bookstoreproject.domain.auths.AuthsProvider;
import com.example.bookstoreproject.domain.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.example.bookstoreproject.api.user.UserDTOMapper.toUserDTO;
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
    public UserDTO findById() {
        return toUserDTO(userService.findById(authsProvider.getCurrentUserId()));
    }

    @Operation(summary = "Update current user's profile")
    @PutMapping
    public UserDTO update(final @RequestBody UserDTO userDTO) {
        return toUserDTO(userService.update(authsProvider.getCurrentUserId(), toUser(userDTO)));
    }
}
