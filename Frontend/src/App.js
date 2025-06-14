import './App.css';
import React from 'react';
import AddExpense from './components/AddExpense';
import ExpenseList from './components/ExpenseList';

function App() {
  return (
    <div className="App">
      <h1>Smart Expense Tracker 💸</h1>
      <AddExpense />
      <ExpenseList />
    </div>
  );
}

export default App;
