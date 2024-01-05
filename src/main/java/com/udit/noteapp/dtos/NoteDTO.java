package com.udit.noteapp.dtos;

import com.udit.noteapp.entities.Note;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {
    private String title;
    private String content;
    private String authorId;

    public NoteDTO(Note note) {
        this.title = note.getTitle();
        this.content = note.getContent();
        this.authorId = String.valueOf(note.getAuthor());
    }
}
