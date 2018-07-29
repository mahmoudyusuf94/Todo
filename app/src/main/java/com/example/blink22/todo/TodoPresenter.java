package com.example.blink22.todo;

import android.content.Context;

import com.example.blink22.todo.data.TodoDataManager;
import com.example.blink22.todo.data.model.Todo;

import java.util.Date;

public class TodoPresenter implements DetailsPresenter{

    DetailsView mView;
    TodoDataManager db;

    public TodoPresenter (DetailsView view, Context context){
        mView = view;
        db = TodoDataManager.getInstance(context);
    }

    @Override
    public Todo prepareTodoView(String todoId) {
        if(todoId!=null){
            Todo todo = db.getTodo(todoId);
            if(todo != null){
                mView.setTodoTitleEditText(todo.getTitle());
                mView.setTodoDescEditText(todo.getDescription());
                mView.setTodoDateButton(todo.getDate());
                return todo;
            }
        }
        return new Todo();
    }

    @Override
    public void cancelTodo() {
        mView.cancel();
    }

    @Override
    public void doneTodo( Todo todo, boolean exists) {
        if(exists){
            db.updateTodo(todo);
        }else{
            db.insertTodo(todo);
        }
        mView.cancel();
    }

    @Override
    public void updateDateButton(Date date) {
        mView.setTodoDateButton(date);
    }
}
