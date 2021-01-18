package com.github.kacperkwiatkowski.holidayscheduler_backend.model;

import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.LeaveType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "leaves", uniqueConstraints = {
        @UniqueConstraint(columnNames = "leaves_id")
})
public class Leave implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leaves_id", nullable = false)
    private int id;

    @Column(name = "leaves_firstDay", nullable = false)
    private LocalDate firstDay;

    @Column(name = "leaves_lastDay", nullable = false)
    private LocalDate lastDay;

    @Column(name = "leaves_isAccepted",nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean isAccepted;

    @Enumerated(EnumType.ORDINAL)
    private LeaveType leaveType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Leave() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(LocalDate firstDay) {
        this.firstDay = firstDay;
    }

    public LocalDate getLastDay() {
        return lastDay;
    }

    public void setLastDay(LocalDate lastDay) {
        this.lastDay = lastDay;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Leave)) return false;
        Leave leave = (Leave) o;
        return id == leave.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DayOff{" +
                "id=" + id +
                ", firstDay=" + firstDay +
                ", lastDay=" + lastDay +
                ", isAccepted=" + isAccepted +
                ", user=" + user +
                '}';
    }
}
