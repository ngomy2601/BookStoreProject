package com.example.bookstoreproject.api.profile;

import com.example.bookstoreproject.api.AbstractControllerTest;
import com.example.bookstoreproject.api.WithMockAdmin;
import com.example.bookstoreproject.api.WithMockUser;
import com.example.bookstoreproject.domain.auths.AuthsProvider;
import com.example.bookstoreproject.domain.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.example.bookstoreproject.fakes.UserFakes.buildUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfileController.class)
@AutoConfigureMockMvc
class ProfileControllerTest extends AbstractControllerTest {

    private static final String BASE_URL = "/api/v1/profile";

    @MockBean
    private AuthsProvider authsProvider;

    @MockBean
    private UserService userService;

    @BeforeEach
    void init() {
        when(authsProvider.getCurrentAuthentication()).thenCallRealMethod();
    }

    @Test
    @WithMockUser
    void shouldGetProfile_OK() throws Exception {
        final var user = buildUser();
        final var userAuthenticationToken = authsProvider.getCurrentAuthentication();
        user.setId(userAuthenticationToken.getUserId());
        when(userService.findById(authsProvider.getCurrentUserId())).thenReturn(user);
        get(BASE_URL)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userAuthenticationToken.getUserId().toString()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.avatar").value(user.getAvatar()));
    }

    @Test
    @WithMockAdmin
    void shouldGetAdminProfile_OK() throws Exception {
        final var admin = buildUser();
        final var userAuthenticationToken = authsProvider.getCurrentAuthentication();
        admin.setId(userAuthenticationToken.getUserId());
        when(userService.findById(authsProvider.getCurrentUserId())).thenReturn(admin);
        get(BASE_URL)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userAuthenticationToken.getUserId().toString()))
                .andExpect(jsonPath("$.username").value(admin.getUsername()))
                .andExpect(jsonPath("$.firstName").value(admin.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(admin.getLastName()))
                .andExpect(jsonPath("$.avatar").value(admin.getAvatar()));
    }

    @Test
    @WithMockUser
    void shouldUpdateProfile_OK() throws Exception {
        final var updatedUser = buildUser();
        final var token = authsProvider.getCurrentAuthentication().getUserId();
        updatedUser.setId(token);
        when(userService.update(any(), any())).thenReturn(updatedUser);
        put(BASE_URL, updatedUser)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedUser.getId().toString()))
                .andExpect(jsonPath("$.username").value(updatedUser.getUsername()))
                .andExpect(jsonPath("$.firstName").value(updatedUser.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(updatedUser.getLastName()))
                .andExpect(jsonPath("$.avatar").value(updatedUser.getAvatar()));
    }

    @Test
    @WithMockAdmin
    void shouldUpdateAdminProfile_OK() throws Exception {
        final var updatedUser = buildUser();
        final var token = authsProvider.getCurrentAuthentication().getUserId();
        updatedUser.setId(token);
        when(userService.update(any(), any())).thenReturn(updatedUser);
        put(BASE_URL, updatedUser)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedUser.getId().toString()))
                .andExpect(jsonPath("$.username").value(updatedUser.getUsername()))
                .andExpect(jsonPath("$.firstName").value(updatedUser.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(updatedUser.getLastName()))
                .andExpect(jsonPath("$.avatar").value(updatedUser.getAvatar()));
    }
}