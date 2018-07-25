package com.example.blink22.todo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoListFragment extends Fragment implements ListView{

    private static TodoListPresenter mTodoListPresenter;

    @BindView(R.id.todo_list_recycler_view)
    RecyclerView mRecyclerView;

    TodoAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_todo_list, container, false);
        if(mTodoListPresenter == null){
            mTodoListPresenter = new TodoListPresenter(this);
        }

        ButterKnife.bind(this, v);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(mAdapter == null){
            mAdapter = new TodoAdapter(mTodoListPresenter);
        }
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

}
