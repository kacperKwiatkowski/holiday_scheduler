package com.github.kacperkwiatkowski.holidayscheduler_backend.service;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.mappers.UserMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.roles.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TeamService teamService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, TeamService teamService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.teamService = teamService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(UserDto userToCreate){

        User user = userMapper.mapToEntity(userToCreate);
        String s = UUID.randomUUID().toString();
        log.info(s);
        user.setPassword(passwordEncoder.encode(s));
        return userMapper.mapToDto(userRepository.save(user));
    }

    public UserDto readUser(int id){
        Optional<User> user = Optional.ofNullable(userRepository.findById(id));
        if(user.isPresent()){
            return userMapper.mapToDto(user.get());
        } else {
            throw ObjectNotFoundException.createWith("Id does not exist.");
        }
    }

    public UserDto readUser(String email){
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if(user.isPresent()){
            return userMapper.mapToDto(user.get());
        } else {
            throw ObjectNotFoundException.createWith("Id does not exist.");
        }
    }

    public List<UserDto> getAllUsers(){
        Optional<List<User>> users = Optional.ofNullable(userRepository.findAll());
        if(users.isPresent()){
            return users.get().stream().map(userMapper::mapToDto).collect(Collectors.toList());
        } else {
            throw ObjectNotFoundException.createWith("The list is empty.");
        }
    }

    public UserDto updateUser(UserDto userToUpdate){
        Optional<User> foundUser = Optional.ofNullable(userRepository.findById(userToUpdate.getId()));
        if(foundUser.isPresent()){
            User user = userMapper.mapToEntity(userToUpdate);
            userRepository.save(user);
            log.info("User: " + userToUpdate.getId() + "updated successfully");
            return userToUpdate;
        } else {
            log.info("User: " + userToUpdate.getId()  + "updated unsuccessfully");
            throw ObjectNotFoundException.createWith("PATCH impossible, user with such id doesnt exists.");
        }
    }

    public UserDto deleteUser(int id) throws ObjectNotFoundException {

        Optional<User> userToDelete = Optional.ofNullable(userRepository.findById(id));
        if(userToDelete.isPresent()){

            checkIfTeamLeaderWithTeam(userToDelete);
            removeFromTeam(id, userToDelete);
            userRepository.deleteById(id);
            return userMapper.mapToDto(userToDelete.get());

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
            return pagedResult.stream().map(userMapper::mapToDto).collect(Collectors.toList());
        } else {
            throw new ObjectNotFoundException("Pagination impossible");
        }
    }

    private void checkIfTeamLeaderWithTeam(Optional<User> userToDelete) throws ObjectNotFoundException {
        if(userToDelete.get().getTeam() != null & userToDelete.get().getRoleType()== RoleType.TEAM_LEADER){
            throw new ObjectNotFoundException("Team leader has a team, delete team first or change team leader and then delete the user.");
        }
    }


    private void removeFromTeam(int id, Optional<User> userToDelete) {
        if(userToDelete.get().getTeam()!=null){
            teamService.removeFromTeam(id);
        }
    }
}
