package com.eventshub.controller;

import com.eventshub.payload.request.LoginRequest;
import com.eventshub.payload.request.SignupRequest;
import com.eventshub.payload.response.MessageResponse;
import com.eventshub.repository.UserRepository;
import com.eventshub.services.impl.AuthServiceImpl;
import com.eventshub.services.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Api("Api for authentication and authorization")
public class AuthController {


    private final AuthServiceImpl authService;


    @Operation(summary = "login")
    @PostMapping("/sign-in")//логин
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }


    @Operation(summary = "registration")
    @PostMapping("/sign-up")//регистрация
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest)
            throws MessagingException, UnsupportedEncodingException {
        authService.registerUser(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("Check your email"));
    }


    @Operation(summary = "Verification")
    @GetMapping("/verifyUser")//рерификация кода который пришел на поч
    public ResponseEntity<MessageResponse> verifyUser(@Param("code") String code) {
        if (authService.verifyUser(code)) {
            return ResponseEntity.ok(new MessageResponse("verify_success"));
        } else {
            return ResponseEntity.ok(new MessageResponse("verify_fail"));
        }
    }














}