package com.eventshub.services.impl;

import com.eventshub.exception.NotAuthenticatedException;
import com.eventshub.exception.NotFoundException;
import com.eventshub.model.Club;
import com.eventshub.model.Event;
import com.eventshub.model.User;
import com.eventshub.repository.UserRepository;
import com.eventshub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllNotDeletedUsers() {
        return userRepository.findAllNotDeletedUsers();
    }

    public User getNotDeletedUserById(Long id) {
        User user = isUserDeletedCheck(id);
        return userRepository.findNotDeletedUserById(id);
    }

    public User deleteUserById(Long id) {
        User user = isUserDeletedCheck(id);
        user.setDateDeleted(LocalDateTime.now());
        user.setEnabled(false);
        userRepository.save(user);
        return user;
    }


    public User getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByEmail(authentication.getName()).orElseThrow( () ->
                new NotAuthenticatedException("You are not logged in"));
    }



    @Override
    public User isUserDeletedCheck(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find user with id: " + id));
        if(user.getDateDeleted() != null) {
            throw new NotFoundException("User with id: " + id + " was deleted!");
        }
        return user;
    }

    @Override
    public Set<Event> getAllCreatedEvents() { return getAuthentication().getCreatedEvents();}

    @Override
    public Set<Event> getAllParticipatedEvents() { return getAuthentication().getParticipatedEvents();}

    @Override
    public Set<Club> getAllMyOwnedClubs() { return getAuthentication().getOwnedClubs(); }

    @Override
    public Set<Club> getAllMySubscribedClubs() { return getAuthentication().getSubscribedClubs(); }


    public User getCurrentUser() {
        return getAuthentication();
    }



}