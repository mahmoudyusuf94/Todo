package com.example.blink22.todo;

import android.support.v4.app.Fragment;

public class TodoActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new TodoFragment();
    }
}
