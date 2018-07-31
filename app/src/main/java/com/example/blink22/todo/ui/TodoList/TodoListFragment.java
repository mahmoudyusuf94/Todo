package com.example.blink22.todo.ui.TodoList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blink22.todo.R;
import com.example.blink22.todo.ui.TodoDetails.TodoActivity;
import com.example.blink22.todo.ui.TodoDetails.TodoAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TodoListFragment extends Fragment implements ListView{

    private static ListPresenter mTodoListPresenter;
    private static ListPresenter mDoneTodoListPresenter;

    @BindView(R.id.todo_list_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.todo_list_fab)
    FloatingActionButton mFab;

    @OnClick(R.id.todo_list_fab)
    void fabClicked(){
        Intent intent = new Intent(getContext(), TodoActivity.class);
        startActivity(intent);
    }

    TodoAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_todo_list, container, false);

        if(mTodoListPresenter == null){
            mTodoListPresenter = new TodoListPresenter(this,getContext());
        }

        ButterKnife.bind(this, v);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(mAdapter == null){
            mAdapter = new TodoAdapter(mTodoListPresenter);
        }
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void updateAdapter() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        if(mAdapter != null){
            mTodoListPresenter.notifyDataChanged();
        }
        super.onResume();
    }
}
