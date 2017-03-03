package ru.third.inno.task.models.pojo;

import java.util.Date;

/**
 * Created by yy on 17.02.17.
 */

public class User {
    public int id;
    public String login;
    private String password;
    public String description;
    public Date timestamp;
    public String role;


    public User() {

    }

    public User(int id, String login, String password, String description, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.description = description;
        this.role = role;
    }

    public User(int id, String login, String password, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String login, String password, String description, String role) {
        this.login = login;
        this.password = password;
        this.description = description;
        this.role = role;
    }

    public User(String login, String password, String description) {
        this.login = login;
        this.password = password;
        this.description = description;
    }

    public User(String login, String description) {
        this.login = login;
        this.description = description;
    }

    public User(int id, String login, String description, Date timestamp) {
        this.id = id;
        this.login = login;
        this.description = description;
        this.timestamp = timestamp;
    }

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                ", role='" + role + '\'' +
                '}';
    }
}
