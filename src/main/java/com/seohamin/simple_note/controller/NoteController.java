package com.seohamin.simple_note.controller;

import com.seohamin.simple_note.dto.NoteRequestDto;
import com.seohamin.simple_note.dto.NoteResponseDto;
import com.seohamin.simple_note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(final NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("")
    public ResponseEntity<NoteResponseDto> createNote(
            @RequestBody final NoteRequestDto noteRequestDto
    ) {
        return ResponseEntity.status(201).body(noteService.createNote(noteRequestDto));
    }

    @GetMapping("")
    public ResponseEntity<List<NoteResponseDto>> getAllNotes(
            @RequestParam(value = "category", required = false) final String category
    ) {
        return ResponseEntity.ok().body(noteService.getAllNotes(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDto> getNote(
            @PathVariable(value = "id") final String idStr
    ) {
        return ResponseEntity.ok().body(noteService.getNote(idStr));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponseDto> putNote(
            @PathVariable(value = "id") final String idStr,
            @RequestBody final NoteRequestDto noteRequestDto
    ) {
        return ResponseEntity.ok().body(noteService.updateNote(idStr, noteRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(
            @PathVariable(value = "id") final String idStr
    ) {
        noteService.deleteNote(idStr);

        return ResponseEntity.noContent().build();
    }
}
