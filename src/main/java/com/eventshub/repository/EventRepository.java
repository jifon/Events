package com.eventshub.repository;

import com.eventshub.model.Event;
import com.eventshub.model.User;
import com.eventshub.payload.dto.ParticipantEventDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;


public interface EventRepository extends JpaRepository<Event, Long> {
//    List<Event> findAll();
//
//    //
//    List<Event> findAllByUserId(Long id);
    Event findEventById(Long id);

    Event findEventByEventName(String eventName);

//<<<<<<< HEAD
//    List<Event> findEventByClubOrganizer(Long id);
//=======
    @Query("SELECT e FROM Event e WHERE e.clubOrganizer.id = :cid")
    List<Event> findAllEventsByClub(@RequestParam Long cid);

//>>>>>>> origin/master






}
