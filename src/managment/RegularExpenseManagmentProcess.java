package managment;

import com.virtualprogrammers.expenses.domain.Employee;
import com.virtualprogrammers.expenses.domain.ExpenseClaim;
import com.virtualprogrammers.expenses.domain.StaffEmployee;

import java.util.ArrayList;
import java.util.List;

public class RegularExpenseManagmentProcess implements ExpenseManagementProcess{
    List<ExpenseClaim> claims = new ArrayList<>();

    @Override
    public int registerExpenseClaim(ExpenseClaim claim) {
        claims.add(claim);
        return claims.size() -1;
    }

    @Override
    public boolean approveClaim(int id, Employee employee) {
        ExpenseClaim claim = claims.get(id);
        if(claim.getTotalAmount() < 100){
            return true;
        } if(employee instanceof StaffEmployee){
            return true;
        }
        return false;
    }
}
