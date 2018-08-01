package com.example.blink22.todo.data.db;

import com.example.blink22.todo.data.model.Todo;

import java.util.List;

public interface DbHelper {

    List<Todo> getAllTodos();

    Todo getTodo(String id);

    void updateTodo(Todo todo);

    void insertTodo (Todo todo);

    void deleteTodo(String todoId);

    List<Todo> getAllDoneTodos();
}
