package com.example.blink22.todo.di.component;


import android.content.Context;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.widget.ListView;

import com.example.blink22.todo.data.DataManager;
import com.example.blink22.todo.di.ActivityContext;
import com.example.blink22.todo.di.ApplicationContext;
import com.example.blink22.todo.di.module.ApplicationModule;
import com.example.blink22.todo.ui.TodoDetails.TodoFragment;
import com.example.blink22.todo.ui.TodoDetails.TodoPresenter;
import com.example.blink22.todo.ui.TodoList.ListPresenter;
import com.example.blink22.todo.ui.TodoList.TodoListFragment;
import com.example.blink22.todo.ui.TodoList.TodoListPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    DataManager getDataManager();

    @ApplicationContext
    Context getAppContext();

    @ActivityContext
    Context getActContext();

    CountingIdlingResource getIdlingResource();


}
