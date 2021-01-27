package com.github.kacperkwiatkowski.holidayscheduler_backend.model;

import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.leaveConfig.LeaveType;
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
    private LeaveType leaveType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_Id")
    private User user;

    public Vacation(LocalDate firstDay, LocalDate lastDay, boolean isAccepted, LeaveType leaveType, User user) {
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.isAccepted = isAccepted;
        this.leaveType = leaveType;
        this.user = user;
    }
}
