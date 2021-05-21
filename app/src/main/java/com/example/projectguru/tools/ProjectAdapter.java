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

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    Context context;
    List<Project> projectListView;

    public ProjectAdapter(Context context, List<Project> projectListView) {
        this.context = context;
        this.projectListView = projectListView;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.project_report_layout, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        if (projectListView != null && projectListView.size() > 0) {
            Project project = projectListView.get(position);
            holder.project_name_tv.setText(project.getProject_name());
            holder.project_start_tv.setText((CharSequence) project.getProject_start());
            holder.project_end_tv.setText((CharSequence) project.getProject_end());
        } else {
            return;
        }

    }

    @Override
    public int getItemCount() {
        return projectListView.size();
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView project_name_tv, project_start_tv, project_end_tv;
        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            project_name_tv = itemView.findViewById(R.id.project_name_tv);
            project_start_tv = itemView.findViewById(R.id.project_start_tv);
            project_end_tv = itemView.findViewById(R.id.project_end_tv);
        }
    }
}
