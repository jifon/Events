package com.eventshub.services;

import com.eventshub.model.Club;
import com.eventshub.model.Event;
import com.eventshub.model.User;
import com.eventshub.payload.dto.EventDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Service
public interface ClubService {

    List<Club> getAll();

    Club create(Club club, String imageURL);
    Club getByID(Long id);
    Club getByName(String name);
    Set<User> getHeadsOfClub(Long id);
    Set<User> getSubscribersOfClub(Long id);
    Set<EventDto> getEventsByClubs(Long id);


}
