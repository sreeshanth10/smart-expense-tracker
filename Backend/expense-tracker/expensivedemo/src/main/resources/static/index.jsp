<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Expense Tracker</title>
</head>
<body>
  <h1>Expense Tracker</h1>

  <form id="expenseForm">
    <input type="text" id="title" placeholder="Title" required><br>
    <input type="text" id="category" placeholder="Category" required><br>
    <input type="number" id="amount" placeholder="Amount" required><br>
    <button type="submit">Add Expense</button>
  </form>

  <h2>All Expenses</h2>
  <ul id="expenseList"></ul>

  <script>
    const form = document.getElementById('expenseForm');
    const expenseList = document.getElementById('expenseList');

    form.addEventListener('submit', async (e) => {
      e.preventDefault();

      const data = {
        title: document.getElementById('title').value,
        category: document.getElementById('category').value,
        amount: parseFloat(document.getElementById('amount').value)
      };

      await fetch('/expenses', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
      });

      loadExpenses(); // refresh the list
    });

    async function loadExpenses() {
      const res = await fetch('/expenses');
      const expenses = await res.json();

      expenseList.innerHTML = '';
      expenses.forEach(exp => {
        const li = document.createElement('li');
        li.textContent = `${exp.title} - ₹${exp.amount} (${exp.category})`;
        expenseList.appendChild(li);
      });
    }

    loadExpenses();
  </script>
</body>
</html>
