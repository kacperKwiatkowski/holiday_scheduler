package com.github.kacperkwiatkowski.holidayscheduler_backend.convertors;

import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.RoleType;

public class RoleTypeConvertor {

    public static RoleType convertToEnum(String string) {
        return RoleType.valueOf(string.toUpperCase());
    }

    public static String convertToString(RoleType roleType) {
        return roleType.toString();
    }
}
