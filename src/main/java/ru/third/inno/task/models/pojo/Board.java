package ru.third.inno.task.models.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yy on 17.02.17.
 */


public class Board {
    private int id;
    private int progress;
    private int person_id;
    private User user;
    private int subject_id;
    private Subject subject;

    public Board(int id, int progress, int person_id, int subject_id) {
        this.id = id;
        this.progress = progress;
        this.person_id = person_id;
        this.subject_id = subject_id;
    }

    public Board(int id, int progress, User user, Subject subject) {
        this.id = id;
        this.progress = progress;
        this.user = user;
        this.subject = subject;
    }


    public Board(int id, int progress, int person_id, int subject_id, User user) {
        this.id = id;
        this.progress = progress;
        this.person_id = person_id;
        this.user = user;
        this.subject_id = subject_id;
    }

    public Board() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
