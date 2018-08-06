package com.example.blink22.todo.ui.TodoList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.blink22.todo.di.component.ActivityComponent;
import com.example.blink22.todo.di.module.ContextModule;
import com.example.blink22.todo.ui.SingleFragmentActivity;

import javax.inject.Inject;

public class TodoListActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, TodoListActivity.class);
    }

    @Override
    protected Fragment createFragment() {
        return new TodoListFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
