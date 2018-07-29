package com.example.blink22.todo.data.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Todo extends RealmObject {

    @Ignore
    public static final String ID_FIELD_NAME = "id";
    @Ignore
    public static final String DONE_FIELD_NAME = "done";

    @PrimaryKey
    private String id;

    private String title;

    private String description;

    private Date date;

    private boolean done;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Todo(String title, Date date, boolean done){
        id = UUID.randomUUID().toString();
        this.title = title;
        this.date = date;
        this.done = done;
    }

    public Todo(){
        id = UUID.randomUUID().toString();
        date = Calendar.getInstance().getTime();
    }



}
