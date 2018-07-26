package com.example.blink22.todo;

import java.util.Date;

public interface DetailsView {
    void setTodoTitleEditText(String title);
    void setTodoDescEditText(String desc);
    void setTodoDateButton(Date date);
}
