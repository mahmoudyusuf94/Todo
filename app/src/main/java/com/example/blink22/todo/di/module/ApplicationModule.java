package com.example.blink22.todo.di.module;

import android.content.Context;

import com.example.blink22.todo.data.DataManager;
import com.example.blink22.todo.data.TodoDataManager;
import com.example.blink22.todo.data.model.DbHelper;
import com.example.blink22.todo.data.model.TodoDbHelper;
import com.example.blink22.todo.di.ApplicationContext;
import com.example.blink22.todo.di.PerActivity;
import com.example.blink22.todo.ui.TodoDetails.DetailsPresenter;
import com.example.blink22.todo.ui.TodoDetails.DetailsView;
import com.example.blink22.todo.ui.TodoDetails.TodoPresenter;
import com.example.blink22.todo.ui.TodoList.ListPresenter;
import com.example.blink22.todo.ui.TodoList.ListView;
import com.example.blink22.todo.ui.TodoList.TodoListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module(includes = ContextModule.class)
public class ApplicationModule {


    @Provides
    @Singleton
    public DataManager dataManager(DbHelper dbHelper){
        return new TodoDataManager(dbHelper);
    }

    @Provides
    @Singleton
    public DbHelper getDbHelper(@ApplicationContext Context context){
        return new TodoDbHelper(context);
    }
}
