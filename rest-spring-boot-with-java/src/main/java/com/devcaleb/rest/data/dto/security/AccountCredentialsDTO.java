package com.devcaleb.rest.data.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AccountCredentialsDTO {

    @JsonProperty("username")
    private String userName;
    private String password;
    @JsonProperty("fullname")
    private String fullName;

    public AccountCredentialsDTO() {
    }

    public AccountCredentialsDTO(String userName, String password, String fullName) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AccountCredentialsDTO that = (AccountCredentialsDTO) o;
        return Objects.equals(userName, that.userName) && Objects.equals(password, that.password) && Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, fullName);
    }

    @Override
    public String toString() {
        return "AccountCredentialsDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
