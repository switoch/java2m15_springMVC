package com.study.study.controller;

import com.study.study.data.entity.Note;
import com.study.study.service.NoteService;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class NoteController {
    Note note;
    NoteService noteService = new NoteService();

    @GetMapping(value = "/note/list")
    public ModelAndView getNotesList() {
        ModelAndView result = new ModelAndView("/note/list");
        result.addObject("list", noteService.listAll());
        return result;
    }

    @PostMapping(value = "note/create")
        public ModelAndView createNote(
                @RequestParam(value="noteID") Long id,
                @RequestParam(value="noteTitle") String title,
                @RequestParam(value="noteContent") String content) {
            note = new Note();
            note.setTitle(title);
            note.setContent(content);
            note.setId(id);
            noteService.add(note);
        return new ModelAndView("redirect:list");

    }

    @PostMapping(value = "note/delete")
    public ModelAndView deleteNoteById(@NotNull @RequestParam(value="id") Long noteId, HttpServletResponse httpServletResponse)  {
        httpServletResponse.setStatus(302);
        noteService.deleteById(noteId);
        return new ModelAndView("redirect:list");
    }

    @GetMapping (value = "note/delete")
    public ModelAndView deleteNote()  {
        return new ModelAndView("/note/delete");
    }

    @GetMapping(value = "/note/edit")
    public ModelAndView editNote(@RequestParam(value="id") Long noteId, String content) {
        return editNoteById(noteId, content);
    }

    @PostMapping(value = "note/edit")
    public ModelAndView editNoteById(@NotNull @RequestParam(value="id") Long noteId, String content)  {
        ModelAndView result = new ModelAndView("/note/edit");
        result.setStatus(HttpStatusCode.valueOf(302));
        noteService.update(noteId, content);
        return new ModelAndView("redirect:list");
    }

}
