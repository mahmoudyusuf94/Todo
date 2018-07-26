package com.example.blink22.todo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoFragment extends Fragment implements DetailsView {

    private static final String ARG_TODO_ID = "todo_id";

    private String mTodoId;
    private DetailsPresenter mPresenter;

    @BindView(R.id.todo_title_edit_text)
    EditText mTitleEditText;

    @BindView(R.id.todo_desc_edit_text)
    EditText mDescEditText;

    @BindView(R.id.todo_date_button)
    Button mDateButton;

    @BindView(R.id.todo_cancel_button)
    Button mCancelButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_todo, container, false);
        Bundle args = getArguments();

        ButterKnife.bind(this, v);
        mPresenter = new TodoPresenter(this);

        if(args != null){
            mTodoId = getArguments().getString(ARG_TODO_ID);
            mPresenter.prepareTodoView(mTodoId);
        }

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void setTodoTitleEditText(String title) {
        mTitleEditText.setText(title);
    }

    @Override
    public void setTodoDescEditText(String desc) {
        if(desc!=null){
            mDescEditText.setText(desc);
        }
    }

    @Override
    public void setTodoDateButton(Date date) {
        mDateButton.setText(date.toString());
    }

    public static TodoFragment newInstance(String todoId){
        TodoFragment fragment = new TodoFragment();
        if(todoId!=null){
            Bundle args = new Bundle();
            args.putString(ARG_TODO_ID, todoId);
            fragment.setArguments(args);
        }
        return fragment;
    }

}
