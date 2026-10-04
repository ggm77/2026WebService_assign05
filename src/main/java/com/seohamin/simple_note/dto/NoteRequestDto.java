package com.seohamin.simple_note.dto;

public record NoteRequestDto(
        String title,
        String author,
        String category,
        String content
) {
}
