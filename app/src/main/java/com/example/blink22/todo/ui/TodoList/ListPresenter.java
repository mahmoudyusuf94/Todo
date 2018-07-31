package com.example.blink22.todo.ui.TodoList;

import android.content.Context;

import com.example.blink22.todo.data.model.Todo;
import com.example.blink22.todo.ui.TodoDetails.TodoAdapter;

public interface ListPresenter {
    void notifyDataChanged();
    void bindViewHolderWithPosition(TodoAdapter.TodoHolder todoHolder, int position);
    int getTodoCount();
    void showTodoDetails(Context context, String id);
    void deleteTodo(Todo todo);
    void doneTodo(String todoId, TodoAdapter.TodoHolder todoHolder);
    void undoneTodo(String todoId, TodoAdapter.TodoHolder todoHolder);
}
