package com.github.kacperkwiatkowski.holidayscheduler_backend.user;

import com.github.kacperkwiatkowski.holidayscheduler_backend.team.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    User findByEmail(String email);

    void deleteById(int id);

    User findFirstByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id IN :ids")
    List<User> findUsersWithIds(List<Integer> ids);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.daysOffLeft = u.daysOffLeft - :daysToSubtract WHERE u.id = :id")
    void subtractDaysOffFromUser(int id, int daysToSubtract);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.daysOffLeft = u.daysOffLeft + :daysToSubtract WHERE u.id = :id")
    void addDaysOffFromUser(int id, int daysToSubtract);


    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.team = null WHERE u.id = :id")
    void removeRelationToTeam(int id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.team = null WHERE u.team.id = :id")
    void clearUsersRelationToTeam(int id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.team = :team WHERE u.id = :userId")
    void updateUserTeamStatus(int userId, Team team);

    @Query("SELECT u FROM User u WHERE u.firstName LIKE %?1%"
            + " OR u.lastName LIKE %?1%")
    Page<User> findWithFilter(String filter, Pageable paging);

    @Query("SELECT u FROM User u WHERE u.team IS NULL AND u.roleType = 1")
    List<User> findAllAvailableTeamLeaders();
}
