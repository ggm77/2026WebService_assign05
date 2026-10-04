package com.seohamin.simple_note.dto;

import com.seohamin.simple_note.domain.Note;

import java.time.Instant;

public record NoteResponseDto(
        Long id,
        String title,
        String author,
        String category,
        String content,
        Instant createdAt,
        Instant updatedAt
) {

    public static NoteResponseDto of(final Note note) {
        return new NoteResponseDto(
                note.getId(),
                note.getTitle(),
                note.getAuthor(),
                note.getCategory(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }
}
