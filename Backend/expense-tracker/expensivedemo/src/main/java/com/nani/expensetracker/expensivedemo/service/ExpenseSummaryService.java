package com.nani.expensetracker.expensivedemo.service;

import com.nani.expensetracker.expensivedemo.model.Expense;
import com.nani.expensetracker.expensivedemo.Repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseSummaryService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getMonthlyExpenseSummary() {
        List<com.nani.expensetracker.expensivedemo.model.Expenses> allExpenses = expenseRepository.findAll();

        return allExpenses.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getCreatedAt().getMonth().toString(), 
                        Collectors.summingDouble(com.nani.expensetracker.expensivedemo.model.Expenses::getAmount)
                ))
                .entrySet()
                .stream()
                .map(entry -> new Expense(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
