package com.example.blink22.todo.ui.TodoDetails;

import com.example.blink22.todo.ui.base.MvpView;

import java.util.Date;

public interface DetailsView extends MvpView {
    void setTodoTitleEditText(String title);
    void setTodoDescEditText(String desc);
    void setTodoDateButton(Date date);
    void cancel();
}
