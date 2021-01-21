package com.github.kacperkwiatkowski.holidayscheduler_backend.model;

import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.RoleType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable{
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

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
            )
    private List<Vacation> vacations;

    private boolean hasTeam;

    @ManyToOne(cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER)
    private Team team;

    public User(String email, String password, String firstName, String lastName, RoleType roleType, int daysOffLeft) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleType = roleType;
        this.daysOffLeft = daysOffLeft;
    }
}
