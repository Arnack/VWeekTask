package ru.third.inno.task.models.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yy on 17.02.17.
 */

public class Subject {
    private int id;
    private String name;
    private String description;
    private String sphere;
    private int popularity;

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getSphere() {
        return sphere;
    }

    public void setSphere(String sphere) {
        this.sphere = sphere;
    }

    public Subject(String name, String description, String sphere, int popularity) {
        this.name = name;
        this.description = description;
        this.sphere = sphere;
        this.popularity = popularity;
    }

    public Subject(int id, String name, String description, String sphere) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sphere = sphere;
    }

    public Subject(String name, String description, String sphere) {
        this.name = name;
        this.description = description;
        this.sphere = sphere;
    }

    public Subject(int id, String name, String description, String sphere, int popularity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sphere = sphere;
        this.popularity = popularity;
    }

    public Subject(int id, String name, String description, int popularity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.popularity = popularity;
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

    public Subject() {
    }

    public Subject(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

