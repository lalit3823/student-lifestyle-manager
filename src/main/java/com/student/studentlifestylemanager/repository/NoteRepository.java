package com.student.studentlifestylemanager.repository;

import com.student.studentlifestylemanager.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
