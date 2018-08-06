package com.example.blink22.todo.ui.TodoList;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;

import com.example.blink22.todo.data.DataManager;
import com.example.blink22.todo.data.db.OnTaskComplete;
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
    List<Todo> mTodos = new ArrayList<>();

    @Inject
    public TodoListPresenter(DataManager dataManager){
        mDataManager = dataManager;

        mDataManager.getAllTodos(new OnTaskComplete() {
            @Override
            public void onSuccess(List<Todo> todoList) {
                if(todoList != null){
                    Log.d("fuck", "SUCCESSSSSS TO GET ALL TODOS"+ "------ todos size = "+ todoList.size());
                }
                else{
                    Log.d("fuck", "SUCCESSSSSS TO GET ALL TODOS"+ "------  but null mother fucker");
                }
                mTodos = todoList;
                notifyDataChanged();
            }

            @Override
            public void onFail() {
                mTodos = new ArrayList<>();
            }
        });
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
//        getMvpView().showWait();
        mDataManager.deleteTodo(todo.getId(), new OnTaskComplete() {
            @Override
            public void onSuccess(List<Todo> todoList) {
//                getMvpView().hideWait();
//                notifyDataChanged();
            }

            @Override
            public void onFail() {

            }
        });
        notifyDataChanged();
    }

    @Override
    public void doneTodo(String todoId, final TodoAdapter.TodoHolder todoHolder) {

//        getMvpView().showWait();
        mDataManager.getTodo(todoId, new OnTaskComplete() {

            @Override
            public void onSuccess(List<Todo> todoList) {
                Todo todo = todoList.get(0);
                todo.setDone(true);
                mDataManager.updateTodo(todo, new OnTaskComplete() {
                    @Override
                    public void onSuccess(List<Todo> todoList) {
//                        getMvpView().hideWait();
//                        todoHolder.markDone();
                    }

                    @Override
                    public void onFail() {

                    }
                });


            }

            @Override
            public void onFail() {

            }

        });
        todoHolder.markDone();

    }

    @Override
    public void undoneTodo(String todoId, final TodoAdapter.TodoHolder todoHolder) {
//        getMvpView().showWait();
        mDataManager.getTodo(todoId, new OnTaskComplete() {
            @Override
            public void onSuccess(List<Todo> todoList) {
                Todo todo = todoList.get(0);
                todo.setDone(false);
                mDataManager.updateTodo(todo, new OnTaskComplete() {
                    @Override
                    public void onSuccess(List<Todo> todoList) {
//                        getMvpView().hideWait();
//                        todoHolder.markUndone();

                    }

                    @Override
                    public void onFail() {

                    }
                });


            }

            @Override
            public void onFail() {

            }
        });
        todoHolder.markUndone();

    }

    public void notifyDataChanged() {
        getMvpView().showWait();
        mDataManager.getAllTodos(new OnTaskComplete() {
            @Override
            public void onSuccess(List<Todo> todoList) {
                mTodos = todoList;
                getMvpView().updateAdapter();
                getMvpView().hideWait();
            }

            @Override
            public void onFail() {

            }
        });

    }

    private CharSequence formatDate(Date date){
        return DateFormat.format("EEEE, MMMM dd, yyyy",date);
    }

}
