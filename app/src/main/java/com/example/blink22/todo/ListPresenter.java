package com.example.blink22.todo;

import android.content.Context;

import com.example.blink22.todo.data.model.Todo;

public interface ListPresenter {
    void notifyDataChanged();
    void bindViewHolderWithPosition(TodoAdapter.TodoHolder todoHolder, int position);
    int getTodoCount();
    void showTodoDetails(Context context, String id);
    void deleteTodo(Todo todo);
}
