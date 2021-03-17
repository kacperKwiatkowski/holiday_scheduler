package com.github.kacperkwiatkowski.user;

import com.github.kacperkwiatkowski.convertors.RoleTypeConvertor;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int daysOffLeft;
    private String roleType;
    private int teamId;

    public static UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .daysOffLeft(user.getDaysOffLeft())
                .roleType(RoleTypeConvertor.convertToString(user.getRoleType()))
                .build();
    }

}


