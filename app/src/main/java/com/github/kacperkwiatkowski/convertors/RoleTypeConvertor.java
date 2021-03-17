package com.github.kacperkwiatkowski.convertors;

import com.github.kacperkwiatkowski.security.RoleType;

public class RoleTypeConvertor {

    public static RoleType convertToEnum(String string) {

        RoleType a = RoleType.valueOf(string.toUpperCase());
        return a;
    }

    public static String convertToString(RoleType roleType) {
        return roleType.toString();
    }
}
