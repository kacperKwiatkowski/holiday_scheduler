package com.github.kacperkwiatkowski.user;

import com.github.kacperkwiatkowski.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.enums.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserFacade {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFactory userFactory;

    UserFacade(UserRepository userRepository, PasswordEncoder passwordEncoder, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userFactory = userFactory;
    }

    public UserDto createUser(UserDto userToCreate){

        User user = userFactory.mapToEntity(userToCreate);
        String s = UUID.randomUUID().toString();
        log.info(s);
        user.setPassword(passwordEncoder.encode(s));
        return UserDto.mapToDto(userRepository.save(user));
    }

    public UserDto readUser(int id){
        Optional<User> user = Optional.ofNullable(userRepository.findById(id));
        if(user.isPresent()){
            return UserDto.mapToDto(user.get());
        } else {
            throw ObjectNotFoundException.createWith("Id does not exist.");
        }
    }

    public UserDto readUser(String email){
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if(user.isPresent()){
            return UserDto.mapToDto(user.get());
        } else {
            throw ObjectNotFoundException.createWith("Id does not exist.");
        }
    }

    public List<UserDto> getAllUsers(){
        Optional<List<User>> users = Optional.ofNullable(userRepository.findAll());
        if(users.isPresent()){
            return users.get().stream().map(UserDto::mapToDto).collect(Collectors.toList());
        } else {
            throw ObjectNotFoundException.createWith("The list is empty.");
        }
    }

    public UserDto updateUser(UserDto userToUpdate){
        Optional<User> foundUser = Optional.ofNullable(userRepository.findById(userToUpdate.getId()));
        if(foundUser.isPresent()){
            User user = userFactory.mapToEntity(userToUpdate);
            userRepository.save(user);
            log.info("User: " + userToUpdate.getId() + "updated successfully");
            return userToUpdate;
        } else {
            log.info("User: " + userToUpdate.getId()  + "updated unsuccessfully");
            throw ObjectNotFoundException.createWith("PATCH impossible, com.github.kacperkwiatkowski.user with such id doesnt exists.");
        }
    }

    public UserDto deleteUser(int id) throws ObjectNotFoundException {

        Optional<User> userToDelete = Optional.ofNullable(userRepository.findById(id));
        if(userToDelete.isPresent()){

            //checkIfTeamLeaderWithTeam(userToDelete);
            userRepository.deleteById(id);
            return UserDto.mapToDto(userToDelete.get());

        } else {
            throw new ObjectNotFoundException("Deletion unsuccessful. Id does not exist.");
        }
    }

    public List<UserDto> listAll(Integer pageNum, Integer pageSize, String sortBy, String sortOrder, String filter) {

        Pageable paging;

        if(sortOrder.equals("ASC")){
            paging = PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, sortBy);
        } else {
            paging = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, sortBy);
        }

        Page<User> pagedResult;

        if(filter.length()<3){
            pagedResult = userRepository.findAll(paging);
        } else {
            pagedResult = userRepository.findWithFilter(filter, paging);
        }

        if(pagedResult.hasContent()) {
            return pagedResult.stream().map(UserDto::mapToDto).collect(Collectors.toList());
        } else {
            throw new ObjectNotFoundException("Pagination impossible");
        }
    }
//
//    private void checkIfTeamLeaderWithTeam(Optional<User> userToDelete) throws ObjectNotFoundException {
//        if(userToDelete.get().getTeam() != null & userToDelete.get().getRoleType()== RoleType.TEAM_LEADER){
//            throw new ObjectNotFoundException("Team leader has a com.github.kacperkwiatkowski.team, delete com.github.kacperkwiatkowski.team first or change com.github.kacperkwiatkowski.team leader and then delete the com.github.kacperkwiatkowski.user.");
//        }
//    }
//
//
//
//
//    public void updateUserTeamStatus(int teamLeaderId, Team save) {
//        userRepository.updateUserTeamStatus(teamLeaderId, save);
//    }
//
//    public List<UserDto> fetchTeamSquad(List<Integer> teamSquadIds) {
//        List<User> foundUsers = userRepository.findUsersWithIds(teamSquadIds);
//        return foundUsers.stream().map(User::mapToDto).collect(Collectors.toList());
//    }
//
//    public List<UserDto> findAvailableTeamLeaders() {
//        return userRepository.findAllAvailableTeamLeaders().stream().map(User::mapToDto).collect(Collectors.toList());
//    }
//
//    public void switchTeamLeaders(int teamLeaderId, Team com.github.kacperkwiatkowski.team) {
//        User oldTeamLeader = userRepository.findById(teamLeaderId);
//        oldTeamLeader.setTeam(null);
//        userRepository.save(oldTeamLeader);
//
//        userRepository.updateUserTeamStatus(com.github.kacperkwiatkowski.team.getTeamLeader().getId(), com.github.kacperkwiatkowski.team);
//    }
//
//    public void clearUsersRelationToTeam(int id) {
//        userRepository.clearUsersRelationToTeam(id);
//    }
//
//    public UserDto removeUserFromTeam(int userId) {
//        Optional<User> foundUser = Optional.ofNullable(userRepository.findById(userId));
//        if (foundUser.isPresent()){
//            userRepository.removeRelationToTeam(userId);
//            log.info("User removal from com.github.kacperkwiatkowski.team successful.");
//            return foundUser.get().mapToDto();
//        } else {
//            throw new ObjectNotFoundException("REMOVAL impossible, object not found.");
//        }
//    }
//
//    public Optional<String> getUserOptionalOfUserImageUrl(int userId) {
//        return userRepository.fetchImageUrl(userId);
//    }

    public UserDto findById(int id) {
        return UserDto.mapToDto(userRepository.findById(id));
    }

    public void save(UserDto user) {
        userRepository.save(userFactory.mapToEntity(user));
    }

    public void save(UserSecurityDto user) {
        userRepository.save(userFactory.mapToEntity(user));
    }


    public void addDaysOffFromUser(int id, int daysBetween) {
        userRepository.addDaysOffFromUser(id, daysBetween);
    }

    public void subtractDaysOffFromUser(int id, int daysBetween) {
        userRepository.subtractDaysOffFromUser(id, daysBetween);
    }

    public String fetchEmail(String email){
        return userRepository.fetchUsersEmail(email);
    }

    public String fetchPassword(String email){
        return userRepository.fetchUsersPassword(email);
    }

    public RoleType fetchRoleType(String email){
        return userRepository.fetchUsersRole(email);
    }
}
