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
    @ColumnInfo(name = "workunit_start")
    private Date workUnit_start;
    @ColumnInfo(name = "workunit_end")
    private Date workUnit_end;
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

    public Date getWorkUnit_start() { return workUnit_start;}

    public void setWorkUnit_start(Date workUnit_start)  {this.workUnit_start = workUnit_start;}

    public Date getWorkUnit_end() { return workUnit_end;}

    public void setWorkUnit_end(Date workUnit_end)  {this.workUnit_end = workUnit_end;}

    public String getWorkUnit_status() { return workUnit_status; }

    public void setWorkUnit_status(String workUnit_status) {this.workUnit_status = workUnit_status;}

    public String getWorkUnit_notes() { return workUnit_notes; }

    public void setWorkUnit_notes(String workUnit_notes) {this.workUnit_notes = workUnit_notes;}
}
