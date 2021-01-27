package com.github.kacperkwiatkowski.holidayscheduler_backend.utils.roleConfig;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.github.kacperkwiatkowski.holidayscheduler_backend.utils.roleConfig.RolePermissionType.*;

public enum RolePermissions {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE));

    private final Set<RolePermissionType> permissions;

    RolePermissions(Set<RolePermissionType> permissions) {
        this.permissions = permissions;
    }

    public Set<RolePermissionType> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
