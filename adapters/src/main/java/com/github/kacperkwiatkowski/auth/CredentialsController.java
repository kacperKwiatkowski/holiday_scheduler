package com.github.kacperkwiatkowski.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/credentials")
class CredentialsController {

    @PostMapping(path = "/{id}/email/change")
    @PreAuthorize("hasAuthority('self:edit')")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity changeEmail(
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
    ResponseEntity changePassword(
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
