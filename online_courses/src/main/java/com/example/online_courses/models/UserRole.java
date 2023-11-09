package com.example.online_courses.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Getter
@AllArgsConstructor
public enum UserRole {
    USER(Set.of(Permission.READ)),
    TEACHER(Set.of(Permission.READ, Permission.MODIFICATION)),
    ADMIN(Set.of(Permission.READ, Permission.MODIFICATION));

    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> grantedAuthority() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(toSet());
    }
}