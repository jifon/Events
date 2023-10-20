package com.eventshub.repository;

import com.eventshub.model.Event;
import com.eventshub.payload.dto.ParticipantEventDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


public interface EventRepository extends JpaRepository<Event, Long> {
//    List<Event> findAll();
//
//    //
//    List<Event> findAllByUserId(Long id);
    Event findEventById(Long id);

    Event findEventByEventName(String eventName);

    List<Event> findEventByClubOrganizer(Long id);






}
