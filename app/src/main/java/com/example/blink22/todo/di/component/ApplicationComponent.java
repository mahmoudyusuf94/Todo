package com.example.blink22.todo.di.component;


import android.content.Context;

import com.example.blink22.todo.data.DataManager;
import com.example.blink22.todo.di.ActivityContext;
import com.example.blink22.todo.di.ApplicationContext;
import com.example.blink22.todo.di.module.ApplicationModule;

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
}
