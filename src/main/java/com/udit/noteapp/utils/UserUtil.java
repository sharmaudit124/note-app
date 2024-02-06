package com.udit.noteapp.utils;

import com.udit.noteapp.entities.User;
import com.udit.noteapp.exception.EntityAlreadyExistException;
import com.udit.noteapp.exception.EntityNotFoundException;
import com.udit.noteapp.exception.WeakPasswordException;
import com.udit.noteapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.udit.noteapp.constants.ExceptionConstants.*;

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

    public User checkIfUserNotExistElseGet(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isEmpty()) {
            throw new EntityNotFoundException(String.format(USERNAME_NOT_FOUND, userName));
        }
        return user.get();
    }

    public void checkIfUsersExists(List<String> userNames) {
        List<User> users = userRepository.findAllByUserName(userNames);

        if (users.size() < 2) {
            throw new EntityNotFoundException(GIVEN_USERNAME_NOT_FOUND);
        }
    }
}
