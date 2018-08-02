package com.example.blink22.todo.data.db;

import com.example.blink22.todo.data.model.Todo;

import java.util.List;

public interface DbHelper {

    void getAllTodos(OnGetComplete callback);

    void getTodo(String id, OnGetComplete callback);

    void updateTodo(Todo todo);

    void insertTodo (Todo todo);

    void deleteTodo(String todoId);

    List<Todo> getAllDoneTodos();
}
