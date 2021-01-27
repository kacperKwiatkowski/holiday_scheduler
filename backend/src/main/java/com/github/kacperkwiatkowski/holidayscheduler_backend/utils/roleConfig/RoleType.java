package com.github.kacperkwiatkowski.holidayscheduler_backend.utils.roleConfig;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.github.kacperkwiatkowski.holidayscheduler_backend.utils.roleConfig.RoleAuthorities.EMPLOYEE_READ;

public enum RoleType {
    EMPLOYEE(Sets.newHashSet(EMPLOYEE_READ)),
    TEAM_LEADER(Sets.newHashSet()),
    HR(Sets.newHashSet()),
    ADMIN(Sets.newHashSet());


    private final Set<RoleAuthorities> permissions;

    RoleType(Set<RoleAuthorities> permissions) {
        this.permissions = permissions;
    }

    public Set<RoleAuthorities> getPermissions() {
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
