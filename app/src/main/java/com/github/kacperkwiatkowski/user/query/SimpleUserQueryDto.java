package com.github.kacperkwiatkowski.user.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;

@Entity
@Getter
@Setter

@Table(name = "User")
public class SimpleUserQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @PersistenceConstructor

    public SimpleUserQueryDto() {
    }
}
