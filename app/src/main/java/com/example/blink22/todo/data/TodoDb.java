package com.example.blink22.todo.data;

import com.example.blink22.todo.data.model.Todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoDb implements TodoDbIF{

    private List<Todo> mTodos;

    public TodoDb(){
        mTodos = new ArrayList<Todo>();
    }

    @Override
    public List<Todo> getAllTodos() {
        return mTodos;
    }

    @Override
    public Todo getTodo(String id) {
        return null;
    }

    @Override
    public void setTodoTitle(String id, String title) {

    }

    @Override
    public void setTodoDescription(String id, String description) {

    }

    @Override
    public void setTodoDone(String id, boolean done) {

    }

    @Override
    public Date getTodoDate(String id) {
        return null;
    }

    @Override
    public void SetTodoDate(String id, Date date) {

    }
}
