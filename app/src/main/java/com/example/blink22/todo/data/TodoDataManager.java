package com.example.blink22.todo.data;

import com.example.blink22.todo.data.model.Todo;
import com.example.blink22.todo.data.db.DbHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TodoDataManager implements DataManager, DbHelper {

    private DbHelper mDbHelper;

    @Inject
    public TodoDataManager(DbHelper dbHelper){
        mDbHelper = dbHelper;
    }

    @Override
    public List<Todo> getAllTodos() {
        return mDbHelper.getAllTodos();
    }

    @Override
    public Todo getTodo(String id) {
        return mDbHelper.getTodo(id);
    }

    @Override
    public void updateTodo(Todo todo) {
        mDbHelper.updateTodo(todo);
    }

    @Override
    public void insertTodo(Todo todo) {
        mDbHelper.insertTodo(todo);
    }

    @Override
    public void deleteTodo(String id) {
        mDbHelper.deleteTodo(id);
    }

    @Override
    public List<Todo> getAllDoneTodos() {
        return mDbHelper.getAllDoneTodos();
    }

}
