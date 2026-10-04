package com.seohamin.simple_note.service;

import com.seohamin.simple_note.domain.Note;
import com.seohamin.simple_note.dto.NoteRequestDto;
import com.seohamin.simple_note.dto.NoteResponseDto;
import com.seohamin.simple_note.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(final NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteResponseDto createNote(final NoteRequestDto noteRequestDto) {
        final Note note = new Note(
                null,
                noteRequestDto.title(),
                noteRequestDto.author(),
                noteRequestDto.category(),
                noteRequestDto.content(),
                null,
                null
        );

        return NoteResponseDto.of(noteRepository.save(note));
    }

    public List<NoteResponseDto> getAllNotes() {
        return noteRepository.findAll().stream()
                .map(NoteResponseDto::of)
                .toList();
    }

    public NoteResponseDto getNote(final String idStr) {
        final Long id;
        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found: " + idStr);
        }

        final Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found: " + id);
        }

        return NoteResponseDto.of(optionalNote.get());
    }

    public NoteResponseDto updateNote(final String idStr, final NoteRequestDto noteRequestDto) {
        final Long id;
        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found: " + idStr);
        }

        final Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found: " + id);
        }

        final Note note = optionalNote.get();
        note.setTitle(noteRequestDto.title());
        note.setAuthor(noteRequestDto.author());
        note.setCategory(noteRequestDto.category());
        note.setContent(noteRequestDto.content());

        return NoteResponseDto.of(noteRepository.save(note));
    }

    public void deleteNote(final String idStr) {
        final Long id;
        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found: " + idStr);
        }

        if (noteRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found: " + id);
        }

        noteRepository.deleteById(id);
    }

}
