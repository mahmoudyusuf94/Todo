package com.example.blink22.todo;

import android.util.Log;

import com.example.blink22.todo.data.TodoDb;
import com.example.blink22.todo.data.model.Todo;

public class TodoPresenter implements DetailsPresenter{

    DetailsView mView;
    TodoDb db;

    public TodoPresenter (DetailsView view){
        mView = view;
        db = new TodoDb();
    }

    @Override
    public void prepareTodoView(String todoId) {
        if(todoId!=null){
            Todo todo = db.getTodo(todoId);
            if(todo != null){
                mView.setTodoTitleEditText(todo.getTitle());
                mView.setTodoDescEditText(todo.getDescription());
                mView.setTodoDateButton(todo.getDate());
            }
        }
    }
}
