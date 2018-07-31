package com.example.blink22.todo.ui.TodoList;

import android.support.v4.app.Fragment;

import com.example.blink22.todo.ui.SingleFragmentActivity;

public class TodoListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TodoListFragment();
    }
}
