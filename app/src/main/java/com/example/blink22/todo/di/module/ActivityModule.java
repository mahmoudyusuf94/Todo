package com.example.blink22.todo.di.module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.example.blink22.todo.data.DataManager;
import com.example.blink22.todo.di.ActivityContext;
import com.example.blink22.todo.di.PerActivity;
import com.example.blink22.todo.ui.MainPresenter;
import com.example.blink22.todo.ui.MainView;
import com.example.blink22.todo.ui.SingleFragmentActivityPresenter;
import com.example.blink22.todo.ui.TodoDetails.DetailsPresenter;
import com.example.blink22.todo.ui.TodoDetails.DetailsView;
import com.example.blink22.todo.ui.TodoDetails.TodoPresenter;
import com.example.blink22.todo.ui.TodoList.ListPresenter;
import com.example.blink22.todo.ui.TodoList.ListView;
import com.example.blink22.todo.ui.TodoList.TodoListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    @Provides
    @PerActivity
    public ListPresenter<ListView> listPresenter(DataManager dataManager){
        return new TodoListPresenter<>(dataManager);
    }

    @Provides
    @PerActivity
    public DetailsPresenter<DetailsView> detailsPresenter(DataManager dataManager){
        return new TodoPresenter<>(dataManager);
    }

    @Provides
    @PerActivity
    public MainPresenter<MainView> mainPresenter(){
        return new SingleFragmentActivityPresenter<>();
    }

    @Provides
    @PerActivity
    public LinearLayoutManager linearLayout(@ActivityContext Context context){
        return new LinearLayoutManager(context);
    }



}
