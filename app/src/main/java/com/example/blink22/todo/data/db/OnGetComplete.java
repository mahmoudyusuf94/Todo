package com.example.blink22.todo.data.db;

import com.example.blink22.todo.data.model.Todo;

import java.util.List;

public interface OnGetComplete {

    void onSuccess(List<Todo> todoList);

    void onFail();

}
