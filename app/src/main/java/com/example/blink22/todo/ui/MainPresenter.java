package com.example.blink22.todo.ui;

import com.example.blink22.todo.ui.base.MvpPresenter;

public interface MainPresenter<V extends MainView> extends MvpPresenter<V>{

    void addTodoSelected();

    void listTodoSelected();

}
