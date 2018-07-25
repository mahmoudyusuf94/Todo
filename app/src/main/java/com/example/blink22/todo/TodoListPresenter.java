package com.example.blink22.todo;

import android.util.Log;
import android.view.View;

import com.example.blink22.todo.data.TodoDb;
import com.example.blink22.todo.data.TodoDbIF;
import com.example.blink22.todo.data.model.Todo;

import java.util.List;

public class TodoListPresenter implements ListPresenter{

    ListView mView;
    TodoDbIF mDb;
    List<Todo> mTodos;

    public TodoListPresenter(ListView view){
        mView = view;
        mDb = new TodoDb();
        mTodos = mDb.getAllTodos(); //to be changed
    }

    public void bindViewHolderWithPosition(TodoAdapter.TodoHolder todoHolder, int position) {
        Todo todo = mTodos.get(position);

        todoHolder.setTitle(todo.getTitle());
        todoHolder.setDone(todo.isDone());
        todoHolder.setDate(todo.getDate().toString());
        Log.i("date", todo.getDate().toString());
    }

    public int getTodoCount() {
        return mTodos.size();
    }
}
