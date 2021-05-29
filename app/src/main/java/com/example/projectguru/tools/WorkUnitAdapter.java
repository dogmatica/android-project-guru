package com.example.projectguru.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.WorkUnit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WorkUnitAdapter extends RecyclerView.Adapter<WorkUnitAdapter.ViewHolder> {

    Context context;
    List<WorkUnit> workUnitListView;
    RecyclerView rvWorkUnits;
    SimpleDateFormat formatter;
    MainDatabase db;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView rowName;
        TextView rowStatus;
        TextView rowProject;
        TextView rowPhase;
        TextView rowStart;
        TextView rowEnd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.textViewWorkUnitName);
            rowStatus = itemView.findViewById(R.id.textViewWorkUnitStatus);
            rowProject = itemView.findViewById(R.id.textViewWorkUnitProject);
            rowPhase = itemView.findViewById(R.id.textViewWorkUnitPhase);
            rowStart = itemView.findViewById(R.id.textViewWorkUnitStart);
            rowEnd = itemView.findViewById(R.id.textViewWorkUnitEnd);
        }
    }

    public WorkUnitAdapter(Context context, List<WorkUnit> workUnitListView, RecyclerView rvWorkUnits) {
        this.context = context;
        this.workUnitListView = workUnitListView;
        this.rvWorkUnits = rvWorkUnits;
    }

    @NonNull
    @Override
    public WorkUnitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.workunit_report_layout, parent, false);
        WorkUnitAdapter.ViewHolder viewHolder = new WorkUnitAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkUnitAdapter.ViewHolder viewHolder, int position) {
        formatter = new SimpleDateFormat("MM/dd/yyyy");
        db = MainDatabase.getInstance(context.getApplicationContext());
        if (workUnitListView != null && workUnitListView.size() > 0) {
            WorkUnit workUnit = workUnitListView.get(position);
            int phaseId = workUnit.getPhase_id_fk();
            int projectId = db.phaseDao().getPhaseById(phaseId).getProject_id_fk();
            Date startDate = workUnit.getWorkUnit_start();
            Date endDate = workUnit.getWorkUnit_end();
            String tempStart = formatter.format(startDate);
            String tempEnd = formatter.format(endDate);
            viewHolder.rowName.setText(workUnit.getWorkUnit_title());
            viewHolder.rowStatus.setText(workUnit.getWorkUnit_status());
            viewHolder.rowProject.setText(db.projectDao().getProject(projectId).getProject_name());
            viewHolder.rowPhase.setText(db.phaseDao().getPhase(projectId, phaseId).getPhase_name());
            viewHolder.rowStart.setText(tempStart);
            viewHolder.rowEnd.setText(tempEnd);
        } else {
            return;
        }

    }

    @Override
    public int getItemCount() {
        return workUnitListView.size();
    }
}
