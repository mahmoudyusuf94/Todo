package com.example.blink22.todo.data;

import android.support.test.espresso.idling.CountingIdlingResource;
import android.util.Log;

import com.example.blink22.todo.data.db.OnTaskComplete;
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
    public void getAllTodos(OnTaskComplete callback) {
        mDbHelper.getAllTodos(callback);
    }

    @Override
    public void getTodo(String id, OnTaskComplete callback) {
        mDbHelper.getTodo(id, callback);
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
