package com.udit.noteapp.services;

import com.udit.noteapp.dtos.AuthDTO;
import com.udit.noteapp.dtos.LoginDTO;
import com.udit.noteapp.dtos.SignUpDTO;

public interface AuthenticationService {

    AuthDTO signUp(SignUpDTO request);

    AuthDTO signIn(LoginDTO request);
}
