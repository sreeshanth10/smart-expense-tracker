package com.nani.expensetracker.expensivedemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.nani.expensetracker.expensivedemo.Repository.ExpenseLimitRepository;
import com.nani.expensetracker.expensivedemo.Repository.ExpenseRepository;
import com.nani.expensetracker.expensivedemo.model.ExpenseLimit;
import com.nani.expensetracker.expensivedemo.model.Expenses;
import com.nani.expensetracker.expensivedemo.model.TotalExpenseResponse;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    // Get all expenses
    @GetMapping
    public List<Expenses> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Get expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<Expenses> getExpenseById(@PathVariable Long id) {
        Optional<Expenses> expense = expenseRepository.findById(id);
        return expense.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Add a new expense
    @PostMapping
    public ResponseEntity<Expenses> addExpense(@RequestBody Expenses expense) {
        Expenses savedExpense = expenseRepository.save(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExpense);
    }

    // Update an existing expense
    @PutMapping("/{id}")
    public ResponseEntity<Expenses> updateExpense(@PathVariable Long id, @RequestBody Expenses expenseDetails) {
        Optional<Expenses> existingExpense = expenseRepository.findById(id);
        if (existingExpense.isPresent()) {
            Expenses expense = existingExpense.get();
            expense.setTitle(expenseDetails.getTitle());
            expense.setCategory(expenseDetails.getCategory());
            expense.setAmount(expenseDetails.getAmount());
            return ResponseEntity.ok(expenseRepository.save(expense));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete an expense by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
     
       @Autowired
private ExpenseLimitRepository expenseLimitRepository;

    // API to set the monthly limit
    @PostMapping("/limit")
    public ResponseEntity<ExpenseLimit> setExpenseLimit(@RequestBody ExpenseLimit limit) {
        ExpenseLimit savedLimit = expenseLimitRepository.save(limit);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLimit);
    }

    // API to get the monthly limit
    @GetMapping("/limit")
    public ResponseEntity<ExpenseLimit> getExpenseLimit() {
        // For now, we assume there is only one limit stored
        if (expenseLimitRepository.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(expenseLimitRepository.findAll().get(0));
    }
    @GetMapping("/total")
    public ResponseEntity<TotalExpenseResponse> getTotalExpense(@RequestParam int month, @RequestParam int year) {
        List<Expenses> expenses = expenseRepository.findAll();
        double total = expenses.stream()
                .filter(e -> e.getCreatedAt() != null &&
                             e.getCreatedAt().getMonthValue() == month &&
                             e.getCreatedAt().getYear() == year)
                .mapToDouble(Expenses::getAmount)
                .sum();
    
        // ✅ Fetch latest limit
        ExpenseLimit limit = expenseLimitRepository.findTopByOrderByIdDesc();
    
        String warning;
    
        if (limit != null && total > limit.getLimitAmount()) {
            warning = "Warning: You have exceeded your monthly expense limit!";
        } else {
            warning = "You are within your monthly expense limit.";
        }
    
        TotalExpenseResponse response = new TotalExpenseResponse(total, warning);
    
        return ResponseEntity.ok(response);
    }
    




}
