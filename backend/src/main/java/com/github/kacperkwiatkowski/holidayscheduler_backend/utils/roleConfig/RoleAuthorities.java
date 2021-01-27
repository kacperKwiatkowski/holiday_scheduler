package com.github.kacperkwiatkowski.holidayscheduler_backend.utils.roleConfig;

import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.ApplicationUserPermission;
import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.github.kacperkwiatkowski.holidayscheduler_backend.utils.ApplicationUserPermission.*;

public enum RoleAuthorities {
    EMPLOYEE(Sets.newHashSet(EMPLOYEE_READ)),
    TEAM_LEADER(Sets.newHashSet(EMPLOYEE_READ)),
    HR(Sets.newHashSet()),
    ADMIN(Sets.newHashSet());


    private final Set<ApplicationUserPermission> permissions;

    RoleAuthorities(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
