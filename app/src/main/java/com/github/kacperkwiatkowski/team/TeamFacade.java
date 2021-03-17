package com.github.kacperkwiatkowski.team;

import com.github.kacperkwiatkowski.user.UserFacade;
import com.github.kacperkwiatkowski.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TeamFacade {

    private final TeamRepository teamRepository;
    private final TeamFactory teamFactory;
    private final UserFacade userFacade;

    TeamFacade(TeamRepository teamRepository, TeamFactory teamFactory, UserFacade userFacade) {
        this.teamRepository = teamRepository;
        this.teamFactory = teamFactory;
        this.userFacade = userFacade;
    }

//    @Transactional
//    public TeamDto createTeam(TeamDto teamToCreate){
//        userService.updateUserTeamStatus(teamToCreate.getTeamLeaderId(), teamRepository.save(teamFactory.mapToEntity(teamToCreate)));
//        return teamToCreate;
//    }

//    @Transactional
//    public TeamDto addMemberToTeam(int id, TeamDto teamToUpdateWithUser){
//        Optional<Team> foundTeam = Optional.ofNullable(teamRepository.findById(teamToUpdateWithUser.getId()));
//
//        //TODO Apply frontend
//        if(foundTeam.isPresent()){
//            List<Integer> usersInTeam = teamToUpdateWithUser.getUserIds();
//            usersInTeam.add(id);
//            teamToUpdateWithUser.setUserIds(usersInTeam);
//            Team com.github.kacperkwiatkowski.team = teamFactory.mapToEntity(teamToUpdateWithUser);
//            teamRepository.save(com.github.kacperkwiatkowski.team);
//
//
//            userService.updateUserTeamStatus(id, com.github.kacperkwiatkowski.team);
//
//            return teamToUpdateWithUser;
//        } else {
//            throw new ObjectNotFoundException("Such com.github.kacperkwiatkowski.team doesn't exist");
//        }
//
//    }


    public TeamDto readTeam(int teamId){
        Optional<Team> foundTeam = Optional.ofNullable(teamRepository.findById(teamId));
        if(foundTeam.isPresent()){
            return foundTeam.get().mapToDto();
        } else {
            throw ObjectNotFoundException.createWith("Team with such ID does not exist.");
        }
    }

    public List<TeamDto> readEachTeam(){
        List<Team> foundTeams = teamRepository.findAll();
        if(foundTeams.size()!=0){
            return foundTeams.stream().map(Team::mapToDto).collect(Collectors.toList());
        } else {
            throw ObjectNotFoundException.createWith("Team with such ID does not exist.");
        }
    }

//    public List<UserDto> readTeamMembers(int id){
//        Optional<Team> foundTeam = Optional.ofNullable(teamRepository.findById(id));
//        if(foundTeam.isPresent()){
//            return userService.fetchTeamSquad(foundTeam.get().getTeamSquad());
//        } else {
//            throw ObjectNotFoundException.createWith("Team with such ID does not exist.");
//        }
//    }

//    public List<UserDto> readAvailableTeamLeaders(){
//        return userService.findAvailableTeamLeaders();
//    }

//    @Transactional
//    public TeamDto updateTeam(TeamDto teamToUpdate) throws ObjectNotFoundException {
//
//        Optional<Team> foundTeam = Optional.ofNullable(teamRepository.findById(teamToUpdate.getId()));
//        if(foundTeam.isPresent()){
//            int oldTeamLeaderId = foundTeam.get().getTeamLeader().getId();
//
//            Team com.github.kacperkwiatkowski.team = teamFactory.mapToEntity(teamToUpdate);
//
//            List<Integer> teamSquad = com.github.kacperkwiatkowski.team.getTeamSquad();
//            teamSquad.remove(Integer.valueOf(oldTeamLeaderId));
//            teamSquad.add(teamToUpdate.getTeamLeaderId());
//            com.github.kacperkwiatkowski.team.setTeamSquad(teamSquad);
//
//            userService.switchTeamLeaders(teamToUpdate.getTeamLeaderId(), com.github.kacperkwiatkowski.team);
//
//
//            teamRepository.save(com.github.kacperkwiatkowski.team);
//
//            return teamToUpdate;
//        } else {
//            throw ObjectNotFoundException.createWith("Team with such ID does not exist.");
//        }
//    }

//    @Transactional
//    public TeamDto deleteTeam(int id) {
//        Optional<Team> foundTeam = Optional.ofNullable(teamRepository.findById(id));
//        if (foundTeam.isPresent()){
//            userService.clearUsersRelationToTeam(id);
//            teamRepository.deleteById(id);
//            log.info("Deletion successful.");
//            return foundTeam.get().mapToDto();
//        } else {
//            throw new ObjectNotFoundException("DELETION impossible, object not found.");
//        }
//    }

    public List<TeamDto> listAll(Integer pageNum, Integer pageSize, String sortBy, String sortOrder, String filter) {

        Pageable paging;

        if(sortOrder.equals("ASC")){
            paging = PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, sortBy);
        } else {
            paging = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, sortBy);
        }

        Page<Team> pagedResult;

        if(filter.length()<3){
            pagedResult = teamRepository.findAll(paging);
        } else {
            pagedResult = teamRepository.findWithFilter(filter, paging);
        }

        if(pagedResult.hasContent()) {
            return pagedResult.stream().map(Team::mapToDto).collect(Collectors.toList());
        } else {
            throw new ObjectNotFoundException("Pagination impossible");
        }
    }



//    @Transactional
//    public UserDto removeFromTeam(int userId){
//        UserDto removedUser = userService.removeUserFromTeam(userId);
//        updateTeamSquad(removedUser.getTeamId(), userId);
//        return removedUser;
//
//    }

    private void updateTeamSquad(int teamID, int userId) {
        //TODO Try to replace this method with a single query
        Team userTeam = teamRepository.findById(teamID);
        List<Integer> teamUsers = userTeam.getTeamSquad();
        teamUsers = teamUsers.stream().filter(u -> u!=userId).collect(Collectors.toList());
        userTeam.setTeamSquad(teamUsers);
        teamRepository.save(userTeam);
    }


}
