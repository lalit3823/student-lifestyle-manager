package com.student.studentlifestylemanager.controller;

import com.student.studentlifestylemanager.model.Note;
import com.student.studentlifestylemanager.model.Reminder;
import com.student.studentlifestylemanager.model.Task;
import com.student.studentlifestylemanager.repository.NoteRepository;
import com.student.studentlifestylemanager.repository.ReminderRepository;
import com.student.studentlifestylemanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public String tasksPage(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("task", new Task()); // 👈 This was missing
        return "tasks";
    }

    @PostMapping("/tasks/add")
    public String addTask(@ModelAttribute("task") Task task) {
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    // ✅ Delete task by ID
    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }

    // ✅ Mark task as completed
    @PostMapping("/tasks/complete/{id}")
    public String completeTask(@PathVariable Long id) {
        taskRepository.findById(id).ifPresent(task -> {
            task.setCompleted(true);
            taskRepository.save(task);
        });
        return "redirect:/tasks";
    }

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/notes")
    public String notes(Model model) {
        model.addAttribute("note", new Note());
        model.addAttribute("notes", noteRepository.findAll());
        return "notes";
    }

    @GetMapping("/notes/edit/{id}")
    public String editNote(@PathVariable Long id, Model model) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid note ID"));
        model.addAttribute("note", note);
        model.addAttribute("notes", noteRepository.findAll());
        return "notes"; // same template reused for editing
    }

    @PostMapping("/notes/add")
    public String addNote(@ModelAttribute Note note) {
        noteRepository.save(note);
        return "redirect:/notes";
    }

    @PostMapping("/notes/update")
    public String updateNote(@ModelAttribute Note note) {
        noteRepository.save(note);
        return "redirect:/notes";
    }

    @PostMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
        return "redirect:/notes";
    }

    @Autowired
    private ReminderRepository reminderRepository;

    @GetMapping("/reminders")
    public String reminders(Model model) {
        model.addAttribute("reminders", reminderRepository.findAll());
        model.addAttribute("reminder", new Reminder());
        return "reminders";
    }

    @PostMapping("/reminders/add")
    public String addReminder(@ModelAttribute Reminder reminder) {
        reminderRepository.save(reminder);
        return "redirect:/reminders";
    }

    @PostMapping("/reminders/delete/{id}")
    public String deleteReminder(@PathVariable Long id) {
        reminderRepository.deleteById(id);
        return "redirect:/reminders";
    }

    @GetMapping("/reminders/edit/{id}")
    public String editReminder(@PathVariable Long id, Model model) {
        Reminder reminder = reminderRepository.findById(id).orElseThrow();
        model.addAttribute("reminder", reminder);
        model.addAttribute("reminders", reminderRepository.findAll());
        return "reminders";
    }

    @PostMapping("/reminders/update")
    public String updateReminder(@ModelAttribute Reminder reminder) {
        reminderRepository.save(reminder);
        return "redirect:/reminders";
    }

    @GetMapping("/")
    public String home(Model model) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneHourLater = now.plusHours(1);
        List<Reminder> upcomingReminders = reminderRepository.findByReminderTimeBetween(now, oneHourLater);
        model.addAttribute("reminders", upcomingReminders);
        return "home";
    }




    // Disabled for now
    // @GetMapping("/health")
    // public String health() {
    //     return "health";
    // }
}