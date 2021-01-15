package com.github.kacperkwiatkowski.holidayscheduler_backend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
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

    @Column(name = "user_levelOfAccess", nullable = false)
    private int levelOfAccess;

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

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLevelOfAccess(int levelOfAccess) {
        this.levelOfAccess = levelOfAccess;
    }

    public void setDaysOffLeft(int daysOffLeft) {
        this.daysOffLeft = daysOffLeft;
    }

    public void setDaysOff(Set<Leave> daysOff) {
        this.daysOff = daysOff;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getLevelOfAccess() {
        return levelOfAccess;
    }

    public int getDaysOffLeft() {
        return daysOffLeft;
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

    public Set<Leave> getDaysOff() {
        return daysOff;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", levelOfAccess=" + levelOfAccess +
                ", daysOffLeft=" + daysOffLeft +
                '}';
    }
}
