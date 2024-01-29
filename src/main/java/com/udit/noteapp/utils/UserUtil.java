package com.udit.noteapp.utils;

import com.udit.noteapp.exception.EntityAlreadyExistException;
import com.udit.noteapp.exception.WeakPasswordException;
import com.udit.noteapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static com.udit.noteapp.constants.ExceptionConstants.USERNAME_ALREADY_FOUND;
import static com.udit.noteapp.constants.ExceptionConstants.WEAK_PASSWORD_FOUND;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public void checkIfUserExist(String userName) {
        if (userRepository.findByUserName(userName).isPresent()) {
            throw new EntityAlreadyExistException(String.format(USERNAME_ALREADY_FOUND, userName));
        }
    }

    public void checkIfStrongPassword(String password) {
        if (!Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}", password)) {
            throw new WeakPasswordException(WEAK_PASSWORD_FOUND);
        }
    }
}
