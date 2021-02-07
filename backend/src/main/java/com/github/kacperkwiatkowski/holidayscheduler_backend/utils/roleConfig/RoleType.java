package com.github.kacperkwiatkowski.holidayscheduler_backend.utils.roleConfig;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.github.kacperkwiatkowski.holidayscheduler_backend.utils.roleConfig.RoleAuthorities.*;

public enum RoleType {
    EMPLOYEE(
            Sets.newHashSet(
//                    EMPLOYEE_READ,
//                    TEAM_LEADER_READ,
//                    HR_READ,
//                    ADMIN_READ,
                    VACATION_CREATE,
                    VACATION_READ,
                    VACATION_NOT_ACCEPTED_EDIT,
                    TEAM_READ,
                    SELF_EDIT
            )),
    TEAM_LEADER(
            Sets.newHashSet(
                    EMPLOYEE_READ,
                    TEAM_LEADER_READ,
                    HR_READ,
                    ADMIN_READ,
                    VACATION_CREATE,
                    VACATION_READ,
                    VACATION_NOT_ACCEPTED_EDIT,
                    TEAM_READ,
                    SELF_EDIT
            )),
    HR(
            Sets.newHashSet(
                    EMPLOYEE_READ,
                    TEAM_LEADER_READ,
                    HR_READ,
                    ADMIN_READ,
                    VACATION_CREATE,
                    VACATION_READ,
                    VACATION_NOT_ACCEPTED_EDIT,
                    VACATION_ACCEPTED_EDIT,
                    VACATION_DELETE,
                    TEAM_CREATE,
                    TEAM_READ,
                    TEAM_UPDATE,
                    TEAM_DELETE,
                    SELF_EDIT
            )),
    ADMIN(
            Sets.newHashSet(
                    EMPLOYEE_CREATE,
                    EMPLOYEE_READ,
                    EMPLOYEE_UPDATE,
                    EMPLOYEE_DELETE,
                    TEAM_LEADER_CREATE,
                    TEAM_LEADER_READ,
                    TEAM_LEADER_UPDATE,
                    TEAM_LEADER_DELETE,
                    HR_CREATE,
                    HR_READ,
                    HR_UPDATE,
                    HR_DELETE,
                    ADMIN_CREATE,
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    VACATION_CREATE,
                    VACATION_READ,
                    VACATION_NOT_ACCEPTED_EDIT,
                    VACATION_ACCEPTED_EDIT,
                    VACATION_DELETE,
                    TEAM_CREATE,
                    TEAM_READ,
                    TEAM_UPDATE,
                    TEAM_DELETE,
                    SELF_EDIT
            ));


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
