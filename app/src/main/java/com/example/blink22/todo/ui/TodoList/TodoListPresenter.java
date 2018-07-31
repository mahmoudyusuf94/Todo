package com.example.blink22.todo.ui.TodoList;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;

import com.example.blink22.todo.data.DataManager;
import com.example.blink22.todo.data.TodoDataManager;
import com.example.blink22.todo.data.model.Todo;
import com.example.blink22.todo.data.model.TodoDbHelper;
import com.example.blink22.todo.ui.TodoDetails.TodoActivity;
import com.example.blink22.todo.ui.TodoDetails.TodoAdapter;
import com.example.blink22.todo.ui.base.BasePresenter;
import com.example.blink22.todo.ui.base.MvpView;

import java.util.Date;
import java.util.List;

public class TodoListPresenter<V extends ListView>  extends BasePresenter<V>
        implements ListPresenter<V>{

    DataManager mDataManager;
    List<Todo> mTodos;

    public TodoListPresenter(Context context){
        mDataManager = new TodoDataManager(new TodoDbHelper(context));
        mTodos = mDataManager.getAllTodos();
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
    public void doneTodo(String todoId, TodoAdapter.TodoHolder todoHolder) {
        Todo todo = mDataManager.getTodo(todoId);
        todo.setDone(true);
        mDataManager.updateTodo(todo);
        todoHolder.markDone();
    }

    @Override
    public void undoneTodo(String todoId, TodoAdapter.TodoHolder todoHolder) {
        Todo todo = mDataManager.getTodo(todoId);
        todo.setDone(false);
        mDataManager.updateTodo(todo);
        todoHolder.markUndone();
    }

    public void notifyDataChanged() {
        mTodos = mDataManager.getAllTodos();
        getMvpView().updateAdapter();
    }

    private CharSequence formatDate(Date date){
        return DateFormat.format("EEEE, MMMM dd, yyyy",date);
    }

}
