package com.github.kacperkwiatkowski.convertors;

import com.github.kacperkwiatkowski.enums.VacationType;

public class VacationTypeConvertor {

    public static VacationType convertToEnum(String string) {
        return VacationType.valueOf(string.toUpperCase());
    }

    public static String convertToString(VacationType vacationType) {
        return vacationType.toString();
    }
}
