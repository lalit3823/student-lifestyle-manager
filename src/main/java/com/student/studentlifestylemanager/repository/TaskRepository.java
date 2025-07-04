package com.student.studentlifestylemanager.repository;

import com.student.studentlifestylemanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // You can define custom methods here later if needed
}