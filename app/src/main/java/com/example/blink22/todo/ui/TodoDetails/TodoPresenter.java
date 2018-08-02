package com.example.blink22.todo.ui.TodoDetails;

import android.util.Log;

import com.example.blink22.todo.data.DataManager;
import com.example.blink22.todo.data.db.OnGetComplete;
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
    public void prepareTodoView(String todoId) {
        Log.i("fuck", "Called prepare with todoid = "+ todoId );
        Log.i("fuck", "Testing dataManager =>>>>>" + mDataManager);
        if(todoId!=null){
            mDataManager.getTodo(todoId, new OnGetComplete() {
                @Override
                public void onSuccess(List<Todo> todoList) {
                    Todo todo = todoList.get(0);
                    if(todo != null){
                        Log.i("fuck", "Get Succedded, todo != null " + todo.toString());
                        getMvpView().setTodoTitleEditText(todo.getTitle());
                        getMvpView().setTodoDescEditText(todo.getDescription());
                        getMvpView().setTodoDateButton(todo.getDate());
                        getMvpView().prepare(todo);
                    }else{
                        Log.i("fuck", "Get Succedded, but todo=null");
                        getMvpView().prepare(new Todo());
                    }
                }

                @Override
                public void onFail() {
                    Log.i("fuck", "Get FAILED!!!!!!");

                    getMvpView().prepare(new Todo());
                }
            });
        }else{
            Todo todo = new Todo();
            Log.i("fuck", "Entered the else =====> Sending new todo = "+ todo);
            getMvpView().prepare(new Todo());
        }
    }

    @Override
    public void cancelTodo() {
        getMvpView().cancel();
    }

    @Override
    public void doneTodo(Todo todo, boolean exists) {
        if(exists){
            mDataManager.updateTodo(todo);
        }else{
            Log.i("fuck", "CALLING INSERT TODO FROM PRESENTER => TODO = " +todo);
            mDataManager.insertTodo(todo);
        }
        getMvpView().cancel();
    }

    @Override
    public void updateDateButton(Date date) {
        getMvpView().setTodoDateButton(date);
    }

}
