package com.example.blink22.todo.di.component;

import com.example.blink22.todo.di.PerActivity;
import com.example.blink22.todo.di.module.ActivityModule;
import com.example.blink22.todo.ui.SingleFragmentActivity;
import com.example.blink22.todo.ui.TodoDetails.TodoActivity;
import com.example.blink22.todo.ui.TodoDetails.TodoFragment;
import com.example.blink22.todo.ui.TodoList.TodoListActivity;
import com.example.blink22.todo.ui.TodoList.TodoListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies =  ApplicationComponent.class , modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(TodoFragment fragment);
    void inject(TodoListFragment fragment);

}
