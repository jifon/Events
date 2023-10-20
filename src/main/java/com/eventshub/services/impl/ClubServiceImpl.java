package com.eventshub.services.impl;

import com.eventshub.model.Club;
import com.eventshub.model.Event;
import com.eventshub.model.User;
import com.eventshub.payload.dto.EventDto;
import com.eventshub.repository.ClubRepository;
import com.eventshub.repository.EventRepository;
import com.eventshub.services.ClubService;
import com.eventshub.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final EventService eventService;
    private final EventRepository eventRepository;

    @Override
    public List<Club> getAll() {
        return clubRepository.findAll();
    }

    @Override
    public Club create(Club club, String imageURL) {
        return clubRepository.save(club);
    }

    @Override
    public Club getByID(Long id) {
        return clubRepository.findClubById(id);
    }

    @Override
    public Club getByName(String name) {
        return clubRepository.findClubByClubName(name);
    }

    @Override
    public Set<User> getHeadsOfClub(Long id) {
        return clubRepository.findClubById(id).getHeaders();
    }

    @Override
    public Set<User> getSubscribersOfClub(Long id) {
        return clubRepository.findClubById(id).getSubscribers();
    }

    @Override
    public List<Event> getEventsByClubs(Long clubId) {
        List<Event> events = eventRepository.findAllEventsByClub(clubId);
//        Set<Event> events = clubRepository.findClubById(id).getClubsEvents();
//        Set<EventDto> dtos = new HashSet<>();
//        for(Event event : events){
//            EventDto dto = eventService.eventToEventDto(event);
//            dtos.add(dto);
//
//        }
//        return dtos;
        return events;
    }
}
