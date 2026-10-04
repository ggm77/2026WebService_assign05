package com.seohamin.simple_note.repository;

import com.seohamin.simple_note.domain.Note;

import java.util.List;
import java.util.Optional;

public interface NoteRepository {
    public Note save(final Note note);
    public Optional<Note> findById(final Long id);
    public List<Note> findAll();
    public void deleteById(final Long id);
}
