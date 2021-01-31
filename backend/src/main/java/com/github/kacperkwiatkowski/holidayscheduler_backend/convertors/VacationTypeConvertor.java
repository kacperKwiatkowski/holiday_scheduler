package com.github.kacperkwiatkowski.holidayscheduler_backend.convertors;

import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.VacationType;

public class VacationTypeConvertor {

    public static VacationType convertToEnum(String string) {
        return VacationType.valueOf(string.toUpperCase());
    }

    public static String convertToString(VacationType vacationType) {
        return vacationType.toString();
    }
}
