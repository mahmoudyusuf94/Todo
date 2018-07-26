package com.example.blink22.todo.data;

import com.example.blink22.todo.data.model.Todo;

import java.util.Date;
import java.util.List;

public interface TodoDbIF {

    List<Todo> getAllTodos();

    Todo getTodo(String id);

    void updateTodo(Todo todo);

    void insertTodo (Todo todo);

    void setTodoTitle(String id, String title);

    void setTodoDescription(String id, String description);

    void setTodoDone(String id, boolean done);

    Date getTodoDate(String id);

    void setTodoDate(String id, Date date);

    int getTodosCount();
}
