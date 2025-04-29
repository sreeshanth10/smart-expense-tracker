package com.nani.expensetracker.expensivedemo.model;

public class TotalExpenseResponse {
    private double totalExpense;
    private String warningMessage;

    public TotalExpenseResponse(double totalExpense, String warningMessage) {
        this.totalExpense = totalExpense;
        this.warningMessage = warningMessage;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }
}
