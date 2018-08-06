package com.example.blink22.todo.ui.TodoList;

import android.content.Context;
import android.content.Intent;

import com.example.blink22.todo.data.DataManager;
import com.example.blink22.todo.data.db.OnTaskComplete;
import com.example.blink22.todo.data.model.Todo;
import com.example.blink22.todo.ui.TodoDetails.TodoActivity;
import com.example.blink22.todo.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.blink22.todo.Util.CommonUtils.formatDate;

public class TodoListPresenter<V extends TodoListView>  extends BasePresenter<V>
        implements ListPresenter<V>{

    private DataManager mDataManager;
    private List<Todo> mTodos = new ArrayList<>();

    @Inject
    public TodoListPresenter(DataManager dataManager){
        mDataManager = dataManager;

        mDataManager.getAllTodos(new OnTaskComplete() {
            @Override
            public void onSuccess(List<Todo> todoList) {
                mTodos = todoList;
                notifyDataChanged();
            }

            @Override
            public void onFail() {
                getMvpView().showConnectionErrorToast();
            }
        });
    }

    @Override
    public void deleteTodo(Todo todo) {
        mDataManager.deleteTodo(todo.getId());
        notifyDataChanged();
    }

    @Override
    public void doneTodo(String todoId, final TodoAdapter.TodoHolder todoHolder) {

        mDataManager.getTodo(todoId, new OnTaskComplete() {
            @Override
            public void onSuccess(List<Todo> todoList) {
                if(!todoList.isEmpty()){
                    Todo todo = todoList.get(0);
                    todo.setDone(true);
                    mDataManager.updateTodo(todo);
                }else{
                    notifyDataChanged();
                }
            }

            @Override
            public void onFail() {
                todoHolder.markUndone();
                getMvpView().showConnectionErrorToast();
            }

        });
        todoHolder.markDone();

    }

    @Override
    public void undoneTodo(String todoId, final TodoAdapter.TodoHolder todoHolder) {
        mDataManager.getTodo(todoId, new OnTaskComplete() {
            @Override
            public void onSuccess(List<Todo> todoList) {
                if(!todoList.isEmpty()){
                    Todo todo = todoList.get(0);
                    todo.setDone(false);
                    mDataManager.updateTodo(todo);
                }else{
                    notifyDataChanged();
                }
            }

            @Override
            public void onFail() {
                todoHolder.markDone();
                getMvpView().showConnectionErrorToast();
            }
        });
        todoHolder.markUndone();

    }

    @Override
    public void fabClicked() {
        getMvpView().openNewTodoActivity();
    }

    public void bindViewHolderWithPosition(Holder todoHolder, int position) {
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

    public void openTodoDetails(Context context, String id) {
        Intent intent = TodoActivity.newIntent(context, id);
        context.startActivity(intent);
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
                getMvpView().showConnectionErrorToast();
            }
        });

    }
}
