package com.eventshub.services.impl;

import com.eventshub.model.Club;
import com.eventshub.model.Event;
import com.eventshub.model.User;
import com.eventshub.payload.dto.ParticipantEventDto;
import com.eventshub.repository.EventRepository;
import com.eventshub.services.EventService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventServiceImpl implements EventService {

//    Event

//    get participants
//    get organizer-user
//    get organizer-club
//    post create event by user
//    post create event by club
//    post edit club by organizer
    // поиск


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
    public Event findEventById(Long id) {
        return eventRepository.findById(id).get();
    }

    @Override
    public Event findEventByName(String name) {
        return eventRepository.findEventByEventName(name);
    }


    //    get organizer-user   id - id мероприятия
    public User getOrganizer(Long id){
        return eventRepository.findEventById(id).getOrganizer();
    }


    // get participants    id - id мероприятия
    public Set<ParticipantEventDto> getParticipants(Long id){
        Set<User> users = eventRepository.findEventById(id).getParticipants();
        Set<ParticipantEventDto> participantEventDtos = new HashSet<>();
        for(User user : users){
            ParticipantEventDto dto = new ParticipantEventDto();
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setId(user.getId());
            participantEventDtos.add(dto);

        }
        return participantEventDtos;
    }

//    public Set<User> getParticipants(Long id){
//        return eventRepository.findById(id).get().getParticipants();
//    }

    // get club organizer
    public Club getClub (Long id){
        return  eventRepository.findEventById(id).getClubOrganizer();
    }









}
