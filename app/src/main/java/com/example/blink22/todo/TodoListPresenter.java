package com.example.blink22.todo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
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
        todoHolder.setTodo(todo);
        Log.i("date", todo.getDate().toString());
    }

    public int getTodoCount() {
        return mTodos.size();
    }

    public void showTodoDetails(Context context, String id) {
        Intent intent = TodoActivity.newIntent(context, id);
        context.startActivity(intent);
    }
}
