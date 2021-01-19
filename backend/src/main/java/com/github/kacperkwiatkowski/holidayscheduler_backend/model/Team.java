package com.github.kacperkwiatkowski.holidayscheduler_backend.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", unique = true, nullable = false)
    private int id;

    @Column(name = "team_name")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "listUsersInTeam", joinColumns = @JoinColumn(name = "team_id"))
    private List<String> userEmail;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User teamLeader;

    public Team() {
    }

    public Team(String name, List<String> userEmail, User teamLeader) {
        this.name = name;
        this.userEmail = userEmail;
        this.teamLeader = teamLeader;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(List<String> userEmail) {
        this.userEmail = userEmail;
    }

    public User getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(User teamLeader) {
        this.teamLeader = teamLeader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return Objects.equals(userEmail, team.userEmail) &&
                Objects.equals(teamLeader, team.teamLeader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmail, teamLeader);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userEmail=" + userEmail +
                ", teamLeader=" + teamLeader +
                '}';
    }
}
