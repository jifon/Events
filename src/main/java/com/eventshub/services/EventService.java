package com.eventshub.services;


import com.eventshub.model.Event;
import com.eventshub.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    // Список всех мероприятий
    List<Event> getAllNotExpired();

    // Создание новых мероприятий
    Event saveEvent(Event event);

    // Удаления мероприятий
    void deleteEvent(Long id);


    // Поиск мероприятия по id
    Event findEventById(Long id);

    Event findEventByName(String name);


}
