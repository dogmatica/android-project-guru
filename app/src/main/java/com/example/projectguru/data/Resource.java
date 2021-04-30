package com.example.projectguru.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "resource_table",
        foreignKeys = @ForeignKey(
                entity = WorkUnit.class,
                parentColumns = "workunit_id",
                childColumns = "workunit_id_fk",
                onDelete = CASCADE
        ),
        indices = {@Index(value = "workunit_id_fk")}
)

public class Resource {
    @PrimaryKey(autoGenerate = true)
    private int resource_id;
    @ColumnInfo(name = "workunit_id_fk")
    private int workUnit_id_fk;
    @ColumnInfo(name = "resource_name")
    private String resource_name;
    @ColumnInfo(name = "resource_phone")
    private String resource_phone;
    @ColumnInfo(name = "resource_email")
    private String resource_email;

    //Resource field getters and setters

    public int getResource_id() { return resource_id;}

    public void setResource_id(int resource_id)  {this.resource_id = resource_id;}

    public int getWorkUnit_id_fk() { return workUnit_id_fk;}

    public void setWorkUnit_id_fk(int workUnit_id_fk)  {this.workUnit_id_fk = workUnit_id_fk;}

    public String getResource_name() { return resource_name;}

    public void setResource_name(String resource_name)  {this.resource_name = resource_name;}

    public String getResource_phone() { return resource_phone;}

    public void setResource_phone(String resource_phone)  {this.resource_phone = resource_phone;}

    public String getResource_email() { return resource_email;}

    public void setResource_email(String resource_email)  {this.resource_email = resource_email;}

}
