package com.udit.noteapp.services.impl;

import com.udit.noteapp.dtos.AuthDTO;
import com.udit.noteapp.dtos.LoginDTO;
import com.udit.noteapp.dtos.SignUpDTO;
import com.udit.noteapp.entities.User;
import com.udit.noteapp.exception.BadCredentialException;
import com.udit.noteapp.repositories.UserRepository;
import com.udit.noteapp.services.AuthenticationService;
import com.udit.noteapp.services.JwtService;
import com.udit.noteapp.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.udit.noteapp.constants.ExceptionConstants.BAD_CREDENTIALS_FOUND;
import static com.udit.noteapp.constants.ExceptionConstants.USERNAME_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserUtil userUtil;


    @Override
    public AuthDTO signUp(SignUpDTO request) {
        userUtil.checkIfUserExist(request.getUserName());
        userUtil.checkIfStrongPassword(request.getPassword());

        User user = User.builder().userName(request.getUserName())
                .fullName(request.getFullName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);

        var jwt = jwtService.generateToken(user);
        //TODO: add refreshToken
        return AuthDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUsername())
                .accessToken(jwt)
                .createdAt(user.getCreatedAt())
                .build();
    }

    @Override
    public AuthDTO signIn(LoginDTO request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialException(BAD_CREDENTIALS_FOUND);
        }
        User user = userRepository.findByUserName(request.getUserName()).orElseThrow(
                () -> new UsernameNotFoundException(String.format(USERNAME_NOT_FOUND, request.getUserName()))
        );

        var jwt = jwtService.generateToken(user);

        return AuthDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUsername())
                .accessToken(jwt)
                .createdAt(user.getCreatedAt())
                .build();
    }
}
