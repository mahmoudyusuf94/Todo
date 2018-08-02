package com.example.blink22.todo.ui.TodoList;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;

import com.example.blink22.todo.data.DataManager;
import com.example.blink22.todo.data.db.OnGetComplete;
import com.example.blink22.todo.data.model.Todo;
import com.example.blink22.todo.ui.TodoDetails.TodoActivity;
import com.example.blink22.todo.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class TodoListPresenter<V extends ListView>  extends BasePresenter<V>
        implements ListPresenter<V>{

    DataManager mDataManager;
    List<Todo> mTodos;

    @Inject
    public TodoListPresenter(DataManager dataManager){
        mDataManager = dataManager;

        mDataManager.getAllTodos(new OnGetComplete() {
            @Override
            public void onSuccess(List<Todo> todoList) {
                Log.d("fuck", "SUCCESSSSSS TO GET ALL TODOS");
                mTodos = todoList;
            }

            @Override
            public void onFail() {
                mTodos = new ArrayList<>();
            }
        });
        notifyDataChanged();
    }

    public void bindViewHolderWithPosition(TodoAdapter.TodoHolder todoHolder, int position) {
        Todo todo = mTodos.get(position);

        todoHolder.setTitle(todo.getTitle());
        todoHolder.setDone(todo.isDone());
        todoHolder.setDate(formatDate(todo.getDate()).toString());
        todoHolder.setTodo(todo);
        if(todo.isDone()){
            todoHolder.markDone();
        }else{
            todoHolder.markUndone();
        }
    }

    public int getTodoCount() {
        return mTodos.size();
    }

    public void showTodoDetails(Context context, String id) {
        Intent intent = TodoActivity.newIntent(context, id);
        context.startActivity(intent);
    }

    @Override
    public void deleteTodo(Todo todo) {
        mDataManager.deleteTodo(todo.getId());
        notifyDataChanged();
    }

    @Override
    public void doneTodo(String todoId, final TodoAdapter.TodoHolder todoHolder) {

        mDataManager.getTodo(todoId, new OnGetComplete() {

            @Override
            public void onSuccess(List<Todo> todoList) {
                Todo todo = todoList.get(0);
                todo.setDone(true);
                mDataManager.updateTodo(todo);
                todoHolder.markDone();
            }

            @Override
            public void onFail() {

            }

        });
    }

    @Override
    public void undoneTodo(String todoId, final TodoAdapter.TodoHolder todoHolder) {

        mDataManager.getTodo(todoId, new OnGetComplete() {
            @Override
            public void onSuccess(List<Todo> todoList) {
                Todo todo = todoList.get(0);
                todo.setDone(false);
                mDataManager.updateTodo(todo);
                todoHolder.markUndone();

            }

            @Override
            public void onFail() {

            }
        });

    }

    public void notifyDataChanged() {
        mDataManager.getAllTodos(new OnGetComplete() {
            @Override
            public void onSuccess(List<Todo> todoList) {
                mTodos = todoList;
            }

            @Override
            public void onFail() {

            }
        });
        getMvpView().updateAdapter();
    }

    private CharSequence formatDate(Date date){
        return DateFormat.format("EEEE, MMMM dd, yyyy",date);
    }

}
