package com.seohamin.simple_note.controller;

import com.seohamin.simple_note.dto.NoteRequestDto;
import com.seohamin.simple_note.dto.NoteResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @PostMapping("")
    public ResponseEntity<NoteResponseDto> createNote(
            @RequestBody final NoteRequestDto noteRequestDto
    ) {

        return ResponseEntity.status(201).body(null);
    }

    @GetMapping("")
    public ResponseEntity<NoteResponseDto> getAllNotes() {

        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<NoteResponseDto>> getNote(
            @PathVariable(value = "id") final String idStr
    ) {

        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<NoteResponseDto>> putNote(
            @PathVariable(value = "id") final String idStr,
            @RequestBody final NoteRequestDto noteRequestDto
    ) {

        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<NoteResponseDto>> deleteNote(
            @PathVariable(value = "id") final String idStr
    ) {

        return ResponseEntity.noContent().build();
    }
}
