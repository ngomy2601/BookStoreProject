package com.example.bookstoreproject.domain.auths;

import com.example.bookstoreproject.domain.role.Role;
import com.example.bookstoreproject.persistence.role.RoleStore;
import com.example.bookstoreproject.persistence.user.UserEntity;
import com.example.bookstoreproject.persistence.user.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.bookstoreproject.domain.user.UserError.supplyUserNotFound;
import static com.example.bookstoreproject.persistence.user.UserEntityMapper.toUserEntity;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserStore userStore;
    private final RoleStore roleStore;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userStore.findByUsername(username)
                .map(user -> buildUser(toUserEntity(user)))
                .orElseThrow(supplyUserNotFound(username));
    }

    private User buildUser(final UserEntity userEntity) {
        final Role role = roleStore.findById(userEntity.getRoleId());
        return new JwtUserDetails(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword(), List.of(new SimpleGrantedAuthority(role.getName())));
    }
}
