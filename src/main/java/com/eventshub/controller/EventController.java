package com.eventshub.controller;

import com.eventshub.model.Event;
import com.eventshub.services.EventService;
import com.eventshub.services.impl.EventServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;


    @Operation(summary = "get all events")
    @GetMapping("/all")
    ResponseEntity<?> getAllEvents(){
        return ResponseEntity.ok(eventService.getAll());
    }

    @Operation(summary = "get all not expired events")
    @GetMapping("/all-not-expired")
    ResponseEntity<?> getAllNotExpiredEvents(){
        return ResponseEntity.ok(eventService.getAllNotExpired());
    }

    @Operation(summary = "get all expired events")
    @GetMapping("/all-expired")
    ResponseEntity<?> getAllExpiredEvents(){
        return ResponseEntity.ok(eventService.getAllExpiredEvent());
    }


    @Operation(summary = "Get information about one Event")
    @GetMapping("/{id}")
    ResponseEntity<?> infoAboutEvent(@PathVariable Long id){
        return ResponseEntity.ok(eventService.findEventById(id));
    }


    @Operation(summary = "Get event organizer")
    @GetMapping("/{id}/organizers")
    ResponseEntity<?> getOrganizers(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getOrganizer(id));
    }

    @Operation(summary = "Get event participants")
    @GetMapping("/{id}/participants")
    ResponseEntity<?> getParticipants(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getParticipants(id));
    }


    @Operation(summary = "Create new event")
    @PostMapping("/create")
    ResponseEntity<?> createEvent(@RequestBody Event event){
        eventService.saveEvent(event);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Get club")
    @GetMapping("/{id}/club")
    ResponseEntity<?> getClubOrg(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getClub(id));
    }


    @DeleteMapping("/{id}/delete")
    ResponseEntity<?> deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
