package com.example.blink22.todo.data.model;

import java.util.Date;
import java.util.UUID;

public class Todo {

    private String mId;

    private String mTitle;

    private String mDescription;

    private Date mDate;

    private boolean mDone;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isDone() {
        return mDone;
    }

    public void setDone(boolean done) {
        mDone = done;
    }

    public Todo(String title, Date date, boolean done){
        mId = UUID.randomUUID().toString();
        mTitle = title;
        mDate = date;
        mDone = done;
    }


}
