package ru.third.inno.task.models.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yy on 17.02.17.
 */

@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class Task {
    private int id;
    private String name;
    private String description;
    private User user;
    private int person_id;
    private Subject subject;
    private int subject_id;
    private int isDone;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public Task(int id, String name, String description, User user, Subject subject) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
        this.subject = subject;
    }

    public Task(int id, String name, String description, int person_id, int subject_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.person_id = person_id;
        this.subject_id = subject_id;
    }

    public Task(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Task() {
    }

    public Task(int id, String name, String description, int person_id, int subject_id, int isDone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.person_id = person_id;
        this.subject_id = subject_id;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", person_id=" + person_id +
                ", subject=" + subject +
                ", subject_id=" + subject_id +
                ", isDone=" + isDone +
                '}';
    }
}
