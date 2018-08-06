package com.example.blink22.todo.ui.TodoList;

import android.content.Context;

import com.example.blink22.todo.data.model.Todo;
import com.example.blink22.todo.ui.base.MvpPresenter;

public interface ListPresenter<V extends TodoListView> extends MvpPresenter<V> {

    void notifyDataChanged();

    void bindViewHolderWithPosition(Holder todoHolder, int position);

    int getTodoCount();

    void openTodoDetails(Context context, String id);

    void deleteTodo(Todo todo);

    void doneTodo(String todoId, TodoAdapter.TodoHolder todoHolder);

    void undoneTodo(String todoId, TodoAdapter.TodoHolder todoHolder);

    void fabClicked();
}
