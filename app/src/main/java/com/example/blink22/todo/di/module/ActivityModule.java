package com.example.blink22.todo.di.module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.example.blink22.todo.data.DataManager;
import com.example.blink22.todo.di.ActivityContext;
import com.example.blink22.todo.di.PerActivity;
import com.example.blink22.todo.ui.MainPresenter;
import com.example.blink22.todo.ui.MainView;
import com.example.blink22.todo.ui.TodoDetails.DetailsPresenter;
import com.example.blink22.todo.ui.TodoDetails.DetailsView;
import com.example.blink22.todo.ui.TodoDetails.TodoPresenter;
import com.example.blink22.todo.ui.TodoList.ListPresenter;
import com.example.blink22.todo.ui.TodoList.TodoListView;
import com.example.blink22.todo.ui.TodoList.TodoListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    @Provides
    @PerActivity
    public ListPresenter<TodoListView> listPresenter(DataManager dataManager){
        return new TodoListPresenter<>(dataManager);
    }

    @Provides
    @PerActivity
    public DetailsPresenter<DetailsView> detailsPresenter(DataManager dataManager){
        return new TodoPresenter<>(dataManager);
    }

    @Provides
    @PerActivity
    public LinearLayoutManager linearLayout(@ActivityContext Context context){
        return new LinearLayoutManager(context);
    }



}
