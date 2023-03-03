package com.example.bookstoreproject.api.book;

import com.example.bookstoreproject.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.bookstoreproject.api.book.UserDTOMapper.toUserDTO;
import static com.example.bookstoreproject.api.book.UserDTOMapper.toUserDTOs;
import static com.example.bookstoreproject.domain.user.UserDomainMapper.toUser;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> findAll() {
        return toUserDTOs(userService.findAll());
    }

    @PostMapping("/create")
    public UserDTO createUser(final @RequestBody UserDTO userDTO) {
        return toUserDTO(userService.createUser(toUser(userDTO)));
    }
}
