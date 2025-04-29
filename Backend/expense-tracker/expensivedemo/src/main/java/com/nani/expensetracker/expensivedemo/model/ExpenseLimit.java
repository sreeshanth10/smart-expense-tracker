package com.nani.expensetracker.expensivedemo.model;

import jakarta.persistence.*;

@Entity
public class ExpenseLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double limitAmount;

    public ExpenseLimit() {
    }

    public ExpenseLimit(double limitAmount) {
        this.limitAmount = limitAmount;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public double getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(double limitAmount) {
        this.limitAmount = limitAmount;
    }
}
