package com.eventshub.controller;

import com.eventshub.services.impl.ClubServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final ClubServiceImpl clubService;

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
    @GetMapping("/{$id}/heads")
    ResponseEntity<?> getHeadsOfClub(@PathVariable Long id){
        return ResponseEntity.ok(clubService.getHeadsOfClub(id));
    }

    @Operation(summary = "get subscribers of club")
    @GetMapping("/{$id}/subscribers")
    ResponseEntity<?> getSubscribersOfClub(@PathVariable Long id){
        return ResponseEntity.ok(clubService.getSubscribersOfClub(id));
    }

    @Operation(summary = "get events of club")
    @GetMapping("/{$id}/events")
    ResponseEntity<?> getEventsOfClub(@PathVariable Long id){
        return ResponseEntity.ok(clubService.getEventsByClubs(id));
    }
}
