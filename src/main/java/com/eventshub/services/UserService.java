package com.eventshub.services;

import com.eventshub.model.Club;
import com.eventshub.model.Event;
import com.eventshub.model.User;

import java.util.List;
import java.util.Set;


public interface UserService {

    List<User> getAllUsers();

    List<User> getAllNotDeletedUsers();

    User isUserDeletedCheck(Long id);

    Set<Event> getAllCreatedEvents();

    Set<Event> getAllParticipatedEvents();

    Set<Club> getAllMyOwnedClubs();

    Set<Club> getAllMySubscribedClubs();
}
