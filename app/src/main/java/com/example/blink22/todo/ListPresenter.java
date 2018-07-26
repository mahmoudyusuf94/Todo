package com.example.blink22.todo;

import android.content.Context;

public interface ListPresenter {
    void notifyDataChanged();
    void bindViewHolderWithPosition(TodoAdapter.TodoHolder todoHolder, int position);
    int getTodoCount();
    void showTodoDetails(Context context, String id);
}
