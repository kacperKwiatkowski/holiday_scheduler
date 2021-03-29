package com.github.kacperkwiatkowski.vacation;

import com.github.kacperkwiatkowski.enums.VacationType;
import com.github.kacperkwiatkowski.user.query.SimpleUserQueryDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
class Vacation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(nullable = false)
    private LocalDate firstDay;

    @Column(nullable = false)
    private LocalDate lastDay;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isAccepted;

    @Enumerated(EnumType.ORDINAL)
    private VacationType vacationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_Id")
    private SimpleUserQueryDto user;

}
