package com.nani.expensetracker.expensivedemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nani.expensetracker.expensivedemo.model.Expenses;

public interface ExpenseRepository extends JpaRepository<Expenses, Long> {
}
