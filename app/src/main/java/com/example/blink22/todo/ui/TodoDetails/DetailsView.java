package com.example.blink22.todo.ui.TodoDetails;

import java.util.Date;

public interface DetailsView {
    void setTodoTitleEditText(String title);
    void setTodoDescEditText(String desc);
    void setTodoDateButton(Date date);
    void cancel();
}
