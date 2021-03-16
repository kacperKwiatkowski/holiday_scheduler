package com.github.kacperkwiatkowski.holidayscheduler_backend.security;

public enum RoleAuthorities {
    EMPLOYEE_CREATE("employee:create"),
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_UPDATE("employee:update"),
    EMPLOYEE_DELETE("employee:delete"),
    TEAM_LEADER_CREATE("teamLeader:create"),
    TEAM_LEADER_READ("teamLeader:read"), //USEFULL?
    TEAM_LEADER_UPDATE("teamLeader:update"),
    TEAM_LEADER_DELETE("teamLeader:delete"),
    HR_CREATE("hr:create"),
    HR_READ("hr:read"),
    HR_UPDATE("hr:update"),
    HR_DELETE("hr:delete"),
    ADMIN_CREATE("admin:create"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),
    VACATION_CREATE("vacation:create"),
    VACATION_READ("vacation:read"),
    VACATION_NOT_ACCEPTED_EDIT("vacation:notAcceptedEdit"),
    VACATION_ACCEPTED_EDIT("vacation:acceptedEDit"),
    VACATION_DELETE("vacation:delete"),
    TEAM_CREATE("team:create"),
    TEAM_READ("team:read"),
    TEAM_UPDATE("team:update"),
    TEAM_DELETE("team:delete"),
    SELF_EDIT("self:edit"),
    NATIONAL_HOLIDAY_MANAGEMENT("nationalHolidays:management");

    private final String permission;

    RoleAuthorities(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
