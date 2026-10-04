package com.seohamin.simple_note.dto;

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
}
