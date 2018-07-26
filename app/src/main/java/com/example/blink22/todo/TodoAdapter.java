package com.example.blink22.todo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.blink22.todo.data.model.Todo;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoHolder>{

    private ListPresenter mPresenter;

    public TodoAdapter(ListPresenter presenter){
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.list_item_todo, viewGroup, false);
        return new TodoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoHolder todoHolder, int position) {
        mPresenter.bindViewHolderWithPosition(todoHolder, position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getTodoCount();
    }

    class TodoHolder extends RecyclerView.ViewHolder implements Holder, View.OnClickListener{

        Todo mTodo;

        @BindView(R.id.list_item_todo_title_text_view)
        TextView mTitleTextView;

        @BindView(R.id.list_item_todo_done_check_box)
        CheckBox mDoneCheckBox;

        @BindView(R.id.list_item_todo_date_text_view)
        TextView mDateTextView;

        public TodoHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setTitle(String title) {
            mTitleTextView.setText(title);
        }

        @Override
        public void setDone(boolean done) {
            mDoneCheckBox.setChecked(done);
        }

        @Override
        public void setDate(String date) {
            mDateTextView.setText(date);
        }


        @Override
        public void setTodo(Todo todo) {
            mTodo = todo;
        }

        @Override
        public void onClick(View view) {
            mPresenter.showTodoDetails(view.getContext(), mTodo.getId());
        }
    }

}
