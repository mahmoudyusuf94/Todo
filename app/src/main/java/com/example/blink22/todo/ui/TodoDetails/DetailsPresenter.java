package com.example.blink22.todo.ui.TodoDetails;

import android.content.Context;

import com.example.blink22.todo.data.model.Todo;
import com.example.blink22.todo.ui.base.MvpPresenter;

import java.util.Date;

public interface DetailsPresenter<V extends DetailsView> extends MvpPresenter<V> {

    void prepareTodoView(String todoId);
    void cancelTodo();
    void doneTodo( Todo todo, boolean exists);
    void updateDateButton(Date date);
}
