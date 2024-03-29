package com.udit.noteapp.services;

import com.udit.noteapp.dtos.CreateNoteDTO;
import com.udit.noteapp.dtos.NoteDTO;

import java.util.List;

public interface NoteService {
    List<NoteDTO> getAllNotes(String userName);

    NoteDTO getNoteById(String noteId, String userName);

    NoteDTO createNote(CreateNoteDTO noteDTO, String userName);

    NoteDTO updateNote(String noteId, NoteDTO noteDTO, String userName);

    void deleteNote(String noteId, String userName);

    NoteDTO shareNote(String noteId, String shareWith, String userName);

    List<NoteDTO> searchByText(String searchText, String userName);
}
