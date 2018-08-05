package com.example.blink22.todo.data;

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
    public void updateTodo(Todo todo, OnTaskComplete callback) {
        mDbHelper.updateTodo(todo, callback);
    }

    @Override
    public void insertTodo(Todo todo, OnTaskComplete callback) {
        Log.i("fuck", "CALLINE INSERT TODO FROM THE MANAGER => TODO = "+ todo);
        mDbHelper.insertTodo(todo, callback);
    }

    @Override
    public void deleteTodo(String id ,OnTaskComplete callback) {
        mDbHelper.deleteTodo(id, callback);
    }

    @Override
    public List<Todo> getAllDoneTodos() {
        return mDbHelper.getAllDoneTodos();
    }

}
