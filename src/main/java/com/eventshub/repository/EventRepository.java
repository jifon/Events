package com.eventshub.repository;

import com.eventshub.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Long> {
//    List<Event> findAll();
//
//    //
//    List<Event> findAllByUserId(Long id);
    Event findEventById(Long id);

    Event findEventByEventName(String eventName);




}
