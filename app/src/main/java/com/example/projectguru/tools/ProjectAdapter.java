package com.example.projectguru.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectguru.R;
import com.example.projectguru.data.Project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    Context context;
    List<Project> projectListView;
    RecyclerView rvProjects;
    SimpleDateFormat formatter;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView rowName;
        TextView rowStatus;
        TextView rowStart;
        TextView rowEnd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.textViewProjectName);
            rowStatus = itemView.findViewById(R.id.textViewProjectStatus);
            rowStart = itemView.findViewById(R.id.textViewProjectStart);
            rowEnd = itemView.findViewById(R.id.textViewProjectEnd);
        }
    }

    public ProjectAdapter(Context context, List<Project> projectListView, RecyclerView rvProjects) {
        this.context = context;
        this.projectListView = projectListView;
        this.rvProjects = rvProjects;
    }

    @NonNull
    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.project_report_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.ViewHolder viewHolder, int position) {
        formatter = new SimpleDateFormat("MM/dd/yyyy");
        if (projectListView != null && projectListView.size() > 0) {
            Project project = projectListView.get(position);
            Date startDate = project.getProject_start();
            Date endDate = project.getProject_end();
            String tempStart = formatter.format(startDate);
            String tempEnd = formatter.format(endDate);
            viewHolder.rowName.setText(project.getProject_name());
            viewHolder.rowStatus.setText(project.getProject_status());
            viewHolder.rowStart.setText(tempStart);
            viewHolder.rowEnd.setText(tempEnd);
            } else {
            return;
        }

    }

    @Override
    public int getItemCount() {
        return projectListView.size();
    }
/*
    public class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView project_name_tv, project_status_tv, project_start_tv, project_end_tv;
        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            project_name_tv = itemView.findViewById(R.id.project_name_tv);
            project_status_tv = itemView.findViewById(R.id.project_status_tv);
            project_start_tv = itemView.findViewById(R.id.project_start_tv);
            project_end_tv = itemView.findViewById(R.id.project_end_tv);
        }
    }*/
}
