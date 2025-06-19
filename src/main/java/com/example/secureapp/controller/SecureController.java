package com.example.secureapp.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/secure")
public class SecureController {

    @Secured("ROLE_READ")
    @GetMapping("/read")
    public String readData() {
        return "This data is only for users with READ role";
    }

    @RolesAllowed("ROLE_WRITE")
    @GetMapping("/write")
    public String writeData() {
        return "This data is only for users with WRITE role";
    }

    @PreAuthorize("hasAnyRole('WRITE', 'DELETE')")
    @GetMapping("/write-or-delete")
    public String writeOrDeleteData() {
        return "This data is for users with WRITE or DELETE role";
    }

    @GetMapping("/user-specific")
    public String userSpecificData(@RequestParam String username) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!currentUser.equals(username)) {
            throw new SecurityException("Access denied: Username does not match");
        }
        return "This data is only for user: " + username;
    }
}