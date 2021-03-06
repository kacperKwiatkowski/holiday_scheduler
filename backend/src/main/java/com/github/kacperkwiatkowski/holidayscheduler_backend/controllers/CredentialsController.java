package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/credentials")
public class CredentialsController {

    private final UserRepository userRepository;

    public CredentialsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/{id}/email/change")
    @PreAuthorize("hasAuthority('self:edit')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity changeEmail(
            @PathVariable("id") int id,
            @RequestParam("oldCredential") String oldEmail,
            @RequestParam("newCredential") String oldEmailRepeated,
            @RequestParam("newCredentialRepeated") String newEmail) {
        //TODO Apply logic

        log.info("Controller 'changeEmail' initiated.");
        return null;
    }

    @PostMapping(path = "/{id}/password/change")
    @PreAuthorize("hasAuthority('self:edit')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity changePassword(
            @PathVariable("id") int id,
            @RequestParam("oldCredential") String oldPassword,
            @RequestParam("newCredential") String oldPasswordRepeated,
            @RequestParam("newCredentialRepeated") String newPassword) {

        //TODO Apply logic

        //TODO hash password

        log.info("Controller 'changePassword' initiated.");
        return null;
    }
}
