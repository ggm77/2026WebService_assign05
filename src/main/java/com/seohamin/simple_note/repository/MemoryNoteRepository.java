package com.seohamin.simple_note.repository;

import com.seohamin.simple_note.domain.Note;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryNoteRepository implements NoteRepository {

    private final Map<Long, Note> store = new LinkedHashMap<>();

    private long sequence = 0L;

    @Override
    public Note save(final Note note) {
        final Instant now = Instant.now();

        if (note.getId() == null) {
            note.setId(++sequence);
            note.setCreatedAt(now);
        }
        note.setUpdatedAt(now);

        store.put(note.getId(), note);
        return note;
    }

    @Override
    public Optional<Note> findById(final Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Note> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(final Long id) {
        store.remove(id);
    }
}
