package com.example.bookstoreproject.api.book;

import com.example.bookstoreproject.domain.user.User;
import com.example.bookstoreproject.domain.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.bookstoreproject.fakes.UserFakes.buildUser;
import static com.example.bookstoreproject.fakes.UserFakes.buildUsers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

    private static final String BASE_URL = "/api/v1/users";
    @Autowired
    protected MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    void shouldFindAll_OK() throws Exception {
        final var users = buildUsers();

        when(userService.findAll()).thenReturn(users);

        this.mvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(users.size()))
                .andExpect(jsonPath("$[0].id").value(users.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].username").value(users.get(0).getUsername()))
                .andExpect(jsonPath("$[0].password").value(users.get(0).getPassword()))
                .andExpect(jsonPath("$[0].firstName").value(users.get(0).getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(users.get(0).getLastName()))
                .andExpect(jsonPath("$[0].avatar").value(users.get(0).getAvatar()))
                .andExpect(jsonPath("$[0].roleId").value(users.get(0).getRoleId().toString()));

        verify(userService).findAll();
    }

    @Test
    void shouldCreateUser_OK() throws Exception{
        final var user = buildUser();
        when(userService.create(any(User.class))).thenReturn(user);

    }


}