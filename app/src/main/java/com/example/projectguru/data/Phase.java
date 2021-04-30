package com.example.projectguru.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "phase_table",
        foreignKeys = @ForeignKey(
                entity = Project.class,
                parentColumns = "project_id",
                childColumns = "project_id_fk",
                onDelete = CASCADE
        ),
        indices = {@Index(value = "phase_name", unique = true), @Index(value = "project_id_fk")}
)

public class Phase {
    @PrimaryKey(autoGenerate = true)
    private int phase_id;
    @ColumnInfo(name = "project_id_fk")
    private int project_id_fk;
    @ColumnInfo(name = "phase_name")
    private String phase_name;
    @ColumnInfo(name = "phase_start")
    private Date phase_start;
    @ColumnInfo(name = "phase_end")
    private Date phase_end;
    @ColumnInfo(name = "phase_status")
    private String phase_status;
    @ColumnInfo(name = "phase_notes")
    private String phase_notes;


    //Phase field getters and setters

    public int getPhase_id() { return phase_id;}

    public void setPhase_id(int phase_id)  {this.phase_id = phase_id;}

    public int getProject_id_fk() { return project_id_fk;}

    public void setProject_id_fk(int project_id_fk)  {this.project_id_fk = project_id_fk;}

    public String getPhase_name() { return phase_name;}

    public void setPhase_name(String phase_name)  {this.phase_name = phase_name;}

    public Date getPhase_start() { return phase_start;}

    public void setPhase_start(Date phase_start)  {this.phase_start = phase_start;}

    public Date getPhase_end() { return phase_end;}

    public void setPhase_end(Date phase_end)  {this.phase_end = phase_end;}

    public String getPhase_status()  {return phase_status;}

    public void setPhase_status(String phase_status)  {this.phase_status = phase_status;}

    public String getPhase_notes()  {return phase_notes;}

    public void setPhase_notes(String phase_notes)  {this.phase_notes = phase_notes;}


}
