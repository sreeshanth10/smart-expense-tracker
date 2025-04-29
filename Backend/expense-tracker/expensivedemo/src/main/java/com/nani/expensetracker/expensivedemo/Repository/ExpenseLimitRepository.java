


package com.nani.expensetracker.expensivedemo.Repository;

import com.nani.expensetracker.expensivedemo.model.ExpenseLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseLimitRepository extends JpaRepository<ExpenseLimit, Long> {
    // ✅ Add this to get latest limit
    ExpenseLimit findTopByOrderByIdDesc();
}
