package com.example.projectguru.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        tableName = "resource_table",
        indices = {@Index(value = "workunit_id")}
)

public class Resource implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int resource_id;
    @ColumnInfo(name = "resource_name")
    private String resource_name;
    @ColumnInfo(name = "resource_type")
    private String resource_type;
    @ColumnInfo(name = "resource_phone")
    private String resource_phone;
    @ColumnInfo(name = "resource_email")
    private String resource_email;
    @ColumnInfo(name = "assigned")
    private int assigned;
    @ColumnInfo(name = "requested")
    private int requested;
    @ColumnInfo(name = "workunit_id")
    private int workUnit_id;


    //Resource field getters and setters

    public int getResource_id() { return resource_id;}

    public void setResource_id(int resource_id)  {this.resource_id = resource_id;}

    public int getWorkUnit_id() { return workUnit_id;}

    public void setWorkUnit_id(int workUnit_id)  {this.workUnit_id = workUnit_id;}

    public String getResource_name() { return resource_name;}

    public void setResource_name(String resource_name)  {this.resource_name = resource_name;}

    public String getResource_type() { return resource_type;}

    public void setResource_type(String resource_type)  {this.resource_type = resource_type;}

    public String getResource_phone() { return resource_phone;}

    public void setResource_phone(String resource_phone)  {this.resource_phone = resource_phone;}

    public String getResource_email() { return resource_email;}

    public void setResource_email(String resource_email)  {this.resource_email = resource_email;}

    public int getAssigned() { return assigned;}

    public void setAssigned(int assigned)  {this.assigned = assigned;}

    public int getRequested() { return requested;}

    public void setRequested(int requested)  {this.requested = requested;}

}
