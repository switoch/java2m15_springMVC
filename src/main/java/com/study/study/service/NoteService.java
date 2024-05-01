package com.study.study.service;

import com.study.study.data.entity.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class NoteService {
    private final List<Note> noteList = new ArrayList<>();

    public List<Note> listAll() {
        return noteList;
    }

    public Note add(Note note) {
        this.noteList.add(note);
        return note;
    }

    public void deleteById(Long id) {
        for (Iterator<Note> iterator = noteList.iterator(); iterator.hasNext(); ) {
            Note value = iterator.next();
            if (value.getId().equals(id)) {
                iterator.remove();
            }
        }
    }

    public void update(Long id, String content) {
        try {
            getById(id).setContent(content);
        } catch (Exception e) {
            System.out.println("Note does not exist");
        }

    }

    public Note getById(Long id) {
        for (Note note : this.noteList) {
            if (note.getId().equals(id)) {
                return note;
            }
        }
        return null;
    }

    public void printNotes(){
       listAll().stream()
               .map(s->"id: " + s.getId() + ", title: " + s.getTitle() + ", text: " + s.getContent())
               .forEach(System.out::println);
    }
}

