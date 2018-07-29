package com.example.blink22.todo.data;

import com.example.blink22.todo.data.model.Todo;

import java.util.List;

public interface TodoDb {

    List<Todo> getAllTodos();

    Todo getTodo(String id);

    void updateTodo(Todo todo);

    void insertTodo (Todo todo);

    void deleteTodo(String todoId);

    List<Todo> getAllDoneTodos();
}
