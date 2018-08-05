package com.example.blink22.todo.data.db;

import com.example.blink22.todo.data.model.Todo;

import java.util.List;

public interface DbHelper {

    void getAllTodos(OnTaskComplete callback);

    void getTodo(String id, OnTaskComplete callback);

    void updateTodo(Todo todo, OnTaskComplete callback);

    void insertTodo (Todo todo, OnTaskComplete callback);

    void deleteTodo(String todoId, OnTaskComplete callback);

    List<Todo> getAllDoneTodos();
}
