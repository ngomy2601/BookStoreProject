package com.example.bookstoreproject.api.user;

import com.example.bookstoreproject.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.example.bookstoreproject.api.user.UserDTOMapper.toUserDTO;
import static com.example.bookstoreproject.api.user.UserResponseDTOMapper.toUserResponseDTO;
import static com.example.bookstoreproject.api.user.UserResponseDTOMapper.toUserResponseDTOs;
import static com.example.bookstoreproject.domain.user.UserDomainMapper.toUser;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping
    public List<UserResponseDTO> findAll() {
        return toUserResponseDTOs(userService.findAll());
    }

    @GetMapping("{id}")
    public UserResponseDTO findById(@PathVariable UUID id) {
        return toUserResponseDTO(userService.findById(id));
    }

    @PostMapping
    public UserDTO create(final @RequestBody UserDTO userDTO) {
        return toUserDTO(userService.create(toUser(userDTO)));
    }

    @PutMapping("{id}")
    public UserDTO update(final @PathVariable UUID id, final @RequestBody UserDTO userDTO) {
        return toUserDTO(userService.update(id, toUser(userDTO)));
    }

    @DeleteMapping("{id}")
    public void delete(final @PathVariable UUID id) {
        userService.delete(id);
    }
}
