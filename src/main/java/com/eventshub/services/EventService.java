package com.eventshub.services;


import com.eventshub.model.Club;
import com.eventshub.model.Event;
import com.eventshub.model.User;
import com.eventshub.payload.dto.EditEventDto;
import com.eventshub.payload.dto.EventDto;
import com.eventshub.payload.dto.ParticipantEventDto;
import com.eventshub.payload.response.EventRespnseWithPart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface EventService {

    // Список всех мероприятий
    List<Event> getAllNotExpired();

    List<Event> getAll();

    // Создание новых мероприятий
    //Event saveEvent(Event event);

    Event saveEvent(EventDto eventDto, MultipartFile img, User user);

    // Удаления мероприятий
    void deleteEvent(Long id);


    // Поиск мероприятия по id
    Optional<Event> findEventById(Long id);

    Event findEventByName(String name);

    EditEventDto eventToEventDto(Event event);

    //    get organizer-user   id - id мероприятия
    ParticipantEventDto getOrganizer(Long id);

    Set<ParticipantEventDto> getParticipants(Long id);

    Club getClub (Long id);


    void editEvent (EditEventDto eventDto);

    List<Event> getAllExpiredEvent();

    EventRespnseWithPart getParticipantsCount(Long id);





}
