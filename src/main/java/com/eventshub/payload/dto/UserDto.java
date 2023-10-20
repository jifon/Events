package com.eventshub.payload.dto;

import com.eventshub.model.Club;
import com.eventshub.model.Event;

import java.time.LocalDateTime;
import java.util.Set;

public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Set<Club> subscribedClubs;

    private Set<Club> ownedClubs;

    private Set<Event> createdEvents;

    private Set<Event> participatedEvents;


}
