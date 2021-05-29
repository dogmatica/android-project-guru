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
import com.example.projectguru.data.Phase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PhaseAdapter extends RecyclerView.Adapter<PhaseAdapter.ViewHolder> {

    Context context;
    List<Phase> phaseListView;
    RecyclerView rvPhases;
    SimpleDateFormat formatter;
    MainDatabase db;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView rowName;
        TextView rowStatus;
        TextView rowProject;
        TextView rowStart;
        TextView rowEnd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.textViewPhaseName);
            rowStatus = itemView.findViewById(R.id.textViewPhaseStatus);
            rowProject = itemView.findViewById(R.id.textViewPhaseProject);
            rowStart = itemView.findViewById(R.id.textViewPhaseStart);
            rowEnd = itemView.findViewById(R.id.textViewPhaseEnd);
        }
    }

    public PhaseAdapter(Context context, List<Phase> phaseListView, RecyclerView rvPhases) {
        this.context = context;
        this.phaseListView = phaseListView;
        this.rvPhases = rvPhases;
    }

    @NonNull
    @Override
    public PhaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.phase_report_layout, parent, false);
        PhaseAdapter.ViewHolder viewHolder = new PhaseAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhaseAdapter.ViewHolder viewHolder, int position) {
        formatter = new SimpleDateFormat("MM/dd/yyyy");
        db = MainDatabase.getInstance(context.getApplicationContext());
        if (phaseListView != null && phaseListView.size() > 0) {
            Phase phase = phaseListView.get(position);
            Date startDate = phase.getPhase_start();
            Date endDate = phase.getPhase_end();
            String tempStart = formatter.format(startDate);
            String tempEnd = formatter.format(endDate);
            viewHolder.rowName.setText(phase.getPhase_name());
            viewHolder.rowStatus.setText(phase.getPhase_status());
            viewHolder.rowProject.setText(db.projectDao().getProject(phase.getProject_id_fk()).getProject_name());
            viewHolder.rowStart.setText(tempStart);
            viewHolder.rowEnd.setText(tempEnd);
        } else {
            return;
        }

    }

    @Override
    public int getItemCount() {
        return phaseListView.size();
    }
}
