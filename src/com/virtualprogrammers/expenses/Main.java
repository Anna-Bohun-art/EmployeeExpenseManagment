package com.virtualprogrammers.expenses;
import com.virtualprogrammers.expenses.domain.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setTitle("Ms");
        employee1.setFirstName("Anna");
        employee1.setSurname("Kladova");

        System.out.println(employee1.getMailingName());
        System.out.println(employee1.getMailingName(true));

        Employee employee2 = new Employee(2, "Manager");
        employee2.setTitle("Dr");
        employee2.setFirstName("Denis");
        employee2.setSurname("Yellow");

        ExpenseClaim expenseClaim = new ExpenseClaim(24, 356, LocalDate.now());
        System.out.println(expenseClaim.getEmployeeId());
        expenseClaim.setPaid(true);
        System.out.println(expenseClaim.getPaid());
        expenseClaim.setApproved(true);
        expenseClaim.setPaid(true);
        System.out.println(expenseClaim.getPaid());
        ExpenseItem expenseItem = new ExpenseItem(2445,467, ExpenseType.ACCOMODATION, "The Grand Hotel", 69.99);
        System.out.println(expenseItem.getDescription());

        Employees employees = new Employees();
        employees.addEmployee(employee1);
        employees.addEmployee(employee2);
        employees.printEmployees();

        employees.addEmployee(new Employee(3,"Mrs", "Susan", "Brown", "Director", Department.FINANCE));

        Employee foundEmployee1 = employees.findBySurname("Brown");
        System.out.println("Found" + foundEmployee1.getMailingName());

        Employee foundEmployee2 = employees.findBySurname("Cyan");
        System.out.println("Didn´t find" + (foundEmployee2 == null));
        System.out.println(employee1.toString());


    }
}