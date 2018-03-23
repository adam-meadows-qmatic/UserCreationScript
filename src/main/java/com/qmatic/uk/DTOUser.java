package com.qmatic.uk;

import java.util.*;

public class DTOUser {

    private String userName, password, firstName, lastName;
    private List<Integer> roles, branchGroups;
    private List<Integer> branches = new ArrayList<>();
    private Integer branchHierarchyRoot = 1;
    private String status = "ACTIVE";
    private Integer language = 1;
    private String imageUrl = null;
    private Map<String, String> data = new HashMap<>();
    private Integer pinCode;

    public DTOUser(String userName, String password, String firstName, String lastName, Integer branchHierarchyRoot, List<Integer> roles, List<Integer> branches, List<Integer> branchGroups) {
        this.branchHierarchyRoot = branchHierarchyRoot;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.branches = branches;
        this.branchGroups =
                branchGroups;
    }

    public DTOUser() {

    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBranchHierarchyRoot() {
        return branchHierarchyRoot;
    }

    public void setBranchHierarchyRoot(Integer branchHierarchyRoot) {
        this.branchHierarchyRoot = branchHierarchyRoot;
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

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }

    public List<Integer> getBranches() {
        return branches;
    }

    public void setBranches(List<Integer> branches) {
        this.branches = branches;
    }

    public List<Integer> getBranchGroups() {
        return branchGroups;
    }

    public void setBranchGroups(List<Integer> branchGroups) {
        this.branchGroups = branchGroups;
    }

    @Override
    public String toString() {
        return "DTOUser{" +
                "branchHierarchyRoot=" + branchHierarchyRoot +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roles=" + roles +
                ", branches=" + branches +
                ", branchGroups=" + branchGroups +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DTOUser)) return false;
        DTOUser dtoUser = (DTOUser) o;
        return Objects.equals(getBranchHierarchyRoot(), dtoUser.getBranchHierarchyRoot()) &&
                Objects.equals(getUserName(), dtoUser.getUserName()) &&
                Objects.equals(getPassword(), dtoUser.getPassword()) &&
                Objects.equals(getFirstName(), dtoUser.getFirstName()) &&
                Objects.equals(getLastName(), dtoUser.getLastName()) &&
                Objects.equals(getRoles(), dtoUser.getRoles()) &&
                Objects.equals(getBranches(), dtoUser.getBranches()) &&
                Objects.equals(getBranchGroups(), dtoUser.getBranchGroups());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getBranchHierarchyRoot(), getUserName(), getPassword(), getFirstName(), getLastName(), getRoles(), getBranches(), getBranchGroups());
    }
}
