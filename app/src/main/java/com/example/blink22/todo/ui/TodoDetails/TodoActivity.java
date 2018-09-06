package com.example.blink22.todo.ui.TodoDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.blink22.todo.di.component.ActivityComponent;
import com.example.blink22.todo.di.module.ContextModule;
import com.example.blink22.todo.ui.SingleFragmentActivity;
import com.example.blink22.todo.ui.TodoList.ListPresenter;

import javax.inject.Inject;

public class TodoActivity extends SingleFragmentActivity {

    private static final String EXTRA_TODO_ID = "com.example.blink22.todo.extra_todo_id";
    private String mTodoId;

    public static Intent newIntent(Context context) {
        return new Intent(context, TodoActivity.class);
    }

    @Override
    protected Fragment createFragment() {
        mTodoId = getIntent().getStringExtra(EXTRA_TODO_ID);
        return TodoFragment.newInstance(mTodoId);
    }

    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, TodoActivity.class);
        intent.putExtra(EXTRA_TODO_ID, id );
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTodoId = getIntent().getStringExtra(EXTRA_TODO_ID);
    }

}
