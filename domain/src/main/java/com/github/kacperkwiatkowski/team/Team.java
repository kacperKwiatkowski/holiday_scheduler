package com.github.kacperkwiatkowski.team;

import com.github.kacperkwiatkowski.user.query.SimpleUserQueryDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TeamSquad", joinColumns = @JoinColumn(name = "teamId"))
    private List<Integer> teamSquad;

    @OneToOne(fetch = FetchType.EAGER)
    private SimpleUserQueryDto teamLeader;


}
