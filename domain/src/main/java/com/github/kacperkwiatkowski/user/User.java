package com.github.kacperkwiatkowski.user;

import com.github.kacperkwiatkowski.enums.RoleType;
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

    private String imageUrl;

    Optional<String> getUserOptionalOfUserImageUrl() {
        return Optional.ofNullable(imageUrl);
    }
}
