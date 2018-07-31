package com.example.blink22.todo.ui.base;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mvpView;

    @Override
    public void onAttach(V view) {
        mvpView = view;
    }

    @Override
    public void onDetach() {
        mvpView = null;
    }

    public V getMvpView(){
        return mvpView;
    }

}
