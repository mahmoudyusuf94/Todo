package com.example.blink22.todo.ui.TodoDetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.blink22.todo.R;
import com.example.blink22.todo.data.model.Todo;

import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static com.example.blink22.todo.Util.CommonUtils.formatDate;

public class TodoFragment extends Fragment implements DetailsView {

    private static final String ARG_TODO_ID = "todo_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    public static TodoFragment newInstance(String todoId) {
        TodoFragment fragment = new TodoFragment();
        if (todoId != null) {
            Bundle args = new Bundle();
            args.putString(ARG_TODO_ID, todoId);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Inject
    DetailsPresenter<DetailsView> mPresenter;

    private String mTodoId;
    private Todo mTodo = new Todo();
    private boolean mTodoExists;

    @BindView(R.id.todo_title_edit_text)
    EditText mTitleEditText;

    @OnTextChanged(value = R.id.todo_title_edit_text, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void titleTextChanged(Editable editable) {
        mTodo.setTitle(editable.toString());
    }

    @BindView(R.id.todo_desc_edit_text)
    EditText mDescEditText;

    @OnTextChanged(value = R.id.todo_desc_edit_text, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void descTextChanged(Editable editable) {
        mTodo.setDescription(editable.toString());
    }

    @BindView(R.id.todo_date_button)
    Button mDateButton;

    @BindView(R.id.todo_cancel_button)
    Button mCancelButton;

    @BindView(R.id.todo_fragment_progress_bar)
    ProgressBar mProgressBar;

    @OnClick(R.id.todo_cancel_button)
    public void onCancelClick() {
        mPresenter.cancelTodo();
    }

    @OnClick(R.id.todo_done_button)
    public void onDoneClick() {
        mPresenter.saveTodo(mTodo, mTodoExists);
    }

    @OnClick(R.id.todo_date_button)
    public void onDateClick() {
        FragmentManager manager = getFragmentManager();
        DatePickerFragment dialog = DatePickerFragment.newInstance(mTodo.getDate());
        dialog.setTargetFragment(TodoFragment.this, REQUEST_DATE);
        dialog.show(manager, DIALOG_DATE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_todo, container, false);

        ButterKnife.bind(this, v);
        mPresenter.loadSavedTodo(mTodoId);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        ((TodoActivity) getActivity()).getActivityComponent().inject(this);

        mPresenter.onAttach(this);
        Bundle args = getArguments();

        if (args != null) {
            mTodoId = getArguments().getString(ARG_TODO_ID);
        }
    }

    @Override
    public void showWait() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideWait() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showConnectionErrorToast() {
        Toast.makeText(this.getContext(), R.string.loading_error_text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTodoTitleEditText(String title) {
        mTitleEditText.setText(title);
    }

    @Override
    public void setTodoDescEditText(String desc) {
        if (desc != null) {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mTodo.setDate(date);
            mPresenter.updateDateButton(date);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    public void prepare(Todo todo) {
        mTodo = todo;
        mTodoExists = (mTodo.getTitle() != null);
    }

}
