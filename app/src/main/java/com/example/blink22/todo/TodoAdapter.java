package com.example.blink22.todo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoHolder>{

    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoHolder todoHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TodoHolder extends RecyclerView.ViewHolder{

        public TodoHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
