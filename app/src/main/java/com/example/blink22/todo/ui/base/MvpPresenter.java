package com.example.blink22.todo.ui.base;

import android.view.View;

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V view);
    void onDetach();
}
