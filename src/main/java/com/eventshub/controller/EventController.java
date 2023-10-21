package com.eventshub.controller;

import com.eventshub.model.Event;
import com.eventshub.services.impl.EventServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventServiceImpl eventService;


    @Operation(summary = "get all events")
    @GetMapping("/all")
    ResponseEntity<?> getAllEvents(){
        return ResponseEntity.ok(eventService.getAll());
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

    @Operation(summary = "Get event and participants count")
    @GetMapping("/{id}/participants-count")
    ResponseEntity<?> getParticipantsCount(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getParticipantsCount(id));
    }


    @Operation(summary = "Create new event")
    @PostMapping("/create")
    ResponseEntity<?> createEvent(@RequestBody Event event){
        return ResponseEntity.ok(eventService.saveEvent(event));
    }









}
