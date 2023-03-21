package com.example.bookstoreproject.api.user;

import com.example.bookstoreproject.api.AbstractControllerTest;
import com.example.bookstoreproject.api.WithMockAdmin;
import com.example.bookstoreproject.api.WithMockUser;
import com.example.bookstoreproject.domain.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.bookstoreproject.fakes.UserFakes.buildUser;
import static com.example.bookstoreproject.fakes.UserFakes.buildUsers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest extends AbstractControllerTest {

    private static final String BASE_URL = "/api/v1/users";
    @Autowired
    protected MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockAdmin
    @WithMockUser
    void shouldFindAll_OK() throws Exception {
        final var users = buildUsers();

        when(userService.findAll()).thenReturn(users);

        get(BASE_URL)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(users.size()))
                .andExpect(jsonPath("$[0].id").value(users.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].username").value(users.get(0).getUsername()))
                .andExpect(jsonPath("$[0].firstName").value(users.get(0).getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(users.get(0).getLastName()))
                .andExpect(jsonPath("$[0].avatar").value(users.get(0).getAvatar()))
                .andExpect(jsonPath("$[0].roleId").value(users.get(0).getRoleId().toString()));

        verify(userService).findAll();
    }

    @Test
    @WithMockAdmin
    @WithMockUser
    void shouldCreateUser_OK() throws Exception {
        final var user = buildUser();
        when(userService.create(any())).thenReturn(user);

        post(BASE_URL, user)
                .andExpect(jsonPath("$.id").value(user.getId().toString()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.password").value(user.getPassword()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.avatar").value(user.getAvatar()))
                .andExpect(jsonPath("$.roleId").value(user.getRoleId().toString()));
    }

    @Test
    @WithMockAdmin
    @WithMockUser
    void shouldUpdateUser_OK() throws Exception {
        final var user = buildUser();
        when(userService.update(any(), any())).thenReturn(user);
        put(BASE_URL + "/" + user.getId(), user)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId().toString()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.password").value(user.getPassword()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.avatar").value(user.getAvatar()))
                .andExpect(jsonPath("$.roleId").value(user.getRoleId().toString()));
    }

    @Test
    @WithMockAdmin
    @WithMockUser
    void shouldDeleteUser_OK() throws Exception {
        final var user = buildUser();
        delete(BASE_URL + "/" + user.getId())
                .andExpect(status().isOk());
    }
}