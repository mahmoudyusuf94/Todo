package com.example.blink22.todo.data;

import android.content.Context;

import com.example.blink22.todo.data.model.Todo;

import java.util.List;

import io.realm.Realm;

public class TodoDataManager implements TodoDb {

    private static TodoDataManager dbSingletonInstance;

    private static Realm realm;

    public static TodoDataManager getInstance(Context context){
        if(dbSingletonInstance == null){
            dbSingletonInstance = new TodoDataManager(context);
        }
        return dbSingletonInstance;
    }

    private TodoDataManager(Context context){
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    private TodoDataManager(){

    }

    @Override
    public List<Todo> getAllTodos() {
        return realm.where(Todo.class).findAll();
    }

    @Override
    public Todo getTodo(String id) {
        return realm.copyFromRealm(realm.where(Todo.class).equalTo(Todo.ID_FIELD_NAME, id).findFirst());
    }

    @Override
    public void updateTodo(Todo todo) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(todo);
        realm.commitTransaction();
    }

    @Override
    public void insertTodo(Todo todo) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(todo);
        realm.commitTransaction();
    }

    @Override
    public void deleteTodo(String id) {
        realm.beginTransaction();
        Todo result = realm.where(Todo.class).equalTo(Todo.ID_FIELD_NAME, id).findFirst();
        if(result != null){
            result.deleteFromRealm();
        }
        realm.commitTransaction();
    }

    private Todo getRealmTodo(String id){
        return realm.copyFromRealm(realm.where(Todo.class).equalTo(Todo.ID_FIELD_NAME, id).findFirst());
    }

}
