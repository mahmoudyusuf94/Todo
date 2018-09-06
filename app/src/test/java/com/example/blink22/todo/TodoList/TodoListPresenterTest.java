package com.example.blink22.todo.TodoList;

import com.example.blink22.todo.data.DataManager;
import com.example.blink22.todo.data.db.OnTaskComplete;
import com.example.blink22.todo.data.model.Todo;
import com.example.blink22.todo.ui.TodoList.ListPresenter;
import com.example.blink22.todo.ui.TodoList.TodoListPresenter;
import com.example.blink22.todo.ui.TodoList.TodoListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class TodoListPresenterTest {

    @Mock
    TodoListView mTodoListView;

    @Mock
    DataManager mDataManager;

    @Captor
    ArgumentCaptor<OnTaskComplete> mOnTaskCompleteCallback;

    ListPresenter<TodoListView> mListPresenter;
    List<Todo> todos = new ArrayList<>();
    private static List<Todo> TODOS;


    @Before
    public void setupTodoListPresenterTest(){
        MockitoAnnotations.initMocks(this);
        todos.add(new Todo());
        todos.add(new Todo());
        TODOS = todos;
        mListPresenter = new TodoListPresenter(mDataManager);
        mListPresenter.onAttach(mTodoListView);
    }

    @Test
    public void deleteTodo(){
        Todo todo = new Todo();
        mListPresenter.deleteTodo(todo);
        Mockito.verify(mDataManager).deleteTodo(todo.getId());
        Mockito.verify(mTodoListView).showWait();
        Mockito.verify(mDataManager).getAllTodos(mOnTaskCompleteCallback.capture());
        mOnTaskCompleteCallback.getValue().onSuccess(TODOS);
        Mockito.verify(mTodoListView).updateAdapter();
        Mockito.verify(mTodoListView).hideWait();
    }
}
