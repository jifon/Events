package com.eventshub.services.impl;

import com.eventshub.model.ERole;
import com.eventshub.model.RefreshToken;
import com.eventshub.model.Role;
import com.eventshub.model.User;
import com.eventshub.payload.request.LoginRequest;
import com.eventshub.payload.request.SignupRequest;
import com.eventshub.payload.response.JwtResponse;
import com.eventshub.security.services.UserDetailsImpl;
import com.eventshub.utils.EmailUtility;
import com.eventshub.exception.EmailAlreadyExistsException;
import com.eventshub.repository.RoleRepository;
import com.eventshub.repository.UserRepository;
import com.eventshub.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class AuthServiceImpl {


    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final RefreshTokenServiceImpl refreshTokenService;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final RoleRepository roleRepository;

    private final JavaMailSender mailSender;




    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        loginRequest.setUsername(loginRequest.getUsername().toLowerCase());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());



        return ResponseEntity.ok(new JwtResponse(jwt,
                refreshToken.getToken(),
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));

    }

    public User registerUser(SignupRequest signUpRequest) throws MessagingException, UnsupportedEncodingException {

        if (signUpRequest.getEmail() != null &
                userRepository.existsByEmail(signUpRequest.getEmail().toLowerCase())) {
            throw new EmailAlreadyExistsException("Error: Email " + signUpRequest.getEmail() + " is already in use!");
           }

        // Create new user's account
        User user = new User(
                signUpRequest.getFirst_name(),
                signUpRequest.getLast_name(),
                signUpRequest.getEmail().toLowerCase(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);

        EmailUtility.sendVerificationEmail(user, mailSender);
        userRepository.save(user);
        return user;


    }

    public boolean verifyUser(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }

    }


}







