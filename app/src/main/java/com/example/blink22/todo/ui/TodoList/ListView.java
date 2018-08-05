package com.example.blink22.todo.ui.TodoList;

import com.example.blink22.todo.ui.base.MvpView;

public interface ListView extends MvpView{
    void updateAdapter();
    void showWait();
    void hideWait();
}
