package com.udit.noteapp.repositories;

import com.udit.noteapp.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserName(String userName);

    List<User> findAllByUserName(List<String> userNames);
}
