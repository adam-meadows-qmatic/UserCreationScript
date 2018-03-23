package com.qmatic.uk;

import com.opencsv.bean.CsvBindByPosition;

import java.util.Objects;

public class CSVUser {
    @CsvBindByPosition(position = 0)
    private String firstName;
    @CsvBindByPosition(position = 1)
    private String lastName;
    @CsvBindByPosition(position = 2)
    private String branch;

    @Override
    public String toString() {
        return "CSVUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CSVUser)) return false;
        CSVUser csvUser = (CSVUser) o;
        return Objects.equals(getFirstName(), csvUser.getFirstName()) &&
                Objects.equals(getLastName(), csvUser.getLastName()) &&
                Objects.equals(getBranch(), csvUser.getBranch());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getFirstName(), getLastName(), getBranch());
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
