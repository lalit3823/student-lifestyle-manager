package com.student.studentlifestylemanager.controller;

import com.student.studentlifestylemanager.model.Reminder;
import com.student.studentlifestylemanager.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminder") // ✅ IMPORTANT
public class ReminderController {

    @Autowired
    private ReminderRepository reminderRepository;

    @GetMapping
    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    @PostMapping
    public Reminder createReminder(@RequestBody Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    @PutMapping("/{id}")
    public Reminder updateReminder(@PathVariable Long id, @RequestBody Reminder updatedReminder) {
        Reminder reminder = reminderRepository.findById(id).orElseThrow();
        reminder.setMessage(updatedReminder.getMessage());
        reminder.setReminderTime(updatedReminder.getReminderTime());
        return reminderRepository.save(reminder);
    }

    @DeleteMapping("/{id}")
    public String deleteReminder(@PathVariable Long id) {
        reminderRepository.deleteById(id);
        return "Reminder " + id + " has been deleted";
    }

}
