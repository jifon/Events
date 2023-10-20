package com.eventshub.services;


import com.eventshub.model.Event;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EventService {

    // Список всех мероприятий
    List<Event> getAllNotExpired();

    // Создание новых мероприятий
    Event saveEvent(Event event);

    // Удаления мероприятий
    void deleteEvent(Long id);


    // Поиск мероприятия по id
    Optional<Event> findEventById(Long id);

    Event findEventByName(String name);


}
