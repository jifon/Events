package com.eventshub.services.impl;

import com.eventshub.model.Club;
import com.eventshub.model.Event;
import com.eventshub.model.User;
import com.eventshub.payload.dto.EditEventDto;
import com.eventshub.payload.dto.EventDto;
import com.eventshub.payload.dto.ParticipantEventDto;
import com.eventshub.payload.response.EventRespnseWithPart;
import com.eventshub.repository.EventRepository;
import com.eventshub.services.EventService;
import com.eventshub.services.FileUploadService;
import com.eventshub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
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
    private final FileUploadServiceImpl fileUploadService;
    //private final UserServiceImpl userService;

//    public EventServiceImpl(EventRepository eventRepository) {
//        this.eventRepository = eventRepository;
//    }

    @Override
    public EditEventDto eventToEventDto(Event event) {
        EditEventDto eventDto = new EditEventDto();
        eventDto.setId(event.getId());
        eventDto.setEventName(event.getEventName());
        eventDto.setPlace(event.getPlace());
        eventDto.setDate(event.getDate());
        eventDto.setDescription(event.getDescription());
        eventDto.setVerified(event.isVerified());
        eventDto.setImage(event.getImage());

        // Set the name of the club organizer
//        if (event.getClubOrganizer() != null) {
//            eventDto.setNameOfClubOrganizer(event.getClubOrganizer().getClubName());
//        }
//
//        // Set the name of the user organizer (assuming you have a method to get the user's name)
//        if (event.getOrganizer() != null) {
//            eventDto.setNameOfUserOrganizer(event.getOrganizer().getFirstName() + " " + event.getOrganizer().getLastName());
//        }
//
//        // Set the quantity of participants
//        eventDto.setQuantityOfParticipants(event.getParticipants().size());

        return eventDto;
    }

    // все мероприятия, которые будут в будущем
    @Override
    public List<Event> getAllNotExpired() {
        Date currentDate = new Date();
        List<Event> filteredData = new ArrayList<>();
        List<Event> notFiltered = eventRepository.findAll();
        for(Event event : notFiltered){
            if(currentDate.before(event.getDate())){
                System.out.println("Cuurent Date " + currentDate);
                System.out.println("Date of event " + event.getDate());
                filteredData.add(event);
            }
        }
        return filteredData;
    }

    @Override
    public List<Event> getAll(){
        return eventRepository.findAll();
    }

    @Override
    public Event saveEvent(EventDto eventDto, MultipartFile img, User user) {
        String imageURL;
        try {
            imageURL = fileUploadService.uploadFile(img);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Event event = new Event();
        event.setEventName(eventDto.getEventName());
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1;
        try {
            date1=formatter.parse(eventDto.getDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //event.setId(5L);
        event.setDate(date1);
        event.setDescription(eventDto.getDescription());
        event.setPlace(eventDto.getPlace());
        event.setOrganizer(user);
        event.setVerified(true);
        event.setImage(imageURL);
        event.setParticipants(new HashSet<>());
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    //    get info about event
    @Override
    public Optional<Event> findEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Event findEventByName(String name) {
        return eventRepository.findEventByEventName(name);
    }


    //    get organizer-user   id - id мероприятия
    @Override
    public ParticipantEventDto getOrganizer(Long id){
        User user = eventRepository.findEventById(id).getOrganizer();
        ParticipantEventDto dto = new ParticipantEventDto();
        dto.setId(user.getId());
        dto.setLastName(user.getLastName());
        dto.setFirstName(user.getFirstName());

        return dto;
    }


    // get participants    id - id мероприятия
    @Override
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

    public EventRespnseWithPart getParticipantsCount(Long id){
        Event event = eventRepository.findEventById(id);
        Integer countOfParticipants = event.getParticipants().size();
        EventRespnseWithPart response = new EventRespnseWithPart();
        response.setCountOfParticipants(countOfParticipants);
        response.setEventName(event.getEventName());
        response.setDate(event.getDate());
        response.setDescription(event.getDescription());
        response.setImage(event.getImage());
        response.setId(event.getId());
        response.setPlace(event.getPlace());
        response.setVerified(event.isVerified());
        return response;
    }

//    public Set<User> getParticipants(Long id){
//        return eventRepository.findById(id).get().getParticipants();
//    }

    // get club organizer
    @Override
    public Club getClub (Long id){
        return  eventRepository.findEventById(id).getClubOrganizer();
    }

    @Override
    public void editEvent(EditEventDto eventDto) {
        Event event = eventRepository.findEventById(eventDto.getId());
        event.setEventName(eventDto.getEventName());
        event.setDescription(eventDto.getDescription());
        event.setDate(eventDto.getDate());
        event.setPlace(eventDto.getPlace());

        eventRepository.save(event);
    }

//    @Override
//    public void editEvent(EventDto eventDto) {
//        Event event = eventRepository.findEventById(eventDto.getId());
//        event.setEventName(eventDto.getEventName());
//        event.setDescription(eventDto.getDescription());
//        event.setDate(eventDto.getDate());
//        event.setPlace(eventDto.getPlace());
//
//        eventRepository.save(event);
//    }

    @Override
    public List<Event> getAllExpiredEvent() {
        Date currentDate = new Date();
        List<Event> filteredData = new ArrayList<>();
        List<Event> notFiltered = eventRepository.findAll();
        for(Event event : notFiltered){
            if(currentDate.after(event.getDate())){
                filteredData.add(event);
            }
        }
        return filteredData;

    }


}
