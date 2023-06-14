package com.virtualprogrammers.expenses.ui;

import com.virtualprogrammers.expenses.domain.*;
import com.virtualprogrammers.expenses.exceptions.InvalidEmployeeIdException;
import com.virtualprogrammers.expenses.exceptions.NameTooShortException;
import com.virtualprogrammers.expenses.utilities.EmployeeUtilities;

import java.time.LocalDate;
import java.util.Scanner;

public class UIFunctions {
    public Employee registerNewEmployee() {
        Scanner scanner = new Scanner(System.in);
        Employee employee = new Employee();
        EmployeeUtilities employeeUtilities = new EmployeeUtilities();
        boolean idIsValid = false;
        while(!idIsValid) {
            System.out.println("Enter the id");
            String inputId = scanner.nextLine();
            try {
                Integer id = employeeUtilities.validateEmployeeId(inputId);
                idIsValid = true;
                employee.setId(id);
            } catch (InvalidEmployeeIdException e) {
                System.out.println("The id you entered was invalid- try again");
            }
        }

        System.out.println("Enter the title");
        String title = scanner.nextLine();
        employee.setTitle(title);


        boolean nameIsValid = false;
        while(!nameIsValid) {
            System.out.println("Enter the firstname");
            String firstName = scanner.nextLine();
            employee.setFirstName(firstName);

            System.out.println("Enter the surname");
            String surname = scanner.nextLine();
            employee.setSurname(surname);

            try{
                employeeUtilities .validateEmployeeName(firstName,
                        surname);
                nameIsValid = true;
            } catch(NameTooShortException e) {
               System.out.println("The name you entered was not valid-try again");
            }

        }
        System.out.println("Enter the job title");
        String jobTitle = scanner.nextLine();
        employee.setJobTitle(jobTitle);

        boolean departmentIsValid = false;
        while(!departmentIsValid) {
            System.out.println("Enter the department");
            String department = scanner.nextLine();
            try {
                Department d = Department.valueOf(department.toUpperCase());
                employee.setDepartment(d);
                System.out.println(employee);
                departmentIsValid = true;
            } catch(IllegalArgumentException e) {
                System.out.println("The department you entered was not valid- try again");
            }
        }
        System.out.println("Is this a member of Staff?(Y/N)");
        String isAStaffMember = scanner.nextLine();
        if(isAStaffMember.toUpperCase().equals("Y")) {

            StaffEmployee staff= new StaffEmployee(employee);
            System.out.println("Enter the usename");
            String username = scanner.nextLine();
            staff.setUserName(username);

            System.out.println("Enter the password");
            String password = scanner.nextLine();
            staff.setPassword(password);
            return staff;
        } else {
            return(employee);
        }
    }

    public ExpenseClaim registerNewExpenseClaim() {
        Scanner scanner = new Scanner(System.in);
        EmployeeUtilities employeeUtilities = new EmployeeUtilities();
        System.out.println("Enter the claim id");
        int claimId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the employee id");
        int employeeId = scanner.nextInt();
        scanner.nextLine();

        LocalDate dateOfClaim = LocalDate.now();
        ExpenseClaim claim = new ExpenseClaim(claimId, employeeId, dateOfClaim);
       Boolean finished = false;
       while(!finished) {
           System.out.println("Enter the expense item id");
           int eiId = scanner.nextInt();
           scanner.nextLine();
           boolean expenseTypeIsValid = false;
           ExpenseType eiType = null;
           while(!expenseTypeIsValid) {
               System.out.println("Enter the expense type");
               String expenseType = scanner.nextLine();
               try {
                   eiType = ExpenseType.valueOf(expenseType.toUpperCase());

                   expenseTypeIsValid = true;
               } catch(IllegalArgumentException e) {
                   System.out.println("The expense type you entered was not valid- try again");
               }
           }
           System.out.println("Enter the description");
           String description = scanner.nextLine();
           System.out.println("Enter the amount");
           double amount = scanner.nextDouble();
           scanner.nextLine();
           ExpenseItem ei = new ExpenseItem(eiId, claimId, eiType, description, amount);
           claim.addExpenseItem(ei);
           System.out.println("Enter another expense item? (Y/N)");
           String anyMore = scanner.nextLine();
           if(!anyMore.toUpperCase().equals("Y")) {
            finished = true;
           }
       }
        return claim;
    }
}
