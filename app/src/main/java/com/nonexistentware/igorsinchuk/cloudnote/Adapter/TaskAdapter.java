package com.nonexistentware.igorsinchuk.cloudnote.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nonexistentware.igorsinchuk.cloudnote.Model.TaskModel;
import com.nonexistentware.igorsinchuk.cloudnote.R;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    Context context;
    ArrayList<TaskModel> taskList;

    public TaskAdapter(Context context, ArrayList<TaskModel> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.title.setText(taskList.get(i).getTitle());
        myViewHolder.description.setText(taskList.get(i).getDescription());
        myViewHolder.date.setText(taskList.get(i).getDate());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, description, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.task_title);
            description = (TextView) itemView.findViewById(R.id.task_description);
            date = (TextView) itemView.findViewById(R.id.task_date);
        }
    }
}
