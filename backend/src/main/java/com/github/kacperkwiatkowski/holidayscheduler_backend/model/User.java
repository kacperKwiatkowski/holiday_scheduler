package com.github.kacperkwiatkowski.holidayscheduler_backend.model;

import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.RoleType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id"),
        @UniqueConstraint(columnNames = "user_email")
})
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private int id;

    @Column(name = "user_email", unique = true, nullable = false)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_firstName", nullable = false)
    private String firstName;

    @Column(name = "user_lastName", nullable = false)
    private String lastName;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_levelOfAccess", nullable = false)
    private RoleType roleType;

    @Column(name = "user_daysOffLeft", nullable = false)
    private int daysOffLeft;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "user")
    private Set<Leave> daysOff;

    @Column(name = "user_isTeamleader")
    private boolean isTeamLeader;

    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Team team;

    public User() {
    }

    public User(String email, String password, String firstName, String lastName, RoleType roleType, int daysOffLeft) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleType = roleType;
        this.daysOffLeft = daysOffLeft;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RoleType getRole() {
        return roleType;
    }

    public void setRole(RoleType roleType) {
        this.roleType = roleType;
    }

    public int getDaysOffLeft() {
        return daysOffLeft;
    }

    public void setDaysOffLeft(int daysOffLeft) {
        this.daysOffLeft = daysOffLeft;
    }

    public Set<Leave> getDaysOff() {
        return daysOff;
    }

    public void setDaysOff(Set<Leave> daysOff) {
        this.daysOff = daysOff;
    }

    public boolean isTeamLeader() {
        return isTeamLeader;
    }

    public void setTeamLeader(boolean teamLeader) {
        isTeamLeader = teamLeader;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
