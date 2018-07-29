package com.example.blink22.todo;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;

import com.example.blink22.todo.data.TodoDataManager;
import com.example.blink22.todo.data.TodoDb;
import com.example.blink22.todo.data.model.Todo;

import java.util.Date;
import java.util.List;

public class TodoListPresenter implements ListPresenter{

    ListView mView;
    TodoDb mDb;
    List<Todo> mTodos;

    public TodoListPresenter(ListView view, Context context){
        mView = view;
        mDb = TodoDataManager.getInstance(context);
        mTodos = mDb.getAllTodos();
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
        mDb.deleteTodo(todo.getId());
        notifyDataChanged();
    }

    @Override
    public void doneTodo(String todoId, TodoAdapter.TodoHolder todoHolder) {
        Todo todo = mDb.getTodo(todoId);
        todo.setDone(true);
        mDb.updateTodo(todo);
        todoHolder.markDone();
    }

    @Override
    public void undoneTodo(String todoId, TodoAdapter.TodoHolder todoHolder) {
        Todo todo = mDb.getTodo(todoId);
        todo.setDone(false);
        mDb.updateTodo(todo);
        todoHolder.markUndone();
    }

    public void notifyDataChanged() {
        mTodos = mDb.getAllTodos();
        mView.updateAdapter();
    }

    private CharSequence formatDate(Date date){
        return DateFormat.format("EEEE, MMMM dd, yyyy",date);
    }
}
