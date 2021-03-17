package com.github.kacperkwiatkowski.team;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface TeamRepository extends JpaRepository<Team, Integer> {

    Team findById(int id);

    @Query("SELECT t FROM Team t WHERE t.teamLeader.firstName LIKE %?1%"
            + " OR t.teamLeader.lastName LIKE %?1%"
            + " OR t.name LIKE %?1%")
    Page<Team> findWithFilter(String filter, Pageable paging);
//
//    @Transactional
//    @Modifying
//    @Query("UPDATE User u SET u.com.github.kacperkwiatkowski.team = :com.github.kacperkwiatkowski.team WHERE u.id = :userId")
//    Team updateUserTeamStatus(int userId, Team com.github.kacperkwiatkowski.team);



}
