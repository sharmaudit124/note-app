package com.udit.noteapp.services.impl;

import com.udit.noteapp.repositories.UserRepository;
import com.udit.noteapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.udit.noteapp.constants.ExceptionConstants.USERNAME_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByUserName(username).orElseThrow(
                        () -> new UsernameNotFoundException(String.format(USERNAME_NOT_FOUND, username))
                );
            }
        };
    }
}
