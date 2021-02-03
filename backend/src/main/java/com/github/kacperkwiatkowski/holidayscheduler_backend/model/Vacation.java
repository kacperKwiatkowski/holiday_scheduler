package com.github.kacperkwiatkowski.holidayscheduler_backend.model;

import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.VacationType;
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
public class Vacation implements Serializable {
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
    private User user;

    public Vacation(LocalDate firstDay, LocalDate lastDay, boolean isAccepted, VacationType vacationType, User user) {
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.isAccepted = isAccepted;
        this.vacationType = vacationType;
        this.user = user;
    }
}
