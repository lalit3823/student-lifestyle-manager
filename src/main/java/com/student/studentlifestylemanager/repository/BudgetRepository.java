package com.student.studentlifestylemanager.repository;

import com.student.studentlifestylemanager.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
