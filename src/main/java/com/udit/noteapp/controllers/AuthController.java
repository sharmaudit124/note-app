package com.udit.noteapp.controllers;

import com.udit.noteapp.dtos.AuthDTO;
import com.udit.noteapp.dtos.LoginDTO;
import com.udit.noteapp.dtos.SignUpDTO;
import com.udit.noteapp.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        return new ResponseEntity<>(authenticationService.signUp(signUpDTO), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTO> login(@RequestBody LoginDTO loginDTO) {
        return new ResponseEntity<>(authenticationService.signIn(loginDTO), HttpStatus.OK);
    }

}
