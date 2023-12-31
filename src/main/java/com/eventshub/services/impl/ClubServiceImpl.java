package com.eventshub.services.impl;

import com.eventshub.model.Club;
import com.eventshub.model.Event;
import com.eventshub.model.User;
import com.eventshub.payload.dto.EditEventDto;
import com.eventshub.payload.dto.EventDto;
import com.eventshub.payload.request.ClubRequest;
import com.eventshub.payload.response.ClubWithEventsCount;
import com.eventshub.repository.ClubRepository;
import com.eventshub.repository.EventRepository;
import com.eventshub.services.ClubService;
import com.eventshub.services.EventService;
import com.eventshub.services.FileUploadService;
import com.eventshub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final UserServiceImpl userService;
    private final FileUploadService fileUploadService;
    private final EventRepository eventRepository;
    private final EventServiceImpl eventService;

    @Override
    public List<Club> getAll() {
        return clubRepository.findAll();
    }

    @Override
    public Club create(Long userID, ClubRequest clubRequest) {
        String imageURL;
        try {
            imageURL = fileUploadService.uploadFile(clubRequest.getMultipartFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Set<User> users = new HashSet<>();
        users.add(userService.getNotDeletedUserById(userID));
        Club club = new Club(
                clubRequest.getClubName(),
                clubRequest.getDescription(),
                imageURL, users);
        return club;
        //return null;
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
    public List<EditEventDto> getEventsByClubs(Long clubId) {
        List<Event> events = eventRepository.findAllEventsByClub(clubId);
        //Set<Event> events = clubRepository.findClubById(id).getClubsEvents();
        List<EditEventDto> dtos = new ArrayList<>();
        for(Event event : events){
            EditEventDto dto = eventService.eventToEventDto(event);
            dtos.add(dto);
        }
        return dtos;
    }

//    @Override
//    public ClubWithEventsCount getCurrentClubCountEvent(Long id) {
//        Club club = clubRepository.findClubById(id);
//        ClubWithEventsCount response = new ClubWithEventsCount();
//        response.setId(club.getId());
//        response.setClubName(club.getClubName());
//        response.setDescription(club.getDescription());
//        response.setImage(club.getImage());
//        List<Event> events = eventRepository.findAllEventsByClub(id);
//        response.setEventsCount(events.size());
//        return response;
//    }

}
