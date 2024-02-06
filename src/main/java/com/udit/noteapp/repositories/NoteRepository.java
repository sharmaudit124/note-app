package com.udit.noteapp.repositories;

import com.udit.noteapp.entities.Note;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByAuthorId(String userId);

    Optional<Note> findByIdAndAuthorId(String noteId, String userId);

    List<Note> findByAuthorIdAndTextSearch(String userId, TextCriteria textCriteria);
}
