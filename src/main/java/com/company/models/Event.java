package com.company.models;

public class Event {
    private long id;
    private String title;
    private String description;
    private String time;

    public Event() {
    }

    public Event(long id, String title, String description, String time) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
