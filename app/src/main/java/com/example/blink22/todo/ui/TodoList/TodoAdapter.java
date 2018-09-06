package com.example.blink22.todo.ui.TodoList;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.blink22.todo.R;
import com.example.blink22.todo.data.model.Todo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoHolder>{

    private static final float TRANSPARENT = 0.5f;
    private static final float SOLID = 1;

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

    public class TodoHolder extends RecyclerView.ViewHolder implements Holder, View.OnClickListener{

        Todo mTodo;

        @BindView(R.id.list_item_todo)
        LinearLayout mLinearLayout;

        @BindView(R.id.list_item_todo_title_text_view)
        TextView mTitleTextView;

        @BindView(R.id.list_item_todo_done_check_box)
        CheckBox mDoneCheckBox;

        @BindView(R.id.list_item_todo_date_text_view)
        TextView mDateTextView;

        @BindView(R.id.list_item_todo_delete_icon)
        ImageView mDeleteImageView;

        @OnClick(R.id.list_item_todo_delete_icon)
        void deleteClicked(){
            mPresenter.deleteTodo(mTodo);
        }

        @OnClick(R.id.list_item_todo_done_check_box)
        void doneClicked(){
            if (mDoneCheckBox.isChecked()){
                mPresenter.doneTodo(mTodo.getId(), this);
            }else{
                mPresenter.undoneTodo(mTodo.getId(), this);
            }
        }

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
        public void markDone() {
            mTitleTextView.setPaintFlags(mTitleTextView.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            mDoneCheckBox.setChecked(true);
            mLinearLayout.setAlpha(TRANSPARENT);
        }

        @Override
        public void markUndone() {
            mTitleTextView.setPaintFlags(mTitleTextView.getPaintFlags() &  (~Paint.STRIKE_THRU_TEXT_FLAG));
            mDoneCheckBox.setChecked(false);
            mLinearLayout.setAlpha(SOLID);
        }

        @Override
        public void onClick(View view) {
            mPresenter.openTodoDetails(view.getContext(), mTodo.getId());
        }
    }

}
