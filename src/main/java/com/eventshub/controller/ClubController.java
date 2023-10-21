package com.eventshub.controller;

import com.eventshub.payload.request.ClubRequest;
import com.eventshub.services.ClubService;
import com.eventshub.services.UserService;
import com.eventshub.services.impl.ClubServiceImpl;
import com.eventshub.services.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
@Api
public class ClubController {

    private final ClubServiceImpl clubService;
    private final UserServiceImpl userService;

    @Operation(summary = "get all clubs")
    @GetMapping("/all")
    ResponseEntity<?> getAllClubs(){
        return ResponseEntity.ok(clubService.getAll());
    }

    @Operation(summary = "get information about the club with ID")
    @GetMapping("/{id}")
    ResponseEntity<?> getCurrentClub(@PathVariable Long id){
        return ResponseEntity.ok(clubService.getByID(id));
    }

    @Operation(summary = "get heads of club with ID")
    @GetMapping("/{id}/organizers")
    ResponseEntity<?> getHeadsOfClub(@PathVariable Long id){
        return ResponseEntity.ok(clubService.getHeadsOfClub(id));
    }

    @Operation(summary = "get subscribers of club")
    @GetMapping("/{id}/subscribers")
    ResponseEntity<?> getSubscribersOfClub(@PathVariable Long id){
        return ResponseEntity.ok(clubService.getSubscribersOfClub(id));
    }

    @Operation(summary = "get events of club")
    @GetMapping("/{id}/events")
    ResponseEntity<?> getEventsOfClub(@PathVariable Long id){
        return ResponseEntity.ok(clubService.getEventsByClubs(id));
    }

    @Operation(summary = "Create new club")
    @PostMapping("/create")
    public ResponseEntity<?> createClub(@RequestPart MultipartFile img,
                                        @RequestParam String description, @RequestParam String clubName){
        ClubRequest clubRequest = new ClubRequest(clubName, description, img);
        return ResponseEntity.ok(clubService.create(userService.getCurrentUser().getId(), clubRequest));
    }
}
