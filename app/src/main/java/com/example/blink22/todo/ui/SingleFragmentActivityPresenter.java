package com.example.blink22.todo.ui;

import com.example.blink22.todo.ui.base.BasePresenter;

public class SingleFragmentActivityPresenter<V extends MainView>  extends BasePresenter<V>
        implements MainPresenter<V> {

    public void addTodoSelected() {
        getMvpView().showAddTodo();
    }

    public void listTodoSelected(){
        getMvpView().showTodoList();
    }

}
