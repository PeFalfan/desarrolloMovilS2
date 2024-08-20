package com.example.appeventos.models;

import java.util.Date;

public class EventModel {

    private int id = 0;
    private String title = "";
    private String description = "";
    private Date dateToRemember = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateToRemember() {
        return dateToRemember;
    }

    public void setDateToRemember(Date dateToRemember) {
        this.dateToRemember = dateToRemember;
    }
}
