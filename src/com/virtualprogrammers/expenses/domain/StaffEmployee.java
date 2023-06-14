package com.virtualprogrammers.expenses.domain;

import java.util.Objects;

public class StaffEmployee extends Employee implements Comparable<Employee>{
    private String userName;
    private String password;

    public StaffEmployee(Employee e) {
        super(e.getId(), e.getTitle(), e.getFirstName(), e.getSurname(), e.getJobTitle(), e.getDepartment());
    }
    public StaffEmployee(){
        super();
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StaffEmployee that = (StaffEmployee) o;
        return Objects.equals(userName, that.userName) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userName, password);
    }
}
