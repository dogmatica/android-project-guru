package com.example.projectguru.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "project_table",
        indices = {@Index(value = "project_name", unique = true)}
)
public class Project {
    @PrimaryKey(autoGenerate = true)
    private int project_id;
    @ColumnInfo(name = "project_name")
    private String project_name;
    @ColumnInfo(name = "project_start")
    private Date project_start;
    @ColumnInfo(name = "project_end")
    private Date project_end;

    //Term field getters and setters

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Date getProject_start() {
        return project_start;
    }

    public void setProject_start(Date project_start) {
        this.project_start = project_start;
    }

    public Date getProject_end() {
        return project_end;
    }

    public void setProject_end(Date project_end) {
        this.project_end = project_end;
    }
}
