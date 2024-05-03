package com.study.study.service;

import com.study.study.data.entity.NoteEntity;
import com.study.study.data.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NoteServiceDB {

    private final NoteRepository repo;

    public Iterable<NoteEntity> listAll() {
        return repo.findAll();
    }

    public NoteEntity add(NoteEntity note) {
        return repo.save(note);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public void update(Long id, String content) throws Exception {
        if (Objects.isNull(getById(id))) {
            throw new Exception("NoteEntity not found");
        }
        NoteEntity note = new NoteEntity();
        note.setId(id);
        note.setTitle(getById(note.getId()).getTitle());
        note.setContent(content);
        repo.save(note);

    }

    public NoteEntity getById(Long id) throws Exception {
        Optional<NoteEntity> optionalNote = repo.findById(id);
        if (optionalNote.isPresent()) {
            return optionalNote.get();
        } else {
            throw new Exception("NoteEntity not found");
        }
    }
}
