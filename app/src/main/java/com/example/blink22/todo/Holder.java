package com.example.blink22.todo;

import com.example.blink22.todo.data.model.Todo;

public interface Holder {
    void setTitle(String title);
    void setDone(boolean done);
    void setDate(String date);
    void setTodo(Todo todo);
}
