package com.example.projectguru.data;


//Defining the workunit table

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

//Defining the workunit table

@Entity(
        tableName = "workunit_table",
        foreignKeys = @ForeignKey(
                entity = Phase.class,
                parentColumns = "phase_id",
                childColumns = "phase_id_fk",
                onDelete = CASCADE
        ),
        indices = {@Index(value = "workunit_title", unique = true), @Index(value = "phase_id_fk")}
)

public class WorkUnit {
    @PrimaryKey(autoGenerate = true)
    private int workUnit_id;
    @ColumnInfo(name = "phase_id_fk")
    private int phase_id_fk;
    @ColumnInfo(name = "workunit_title")
    private String workUnit_title;
    @ColumnInfo(name = "workunit_type")
    private String workUnit_type;
    @ColumnInfo(name = "workunit_due")
    private Date workUnit_due;
    @ColumnInfo(name = "workunit_goal")
    private Date workUnit_goal;
    @ColumnInfo(name = "workunit_status")
    private String workUnit_status;
    @ColumnInfo(name = "workunit_notes")
    private String workUnit_notes;

    //WorkUnit field getters and setters

    public int getWorkUnit_id() { return workUnit_id;}

    public void setWorkUnit_id(int workUnit_id)  {this.workUnit_id = workUnit_id;}

    public int getPhase_id_fk() { return phase_id_fk;}

    public void setPhase_id_fk(int phase_id_fk)  {this.phase_id_fk = phase_id_fk;}

    public String getWorkUnit_title() { return workUnit_title;}

    public void setWorkUnit_title(String workUnit_title)  {this.workUnit_title = workUnit_title;}

    public String getWorkUnit_type() { return workUnit_type;}

    public void setWorkUnit_type(String workUnit_type)  {this.workUnit_type = workUnit_type;}

    public Date getWorkUnit_due() { return workUnit_due;}

    public void setWorkUnit_due(Date workUnit_due)  {this.workUnit_due = workUnit_due;}

    public Date getWorkUnit_goal() { return workUnit_goal;}

    public void setWorkUnit_goal(Date workUnit_goal)  {this.workUnit_goal = workUnit_goal;}

    public String getWorkUnit_status() { return workUnit_status; }

    public void setWorkUnit_status(String workUnit_status) {this.workUnit_status = workUnit_status;}

    public String getWorkUnit_notes() { return workUnit_notes; }

    public void setWorkUnit_notes(String workUnit_notes) {this.workUnit_status = workUnit_notes;}
}
