import axios from 'axios';

const BASE_URL = "http://localhost:8585"; // your Spring Boot backend URL

class ExpenseService {
  
  // Get all expenses
  getAllExpenses() {
    return axios.get(`${BASE_URL}/expenses`);
  }

  // Add a new expense
  addExpense(expense) {
    return axios.post(`${BASE_URL}/expenses`, expense);
  }

  // Set spending limit
  setLimit(limit) {
    return axios.post(`${BASE_URL}/limit`, { limit });
  }

  // Get total expenses and check limit
  getTotalExpenses() {
    return axios.get(`${BASE_URL}/total`);
  }
}

export default new ExpenseService();
