package com.example.bookstoreproject.domain.user;

import com.example.bookstoreproject.domain.auths.JwtUserDetails;
import com.example.bookstoreproject.domain.auths.JwtUserDetailsService;
import com.example.bookstoreproject.persistence.role.RoleStore;
import com.example.bookstoreproject.persistence.user.UserStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.bookstoreproject.fakes.JwtUserDetailFakes.buildJwtUserDetails;
import static com.example.bookstoreproject.fakes.RoleFakes.buildRole;
import static com.example.bookstoreproject.fakes.SocialUserFakes.buildSocialUser;
import static com.example.bookstoreproject.fakes.UserFakes.buildUser;
import static com.example.bookstoreproject.fakes.UserFakes.buildUsers;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserStore userStore;

    @Mock
    private JwtUserDetailsService jwtUserDetailsService;

    @Mock
    private RoleStore roleStore;

    @Mock
    private FacebookService facebookService;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldFindAll_OK() {
        final var expected = buildUsers();

        when(userStore.findAll())
                .thenReturn(expected);

        final var actual = userService.findAll();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getUsername(), actual.get(0).getUsername());
        assertEquals(expected.get(0).getPassword(), actual.get(0).getPassword());
        assertEquals(expected.get(0).getFirstName(), actual.get(0).getFirstName());
        assertEquals(expected.get(0).getLastName(), actual.get(0).getLastName());
        assertEquals(expected.get(0).getAvatar(), actual.get(0).getAvatar());
        assertEquals(expected.get(0).getRoleId(), actual.get(0).getRoleId());
        
        verify(userStore).findAll();
    }

    @Test
    void shouldFindById_OK() {
        final var user = buildUser();
        when(userStore.findById(user.getId())).thenReturn(Optional.of(user));
        final var foundUser = userService.findById(user.getId());
        assertEquals(user, foundUser);
        verify(userStore).findById(user.getId());
    }

    @Test
    void shouldCreateUser_OK() {
        final var user = buildUser();
        when(userStore.create(user)).thenReturn(user);
        final var createdUser = userService.create(user);
        assertEquals(user.getId(), createdUser.getId());
        assertEquals(user.getUsername(), createdUser.getUsername());
        assertEquals(user.getFirstName(), createdUser.getFirstName());
        assertEquals(user.getLastName(), createdUser.getLastName());
        assertEquals(user.getAvatar(), createdUser.getAvatar());
        assertEquals(user.getRoleId(), createdUser.getRoleId());
        verify(userStore).create(user);
    }

    @Test
    void shouldUpdateUser_OK() {
        final var user = buildUser();
        final var updatedUser = buildUser()
                .withId(user.getId())
                .withRoleId(user.getRoleId());
        when(userStore.findById(user.getId())).thenReturn(Optional.of(user));
        when(userStore.update(user)).thenReturn(user);
        final var expected = userService.update(user.getId(), updatedUser);
        assertEquals(expected.getId(), updatedUser.getId());
        assertEquals(expected.getUsername(), updatedUser.getUsername());
        assertEquals(expected.getFirstName(), updatedUser.getFirstName());
        assertEquals(expected.getLastName(), updatedUser.getLastName());
        assertEquals(expected.getAvatar(), updatedUser.getAvatar());
        assertEquals(expected.getRoleId(), updatedUser.getRoleId());
    }

    @Test
    void shouldDeleteUser_OK() {
        final var user = buildUser();
        userService.delete(user.getId());
        verify(userStore).delete(user.getId());
    }

    @Test
    public void testLoginWithFacebook_UserExisted_OK() {
        final var accessToken = randomAlphabetic(6, 10);
        final var socialUser = buildSocialUser();
        final var user = buildUser();
        socialUser.setUsername(user.getUsername());

        when(facebookService.parseToken(anyString())).thenReturn(socialUser);
        when(userStore.findByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));
        final var userFound = userService.findByUsername(socialUser.getUsername());
        final JwtUserDetails userDetails = buildJwtUserDetails();
        userFound.setUsername(userDetails.getUsername());
        when(userService.loginWithFacebook(accessToken)).thenReturn(userDetails);
        when(jwtUserDetailsService.loadUserByUsername(userFound.getUsername())).thenReturn(userDetails);
        final var actual = jwtUserDetailsService.loadUserByUsername(userFound.getUsername());

        assertEquals(userDetails, actual);
    }

    @Test
    public void testLoginWithFacebook_UserEmpty_OK() {
        final var accessToken = randomAlphabetic(6, 10);
        final var socialUser = buildSocialUser();
        final var role = buildRole();
        final var user = buildUser();
        final JwtUserDetails userDetails = buildJwtUserDetails();
        user.setUsername(socialUser.getUsername());

        when(facebookService.parseToken(anyString())).thenReturn(socialUser);
        when(roleStore.findByName(anyString())).thenReturn(role);
        when(userService.loginWithFacebook(accessToken)).thenReturn(userDetails);

        when(userStore.create(any(User.class))).thenReturn(user);
        final var userCreated = userStore.create(user);

        when(jwtUserDetailsService.loadUserByUsername(userCreated.getUsername())).thenReturn(userDetails);

        final var actual = jwtUserDetailsService.loadUserByUsername(userCreated.getUsername());
        assertEquals(userDetails, actual);
    }
}