package com.example.blink22.todo;

import android.content.Context;

import com.example.blink22.todo.data.model.Todo;

import java.util.Date;

public interface DetailsPresenter {

    Todo prepareTodoView(String todoId);
    void cancelTodo();
    void doneTodo( Todo todo, boolean exists);
    void changeDate(Context context, Date date);
    void updateDateButton(Date date);
}
