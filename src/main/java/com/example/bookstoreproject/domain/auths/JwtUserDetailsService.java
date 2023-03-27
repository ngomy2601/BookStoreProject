package com.example.bookstoreproject.domain.auths;

import com.example.bookstoreproject.domain.role.Role;
import com.example.bookstoreproject.persistence.role.RoleStore;
import com.example.bookstoreproject.persistence.user.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.bookstoreproject.domain.user.UserError.supplyUserNotFound;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserStore userStore;
    private final RoleStore roleStore;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userStore.findByUsername(username)
                .map(this::buildUser)
                .orElseThrow(supplyUserNotFound(username));
    }

    private User buildUser(final com.example.bookstoreproject.domain.user.User user) {
        final Optional<Role> role = roleStore.findById(user.getRoleId());
        return new JwtUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(role.get().getName()))
        );
    }
}
