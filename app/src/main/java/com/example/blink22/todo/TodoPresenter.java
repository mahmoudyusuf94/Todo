package com.example.blink22.todo;

import android.content.Context;

import com.example.blink22.todo.data.DataManager;
import com.example.blink22.todo.data.TodoDataManager;
import com.example.blink22.todo.data.model.Todo;
import com.example.blink22.todo.data.model.TodoDbHelper;

import java.util.Date;

public class TodoPresenter implements DetailsPresenter{

    DetailsView mView;
    DataManager mDataManager;

    public TodoPresenter (DetailsView view, Context context){
        mView = view;
        mDataManager = new TodoDataManager(new TodoDbHelper(context));
    }

    @Override
    public Todo prepareTodoView(String todoId) {
        if(todoId!=null){
            Todo todo = mDataManager.getTodo(todoId);
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
            mDataManager.updateTodo(todo);
        }else{
            mDataManager.insertTodo(todo);
        }
        mView.cancel();
    }

    @Override
    public void updateDateButton(Date date) {
        mView.setTodoDateButton(date);
    }
}
