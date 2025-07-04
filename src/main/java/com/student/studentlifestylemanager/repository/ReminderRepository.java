package com.student.studentlifestylemanager.repository;

import com.student.studentlifestylemanager.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findByReminderTimeBetween(LocalDateTime start, LocalDateTime end);
}
