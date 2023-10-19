package com.eventshub.services;

import com.eventshub.model.User;

import java.util.List;


public interface UserService {

    List<User> getAllUsers();


//    boolean isDeleted(Long id);

    List<User> getAllNotDeletedUsers();

    User isUserDeletedCheck(Long id);
}
