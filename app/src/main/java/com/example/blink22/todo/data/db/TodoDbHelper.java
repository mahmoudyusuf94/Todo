package com.example.blink22.todo.data.db;

import android.content.Context;

import com.example.blink22.todo.data.model.Todo;
import com.example.blink22.todo.di.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;

@Singleton
public class TodoDbHelper implements DbHelper {

    public static final boolean NOT_DONE = false;
    public static final boolean DONE = true;

    private Realm realm;

    @Inject
    public TodoDbHelper(@ApplicationContext Context context){
        Realm.init(context);
        realm = Realm.getDefaultInstance();
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

    @Override
    public List<Todo> getAllDoneTodos() {
        return realm.where(Todo.class).equalTo(Todo.DONE_FIELD_NAME, DONE).findAll();
    }
}
