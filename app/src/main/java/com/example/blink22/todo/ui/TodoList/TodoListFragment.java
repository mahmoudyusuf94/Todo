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
import android.widget.LinearLayout;

import com.example.blink22.todo.R;
import com.example.blink22.todo.data.TodoDataManager;
import com.example.blink22.todo.data.model.TodoDbHelper;
import com.example.blink22.todo.di.component.ActivityComponent;
import com.example.blink22.todo.di.component.ApplicationComponent;
import com.example.blink22.todo.di.module.ContextModule;
import com.example.blink22.todo.ui.TodoDetails.TodoActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TodoListFragment extends Fragment implements ListView{

    @Inject
    ListPresenter<ListView> mTodoListPresenter;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

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

        ButterKnife.bind(this, v);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
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

        ((TodoListActivity)getActivity()).getActivityComponent().inject(this);
        mTodoListPresenter.onAttach(this);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTodoListPresenter.onDetach();
    }
}
