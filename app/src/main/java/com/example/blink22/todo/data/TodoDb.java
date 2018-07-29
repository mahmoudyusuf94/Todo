package com.example.blink22.todo.data;

import com.example.blink22.todo.data.model.Todo;

import java.util.Date;
import java.util.List;

public interface TodoDb {

    List<Todo> getAllTodos();

    Todo getTodo(String id);

    void updateTodo(Todo todo);

    void insertTodo (Todo todo);

}
