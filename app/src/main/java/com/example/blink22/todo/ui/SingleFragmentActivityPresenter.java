package com.example.blink22.todo.ui;

public class SingleFragmentActivityPresenter {

    SingleFragmentActivity mActivity;

    public SingleFragmentActivityPresenter(SingleFragmentActivity activity){
        mActivity = activity;
    }


    public void addTodoSelected() {
        mActivity.showAddTodo();
    }

    public void listTodoSelected(){
        mActivity.showTodoList();
    }
}
