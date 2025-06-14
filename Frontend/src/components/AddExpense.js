import React, { useState } from 'react';
import ExpenseService from '../services/ExpenseService';

const AddExpense = () => {
  const [title, setTitle] = useState('');
  const [category, setCategory] = useState('');
  const [amount, setAmount] = useState('');

  const saveExpense = (e) => {
    e.preventDefault();

    const expense = { title, category, amount };

    console.log('Saving Expense:', expense);

    ExpenseService.addExpense(expense)  // ✅ Correct method name
      .then((response) => {
        console.log('Expense saved successfully:', response.data);
        alert('Expense Saved Successfully ✅');
        setTitle('');
        setCategory('');
        setAmount('');
      })
      .catch((error) => {
        console.error('Error saving expense:', error);
        alert('Failed to Save Expense ❌');
      });
  };

  return (
    <div style={{ margin: '30px' }}>
      <h2>Add New Expense</h2>
      <form onSubmit={saveExpense}>
        <div>
          <label>Title: </label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
        </div>
        <br />
        <div>
          <label>Category: </label>
          <input
            type="text"
            value={category}
            onChange={(e) => setCategory(e.target.value)}
            required
          />
        </div>
        <br />
        <div>
          <label>Amount: </label>
          <input
            type="number"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            required
          />
        </div>
        <br />
        <button type="submit">Save Expense</button>
      </form>
    </div>
  );
};

export default AddExpense;
