package com.github.kacperkwiatkowski.user;

import com.github.kacperkwiatkowski.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/user")
class UserController {

    UserController(UserFacade userFacade, UserRepository userRepository) {
        this.userFacade = userFacade;
        this.userRepository = userRepository;
    }

    private final UserFacade userFacade;
    private final UserRepository userRepository;

    @PostMapping(path = "/create")
    @PreAuthorize("hasAuthority('employee:create')")
    @ResponseBody
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        log.info("Controller 'createUser' initiated.");
        return ResponseEntity.ok(userFacade.createUser(userDto));
    }

    @GetMapping(path = "/read/{id}")
    @PreAuthorize("hasAuthority('employee:read')")
    ResponseEntity<UserDto> getUser(@PathVariable int id) throws ObjectNotFoundException {
        log.info("Controller 'getUser' initiated.");
        return new ResponseEntity<UserDto>(userFacade.readUser(id), HttpStatus.OK);
    }

    @GetMapping(path = "/read/logged")
    @PreAuthorize("hasAuthority('self:edit')")
    ResponseEntity<UserDto> getUser(@RequestParam String email) throws ObjectNotFoundException {
        log.info("Controller 'getUser' initiated.");
        return new ResponseEntity<UserDto>(userFacade.readUser(email), HttpStatus.OK);
    }


    @GetMapping(path = "/read/all")
    @PreAuthorize("hasAuthority('employee:read')")
    ResponseEntity<List<UserDto>> getAllUser() throws ObjectNotFoundException {
        log.info("Controller 'getAllUser' initiated.");
        return new ResponseEntity<List<UserDto>>(userFacade.getAllUsers(), HttpStatus.OK);
    }

    @PatchMapping(path = "/update/{id}")
    @PreAuthorize("hasAuthority('employee:update')")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto userToUpdate) throws ObjectNotFoundException {
            log.info("Controller 'updateUser' initiated.");
            return ResponseEntity.ok(userFacade.updateUser(userToUpdate));

    }

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('employee:delete')")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserDto> deleteUser(
            @PathVariable("id") int id,String token
    ) throws ObjectNotFoundException {

        log.info("Controller 'deleteUser' initiated.");
        return ResponseEntity.ok(userFacade.deleteUser(id));
    }


    @GetMapping(path = "/page")
    ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "lastName") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam(defaultValue = "") String filter)
    {
        log.info("Controller 'getAllUsers' initiated.");
        return new ResponseEntity<List<UserDto>>(userFacade.listAll(pageNum, pageSize, sortBy, sortOrder, filter), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Long> userCount(){
        log.info("Controller 'userCount' initiated.");
        return ResponseEntity.ok(userRepository.count());
    }
}