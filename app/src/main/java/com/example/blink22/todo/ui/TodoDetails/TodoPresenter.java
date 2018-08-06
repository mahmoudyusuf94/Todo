package com.example.blink22.todo.ui.TodoDetails;

import android.util.Log;

import com.example.blink22.todo.data.DataManager;
import com.example.blink22.todo.data.db.OnTaskComplete;
import com.example.blink22.todo.data.model.Todo;
import com.example.blink22.todo.ui.base.BasePresenter;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class TodoPresenter<V extends DetailsView> extends BasePresenter<V>
        implements DetailsPresenter<V>{

    DataManager mDataManager;

    @Inject
    public TodoPresenter (DataManager dataManager){
        mDataManager = dataManager;
    }

    @Override
    public void loadSavedTodo(String todoId) {
        if(todoId!=null){
            getMvpView().showWait();
            mDataManager.getTodo(todoId, new OnTaskComplete() {
                @Override
                public void onSuccess(List<Todo> todoList) {
                    Todo todo = todoList.get(0);
                    if(todo != null){
                        getMvpView().setTodoTitleEditText(todo.getTitle());
                        getMvpView().setTodoDescEditText(todo.getDescription());
                        getMvpView().setTodoDateButton(todo.getDate());
                        getMvpView().prepare(todo);
                    }else{
                        getMvpView().prepare(new Todo());
                    }
                    getMvpView().hideWait();
                }

                @Override
                public void onFail() {
                    getMvpView().hideWait();
                    getMvpView().showConnectionErrorToast();
                    getMvpView().cancel();
                }
            });
        }else{
            getMvpView().prepare(new Todo());
        }
    }

    @Override
    public void cancelTodo() {
        getMvpView().cancel();
    }

    @Override
    public void saveTodo(Todo todo, boolean exists) {
        if(exists){
            mDataManager.updateTodo(todo);
            getMvpView().cancel();

        }else{
            mDataManager.insertTodo(todo);
            getMvpView().cancel();

        }
    }

    @Override
    public void updateDateButton(Date date) {
        getMvpView().setTodoDateButton(date);
    }

}
