package com.github.kacperkwiatkowski.holidayscheduler_backend.utils.roleConfig;

public enum RolePermissionType {
    EMPLOYEE_CREATE("student:create"),
    EMPLOYEE_READ("student:read"),
    EMPLOYEE_UPDATE("student:update"),
    EMPLOYEE_DELETE("student:delete"),
    TEAM_LEADER_CREATE("teamLeader:create"),
    TEAM_LEADER_READ("teamLeader:read"),
    TEAM_LEADER_UPDATE("teamLeader:update"),
    TEAM_LEADER_DELETE("teamLeader:delete"),
    HR_CREATE("hr:create"),
    HR_READ("hr:read"),
    HR_UPDATE("hr:update"),
    HR_DELETE("hr:delete"),
    ADMIN_CREATE("admin:create"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete");

    private final String permission;

    RolePermissionType(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
