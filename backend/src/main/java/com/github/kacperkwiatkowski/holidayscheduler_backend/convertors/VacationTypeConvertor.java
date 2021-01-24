package com.github.kacperkwiatkowski.holidayscheduler_backend.convertors;

import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.LeaveType;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.RoleType;

public class VacationTypeConvertor {

    public static LeaveType convertToEnum(String string) {
        return LeaveType.valueOf(string.toUpperCase());
    }

    public static String convertToString(LeaveType leaveType) {
        return leaveType.toString();
    }
}
