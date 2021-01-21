package com.github.kacperkwiatkowski.holidayscheduler_backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "teamSquad", joinColumns = @JoinColumn(name = "teamId"))
    private List<Integer> teamSquad;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User teamLeader;

    public Team(String name, List<Integer> teamSquad, User teamLeader) {
        this.name = name;
        this.teamSquad = teamSquad;
        this.teamLeader = teamLeader;
    }
}
