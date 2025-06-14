import React, { useEffect, useState } from 'react';
import ExpenseService from '../services/ExpenseService';

const ExpenseList = () => {
  const [expenses, setExpenses] = useState([]);

  useEffect(() => {
    ExpenseService.getAllExpenses()
      .then(response => {
        setExpenses(response.data);
      })
      .catch(error => {
        console.error('Error fetching expenses:', error);
      });
  }, []);

  return (
    <div style={{ margin: '30px' }}>
      <h2>All Expenses</h2>
      <table style={{ width: '100%', borderCollapse: 'collapse' }}>
        <thead>
          <tr style={{ backgroundColor: '#f2f2f2' }}>
            <th style={{ border: '1px solid #ccc', padding: '10px' }}>Title</th>
            <th style={{ border: '1px solid #ccc', padding: '10px' }}>Category</th>
            <th style={{ border: '1px solid #ccc', padding: '10px' }}>Amount (₹)</th>
          </tr>
        </thead>
        <tbody>
          {expenses.map((exp, index) => (
            <tr key={index}>
              <td style={{ border: '1px solid #ccc', padding: '10px' }}>{exp.title}</td>
              <td style={{ border: '1px solid #ccc', padding: '10px' }}>{exp.category}</td>
              <td style={{ border: '1px solid #ccc', padding: '10px' }}>{exp.amount}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ExpenseList;
