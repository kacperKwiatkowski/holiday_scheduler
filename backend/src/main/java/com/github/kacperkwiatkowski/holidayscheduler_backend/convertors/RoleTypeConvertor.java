package com.github.kacperkwiatkowski.holidayscheduler_backend.convertors;

import com.github.kacperkwiatkowski.holidayscheduler_backend.roles.RoleType;

public class RoleTypeConvertor {

    public static RoleType convertToEnum(String string) {

        RoleType a = RoleType.valueOf(string.toUpperCase());
        return a;
    }

    public static String convertToString(RoleType roleType) {
        return roleType.toString();
    }
}
