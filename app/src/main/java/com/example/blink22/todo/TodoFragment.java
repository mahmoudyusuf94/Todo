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

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoFragment extends Fragment {


    DetailsPresenter mPresenter;

    @BindView(R.id.todo_title_edit_text)
    EditText mTitleEditText;

    @BindView(R.id.todo_desc_edit_text)
    EditText mDescEditText;

    @BindView(R.id.todo_date_button)
    Button mDateButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_todo, container, false);

        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
}
