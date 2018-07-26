package com.example.blink22.todo.data;

import com.example.blink22.todo.data.model.Todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TodoDb implements TodoDbIF{

    private static List<Todo> mTodos;

    public TodoDb(){
        Calendar today = Calendar.getInstance();
        Date date = today.getTime();
        if(mTodos == null){
            mTodos = new ArrayList<Todo>();
            mTodos.add(new Todo("Todo 1", date, false));
            mTodos.add(new Todo("Todo 2", date, false));
            mTodos.add(new Todo("Todo 3", date, false));
        }
    }

    @Override
    public List<Todo> getAllTodos() {
        return mTodos;
    }

    @Override
    public Todo getTodo(String id) {
        for(Todo todo:mTodos){
            if(id.equals(todo.getId())){
                return todo;
            }
        }
        return null;
    }

    @Override
    public void setTodoTitle(String id, String title) {
        int index=0;
        for(Todo todo:mTodos){
            if(id.equals(todo.getId())){
                todo.setTitle(title);
                mTodos.set(index,todo);
                return;
            }
            index++;
        }
    }

    @Override
    public void setTodoDescription(String id, String description) {
        int index=0;
        for(Todo todo:mTodos){
            if(id.equals(todo.getId())){
                todo.setDescription(description);
                mTodos.set(index,todo);
                return;
            }
            index++;
        }
    }

    @Override
    public void setTodoDone(String id, boolean done) {
        int index=0;
        for(Todo todo:mTodos){
            if(id.equals(todo.getId())){
                todo.setDone(done);
                mTodos.set(index,todo);
                return;
            }
            index++;
        }
    }

    @Override
    public Date getTodoDate(String id) {
        for(Todo todo:mTodos){
            if(id.equals(todo.getId())){
                return todo.getDate();
            }
        }
        return null;
    }

    @Override
    public void setTodoDate(String id, Date date) {
        int index=0;
        for(Todo todo:mTodos){
            if(id.equals(todo.getId())){
                todo.setDate(date);
                mTodos.set(index,todo);
                return;
            }
            index++;
        }
    }

    @Override
    public int getTodosCount() {
        return mTodos.size();
    }
}
