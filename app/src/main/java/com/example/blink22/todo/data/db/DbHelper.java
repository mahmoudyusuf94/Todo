package com.example.blink22.todo.data.db;

import com.example.blink22.todo.data.model.Todo;

import java.util.List;

public interface DbHelper {

    void getAllTodos(OnTaskComplete callback);

    void getTodo(String id, OnTaskComplete callback);

    void updateTodo(Todo todo);

    void insertTodo (Todo todo);

    void deleteTodo(String todoId);

    List<Todo> getAllDoneTodos();
}
