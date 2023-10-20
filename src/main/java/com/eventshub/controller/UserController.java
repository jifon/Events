package com.eventshub.controller;

import com.eventshub.services.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")

public class UserController {

    private final UserServiceImpl userService;



    @Operation(summary = "Get all not deleted users")
    @GetMapping("/all-not-deleted")
    ResponseEntity<?> getAllNotDeletedUsers() {
        return ResponseEntity.ok(userService.getAllNotDeletedUsers());
    }

    @Operation(summary = "Delete user by id")
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @Operation(summary = "Get not deleted user by id")
    @GetMapping("/{id}")
    ResponseEntity<?> getNotDeletedUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getNotDeletedUserById(id));
    }

    @Operation(summary = "Get current user")
    @GetMapping("/my")
    ResponseEntity<?> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @Operation(summary = "Get all users")
    @GetMapping("/all")
    ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }




}
