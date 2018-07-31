package com.example.blink22.todo.di.component;

import com.example.blink22.todo.di.PerActivity;
import com.example.blink22.todo.di.module.ActivityModule;
import com.example.blink22.todo.ui.MainPresenter;
import com.example.blink22.todo.ui.MainView;
import com.example.blink22.todo.ui.SingleFragmentActivity;
import com.example.blink22.todo.ui.TodoDetails.DetailsPresenter;
import com.example.blink22.todo.ui.TodoDetails.DetailsView;
import com.example.blink22.todo.ui.TodoDetails.TodoActivity;
import com.example.blink22.todo.ui.TodoDetails.TodoFragment;
import com.example.blink22.todo.ui.TodoList.ListPresenter;
import com.example.blink22.todo.ui.TodoList.ListView;
import com.example.blink22.todo.ui.TodoList.TodoListActivity;
import com.example.blink22.todo.ui.TodoList.TodoListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies =  ApplicationComponent.class , modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(TodoActivity activity);
    void inject(TodoListActivity activity);
    void inject(SingleFragmentActivity activity);

    void inject(TodoFragment fragment);
    void inject(TodoListFragment fragment);

}
