package com.eventshub.services.impl;

import com.eventshub.dto.UserBasic;
import com.eventshub.exception.NotAuthenticatedException;
import com.eventshub.exception.NotFoundException;
import com.eventshub.model.PasswordResetToken;
import com.eventshub.model.User;
import com.eventshub.repository.PasswordResetTokenRepository;
import com.eventshub.repository.UserRepository;
import com.eventshub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Path root = Paths.get("user");
    private final Path rootBanner = Paths.get("banner");


    private final UserRepository userRepository;


    private final PasswordEncoder encoder;

    private final PasswordResetTokenRepository passwordResetTokenRepository;

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


    public String hardDeleteAllUsers() {

        userRepository.deleteAll();
        return "All Users deleted";
    }

    public String hardDeleteById(Long id) {

        userRepository.deleteById(id);
        return "User with id:" + id + " deleted";
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

    public User getCurrentUser() {
        return getAuthentication();
    }

    public User deleteCurrentUser() {
        User user = getAuthentication();
        deleteUserById(user.getId());
        return user;

    }

    public User findUserByEmail(String userEmailorPhone) {
        return  userRepository.findByEmail(userEmailorPhone).
                orElseThrow(() -> new NotFoundException("User not Found") );
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }

    public void changePassword(PasswordResetToken token, String newPassword) {
        User user = token.getUser();
        user.setPassword(encoder.encode(newPassword));
        passwordResetTokenRepository.delete(token);
        userRepository.save(user);
    }





    public UserBasic getNotDeletedBasicUserById(Long id) {
        User user = isUserDeletedCheck(id);
        ModelMapper modelMapper = new ModelMapper();
        UserBasic userBasic =
                modelMapper.map(user, UserBasic.class);
        return userBasic;
    }

    public List<UserBasic> getAllNotDeletedBasicUsers() {

        List<UserBasic> userBasics = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();

        List<User> users = userRepository.findAllNotDeletedUsers();
        for(User user:users){

            UserBasic userBasic =
                    modelMapper.map(user, UserBasic.class);

            userBasics.add(userBasic);
        }

        return userBasics;
    }




}
