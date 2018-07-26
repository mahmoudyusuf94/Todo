package com.example.blink22.todo;

import android.content.Context;

import com.example.blink22.todo.data.model.Todo;

import java.util.Date;

public interface DetailsPresenter {

    Todo prepareTodoView(String todoId);
    void cancelTodo();
    void doneTodo( Todo todo, boolean exists);
    void updateDateButton(Date date);
}
