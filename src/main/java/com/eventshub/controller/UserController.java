package com.eventshub.controller;

import com.eventshub.services.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Api()

public class UserController {

    private final UserServiceImpl userService;


    @Operation(summary = "get events user organizes")
    @GetMapping("/all-my-org-events")
    ResponseEntity<?> getAllCreatedEvents( ){
        return ResponseEntity.ok(userService.getAllCreatedEvents());
    }

    //events example
    @Operation(summary = "get events that I will participate")
    @GetMapping("/all-my-part-events")
    ResponseEntity<?> getAllEventsThatParticipate(){
        return ResponseEntity.ok(userService.getAllParticipatedEvents());
    }

    @Operation(summary = "get my owned clubs")
    @GetMapping("/all-my-owned-clubs")
    ResponseEntity<?> getAllMyClubs(){
        return ResponseEntity.ok(userService.getAllMyOwnedClubs());
    }

    @Operation(summary = "get my subscribed clubs")
    @GetMapping("/all-my-subscribed-clubs")
    ResponseEntity<?> getAllMySubscribedClubs(){
        return ResponseEntity.ok(userService.getAllMySubscribedClubs());
    }


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