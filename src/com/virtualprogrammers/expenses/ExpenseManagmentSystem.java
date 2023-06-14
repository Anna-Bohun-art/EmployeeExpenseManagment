package com.virtualprogrammers.expenses;

import com.virtualprogrammers.expenses.domain.Employee;
import com.virtualprogrammers.expenses.domain.Employees;
import com.virtualprogrammers.expenses.domain.ExpenseClaim;
import com.virtualprogrammers.expenses.domain.StaffEmployee;
import com.virtualprogrammers.expenses.exceptions.EmployeeNotFoundException;
import com.virtualprogrammers.expenses.ui.UIFunctions;
import com.virtualprogrammers.expenses.utilities.ExpenseAnalysis;
import com.virtualprogrammers.expenses.utilities.ExpenseAnalysisTempImpl;
import managment.ExpenseManagementProcess;
import managment.ExpressExpenseManagmentProcess;
import managment.RegularExpenseManagmentProcess;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;

public class ExpenseManagmentSystem {
    public ExpenseManagmentSystem() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {
        Employees employees = new Employees();
        Scanner scanner = new Scanner(System.in);
        UIFunctions uiFunctions = new UIFunctions();
        ExpenseManagementProcess expressEMP = new ExpressExpenseManagmentProcess();
        ExpenseManagementProcess regularEMP = new RegularExpenseManagmentProcess();


        boolean readyToExit = false;
        while (!readyToExit) {

            System.out.println("Expense Management System");
            System.out.println("-------------------------");
            System.out.println("e- register new employee");
            System.out.println("c- register new claim");
            System.out.println("p- print all employees");
            System.out.println("a-approve claim");
            System.out.println("r1-outstanding expense claims");
            System.out.println("r2-paid expense claims");
            System.out.println("r2-expense claims above specified amount");
            System.out.println("x- exit");
            String option = scanner.nextLine();
            ExpenseAnalysis expenseAnalysis = new ExpenseAnalysisTempImpl();

            switch (option) {
                case "e":
                    Employee e = uiFunctions.registerNewEmployee();
                    employees.addEmployee(e);
                    break;
                case "c":
                    ExpenseClaim claim = uiFunctions.registerNewExpenseClaim();
                    try {
                        employees.addExpenseClaim(claim);
                        expressEMP.registerExpenseClaim(claim);
                        int id =  regularEMP.registerExpenseClaim((claim));
                        System.out.println("The claim has been registered with ID "+ id);
                    } catch (EmployeeNotFoundException ex) {
                        System.out.println("There was no employee with ID" + claim.getEmployeeId());
                    }
                    //register the new claim
                    break;
                case "a":
                    //get the claim ID

                    System.out.println("Enter the id of the claim ID");
                    int claimId = scanner.nextInt();
                    scanner.nextLine();
                    //get employee ID

                    System.out.println("Enter the id of the employee ID");
                    int employeeId = scanner.nextInt();
                    scanner.nextLine();
                    //find the employee
                    Employee foundEmployee = employees.findById(employeeId);
                    System.out.println("Enter r for regular or e for express ");
                    String claimType = scanner.nextLine();
                    ExpenseManagementProcess requestedProcess;
                    //call relevant method
                    if (claimType.equals("r")) {
                        requestedProcess = regularEMP;
                    } else {
                        requestedProcess = expressEMP;
                    }
                    boolean result = requestedProcess.approveClaim(claimId, foundEmployee);
                    System.out.println("The result was" + result);
                    break;
                case "p":
                    employees.printEmployees();
                    break;
                case "x":
                    readyToExit = true;
                    break;
                case "r1":
                    expenseAnalysis.printOutstandingClaims();
                    break;
                case "r2":
                    System.out.println("Enter date from");
                    String dateFrom = scanner.nextLine();

                    System.out.println("Enter date from");
                    String dateTo = scanner.nextLine();

                    expenseAnalysis.printPaidClaims(LocalDate.parse(dateFrom), LocalDate.parse(dateTo));;
                    break;
                case "r3":
                    System.out.println("Enter amount");
                    Double amount = scanner.nextDouble();
                    scanner.nextLine();
                    expenseAnalysis.printClaimsOverAmount(amount);;
                    break;
                default:
                    System.out.println("Option not valid");
            }
        }
    }
}
