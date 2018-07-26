package com.example.blink22.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.blink22.todo.data.model.Todo;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TodoFragment extends Fragment implements DetailsView {

    private static final String ARG_TODO_ID = "todo_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0 ;


    private String mTodoId;
    private DetailsPresenter mPresenter;
    private Todo mTodo;
    private boolean mTodoExists;

    @BindView(R.id.todo_title_edit_text)
    EditText mTitleEditText;

    @BindView(R.id.todo_desc_edit_text)
    EditText mDescEditText;

    @BindView(R.id.todo_date_button)
    Button mDateButton;

    @BindView(R.id.todo_cancel_button)
    Button mCancelButton;

    @OnClick(R.id.todo_cancel_button)
    public void onCancelClick(){
        mPresenter.cancelTodo();
    }

    @OnClick(R.id.todo_done_button)
    public void onDoneClick(){
        mPresenter.doneTodo(mTodo,mTodoExists);
    }

    @OnClick(R.id.todo_date_button)
    public void onDateClick(){
        FragmentManager manager = getFragmentManager();
        DatePickerFragment dialog = DatePickerFragment.newInstance(mTodo.getDate());
        dialog.setTargetFragment(TodoFragment.this, REQUEST_DATE);
        dialog.show(manager, DIALOG_DATE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_todo, container, false);
        Bundle args = getArguments();

        ButterKnife.bind(this, v);
        mPresenter = new TodoPresenter(this);

        if(args != null){
            mTodoId = getArguments().getString(ARG_TODO_ID);
            mTodo = mPresenter.prepareTodoView(mTodoId);
            mTodoExists = (mTodo.getTitle() != null);
        }

        mTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTodo.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDescEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTodo.setDescription(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


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
        mDateButton.setText(formatDate(date));
    }

    @Override
    public void cancel() {
        getActivity().onBackPressed();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mTodo.setDate(date);
            mPresenter.updateDateButton(date);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private CharSequence formatDate(Date date){
        return DateFormat.format("EEEE, MMMM dd, yyyy",date);
    }
}
