package com.eventshub.services;


import com.eventshub.model.Club;
import com.eventshub.model.Event;
import com.eventshub.payload.dto.EventDto;
import com.eventshub.payload.dto.ParticipantEventDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface EventService {

    // Список всех мероприятий
    List<Event> getAllNotExpired();

    List<Event> getAll();

    // Создание новых мероприятий
    Event saveEvent(Event event);

    // Удаления мероприятий
    void deleteEvent(Long id);


    // Поиск мероприятия по id
    Optional<Event> findEventById(Long id);

    Event findEventByName(String name);

    EventDto eventToEventDto(Event event);

    //    get organizer-user   id - id мероприятия
    ParticipantEventDto getOrganizer(Long id);

    Set<ParticipantEventDto> getParticipants(Long id);

    Club getClub (Long id);


}
