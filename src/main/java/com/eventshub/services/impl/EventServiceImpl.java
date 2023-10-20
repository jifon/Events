package com.eventshub.services.impl;

import com.eventshub.model.Club;
import com.eventshub.model.Event;
import com.eventshub.model.User;
import com.eventshub.repository.EventRepository;
import com.eventshub.services.EventService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class EventServiceImpl implements EventService {

//    Event

//    get participants
//    get organizer-user
//    get organizer-club
//    post create event by user
//    post create event by club
//    post edit club by organizer

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // все мероприятия, которые будут в будущем
    @Override
    public List<Event> getAllNotExpired() {
        Date currentDate = new Date();
        List<Event> filteredData = new ArrayList<>();
        List<Event> notFiltered = eventRepository.findAll();
        for(Event event : notFiltered){
            if(currentDate.before(event.getDate())){
                filteredData.add(event);
            }
        }
        return filteredData;
    }

    public List<Event> getAll(){
        return eventRepository.findAll();
    }

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    //    get info about event
    @Override
    public Event findEvent(Long id) {
        return eventRepository.findById(id).get();
    }



    //    get organizer-user   id - id мероприятия
    public User getOrganizers(Long id){
        return eventRepository.findEventById(id).getOrganizer();
    }


    // get participants    id - id мероприятия
    public Set<User> getParticipants(Long id){
        return eventRepository.findEventById(id).getParticipants();
    }


    // get club organizer
    public Club getClub (Long id){
        return  eventRepository.findEventById(id).getClubOrganizer();
    }







}
