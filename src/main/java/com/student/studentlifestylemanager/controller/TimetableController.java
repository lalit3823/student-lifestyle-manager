package com.student.studentlifestylemanager.controller;

import com.student.studentlifestylemanager.model.Timetable;
import com.student.studentlifestylemanager.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

    @Autowired
    private TimetableRepository timetableRepository;

    @GetMapping
    public String viewTimetable(Model model) {
        List<Timetable> entries = timetableRepository.findAll();
        model.addAttribute("timetable", entries);
        model.addAttribute("entry", new Timetable());
        return "timetable";
    }

    @PostMapping("/add")
    public String addEntry(@ModelAttribute Timetable entry) {
        timetableRepository.save(entry);
        return "redirect:/timetable";
    }

    @PostMapping("/delete/{id}")
    public String deleteEntry(@PathVariable Long id) {
        timetableRepository.deleteById(id);
        return "redirect:/timetable";
    }
}
