package com.eventshub.services.impl;

import com.eventshub.model.Club;
import com.eventshub.model.Event;
import com.eventshub.model.User;
import com.eventshub.repository.ClubRepository;
import com.eventshub.services.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    @Override
    public Set<Club> getAll() {
        return (Set<Club>) clubRepository.findAll();
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
    public Set<Event> getEventsByClubs(Long id) {
        return clubRepository.findClubById(id).getClubsEvents();
    }
}