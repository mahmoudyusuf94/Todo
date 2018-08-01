package com.example.blink22.todo.ui.TodoDetails;

import com.example.blink22.todo.data.DataManager;
import com.example.blink22.todo.data.model.Todo;
import com.example.blink22.todo.ui.base.BasePresenter;

import java.util.Date;

public class TodoPresenter<V extends DetailsView> extends BasePresenter<V>
        implements DetailsPresenter<V>{

    DataManager mDataManager;

    public TodoPresenter (DataManager dataManager){
        mDataManager = dataManager;
    }

    @Override
    public Todo prepareTodoView(String todoId) {
        if(todoId!=null){
            Todo todo = mDataManager.getTodo(todoId);
            if(todo != null){
                getMvpView().setTodoTitleEditText(todo.getTitle());
                getMvpView().setTodoDescEditText(todo.getDescription());
                getMvpView().setTodoDateButton(todo.getDate());
                return todo;
            }
        }
        return new Todo();
    }

    @Override
    public void cancelTodo() {
        getMvpView().cancel();
    }

    @Override
    public void doneTodo( Todo todo, boolean exists) {
        if(exists){
            mDataManager.updateTodo(todo);
        }else{
            mDataManager.insertTodo(todo);
        }
        getMvpView().cancel();
    }

    @Override
    public void updateDateButton(Date date) {
        getMvpView().setTodoDateButton(date);
    }

}
