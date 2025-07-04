package com.student.studentlifestylemanager.controller;

import com.student.studentlifestylemanager.model.Budget;
import com.student.studentlifestylemanager.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    @GetMapping
    public String getAllBudgets(Model model) {
        model.addAttribute("budgets", budgetRepository.findAll());
        model.addAttribute("budget", new Budget()); // For the add form
        return "budget";
    }

    @PostMapping("/add")
    public String addBudget(@ModelAttribute Budget budget) {
        budgetRepository.save(budget);
        return "redirect:/budget";
    }

    @GetMapping("/edit/{id}")
    public String editBudget(@PathVariable Long id, Model model) {
        Budget budget = budgetRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid budget ID"));
        model.addAttribute("budget", budget);
        model.addAttribute("budgets", budgetRepository.findAll());
        return "budget";
    }

    @PostMapping("/update")
    public String updateBudget(@ModelAttribute Budget budget) {
        budgetRepository.save(budget);
        return "redirect:/budget";
    }

    @PostMapping("/delete/{id}")
    public String deleteBudget(@PathVariable Long id) {
        budgetRepository.deleteById(id);
        return "redirect:/budget";
    }
}
