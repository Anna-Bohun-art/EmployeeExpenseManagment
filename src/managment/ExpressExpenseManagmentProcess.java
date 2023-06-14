package managment;

import com.virtualprogrammers.expenses.domain.Employee;
import com.virtualprogrammers.expenses.domain.ExpenseClaim;

public class ExpressExpenseManagmentProcess implements ExpenseManagementProcess{
    private ExpenseClaim claim;

    @Override
    public int registerExpenseClaim(ExpenseClaim claim) {

        this.claim = claim;
        return -1;
    }

    @Override
    public boolean approveClaim(int id, Employee employee) {
        return(claim.getTotalAmount() < 50);
    }
}
