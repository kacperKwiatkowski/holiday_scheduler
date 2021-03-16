package com.github.kacperkwiatkowski.holidayscheduler_backend.team;

import com.github.kacperkwiatkowski.holidayscheduler_backend.team.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
interface TeamRepository extends JpaRepository<Team, Integer> {

    Team findById(int id);

    @Query("SELECT t FROM Team t WHERE t.teamLeader.firstName LIKE %?1%"
            + " OR t.teamLeader.lastName LIKE %?1%"
            + " OR t.name LIKE %?1%")
    Page<Team> findWithFilter(String filter, Pageable paging);

}
