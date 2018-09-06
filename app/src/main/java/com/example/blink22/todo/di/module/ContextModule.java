package com.example.blink22.todo.di.module;

import android.content.Context;

import com.example.blink22.todo.di.ActivityContext;
import com.example.blink22.todo.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Singleton
    @Provides
    @ApplicationContext
    public Context context(){
        return context.getApplicationContext();
    }

    @Singleton
    @Provides
    @ActivityContext
    public Context activityContext(){
        return context;
    }

}

