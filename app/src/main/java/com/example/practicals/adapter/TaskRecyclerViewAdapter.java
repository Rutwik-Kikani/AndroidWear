package com.example.practicals.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicals.activity.DeletetaskActivity;
import com.example.practicals.databinding.TaskRowBinding;
import com.example.practicals.model.Task;

import java.util.List;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    TaskRowBinding rowBinding;
    private List<Task> taskList;
    Context context;

    public TaskRecyclerViewAdapter(List<Task> taskList, Context objContext) {
        super();
        this.taskList = taskList;
        this.context = objContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        rowBinding=TaskRowBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(rowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(taskList.get(position).getTaskDetails(), position);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TaskRowBinding recyclerRowBinding;
        public ViewHolder(TaskRowBinding taskRowBinding) {
            super(taskRowBinding.getRoot());
            this.recyclerRowBinding = taskRowBinding;
        }

        public void bindView(final String taskDetails, final int position) {
            recyclerRowBinding.txtTask.setText(taskDetails);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DeletetaskActivity.class);
                    intent.putExtra( "TASK_ID", taskList.get(position).getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
