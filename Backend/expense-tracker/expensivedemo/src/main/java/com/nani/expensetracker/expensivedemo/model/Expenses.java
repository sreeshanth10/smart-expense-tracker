package com.nani.expensetracker.expensivedemo.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String category;
    private double amount;

    private LocalDate createdAt;

    public Expenses() {
        this.createdAt = LocalDate.now();  // 🛠️ Automatically set today's date
    }

    public Expenses(String title, String category, double amount) {
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.createdAt = LocalDate.now();  // 🛠️ Automatically set today's date
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
