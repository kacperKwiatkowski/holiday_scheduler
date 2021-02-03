package com.github.kacperkwiatkowski.holidayscheduler_backend.convertors;

public class VacationTypeConvertor {

    public static com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.VacationType convertToEnum(String string) {
        return com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.VacationType.valueOf(string.toUpperCase());
    }

    public static String convertToString(com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.VacationType vacationType) {
        return vacationType.toString();
    }
}
