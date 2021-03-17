package com.github.kacperkwiatkowski.holidayscheduler_backend.user;

import com.github.kacperkwiatkowski.holidayscheduler_backend.convertors.RoleTypeConvertor;
import com.github.kacperkwiatkowski.holidayscheduler_backend.security.RoleType;
import com.github.kacperkwiatkowski.holidayscheduler_backend.user.dto.UserDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private RoleType roleType;

    @Column(nullable = false)
    private int daysOffLeft;

//    @OneToMany(mappedBy = "user",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//            )
//    private List<Vacation> vacations;

//    @ManyToOne(cascade = CascadeType.MERGE,
//            fetch = FetchType.EAGER)
//    private Team team;

    private String imageUrl;

    UserDto mapToDto() {
        return UserDto.builder()
                .id(id)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .daysOffLeft(daysOffLeft)
                .roleType(RoleTypeConvertor.convertToString(roleType))
                .build();
    }

    Optional<String> getUserOptionalOfUserImageUrl() {
        return Optional.ofNullable(imageUrl);
    }
}
