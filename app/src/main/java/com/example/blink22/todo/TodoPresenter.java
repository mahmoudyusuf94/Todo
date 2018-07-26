package com.example.blink22.todo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.blink22.todo.data.TodoDb;
import com.example.blink22.todo.data.model.Todo;

import java.util.Date;

public class TodoPresenter implements DetailsPresenter{

    DetailsView mView;
    TodoDb db;

    public TodoPresenter (DetailsView view){
        mView = view;
        db = new TodoDb();
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

    }

    @Override
    public void changeDate(Context context, Date date) {

    }

    @Override
    public void updateDateButton(Date date) {
        mView.setTodoDateButton(date);
    }
}
