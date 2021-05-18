package com.example.projectguru.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProjectDao {
    @Query("SELECT * FROM project_table ORDER BY project_id")
    List<Project> getProjectList();

    @Query("SELECT * FROM project_table WHERE project_id = :projectId ORDER BY project_id")
    Project getProject(int projectId);

    @Query("SELECT * FROM project_table WHERE project_name = :projectName ORDER BY project_id")
    Project getProjectByName(String projectName);

    @Query("SELECT * FROM project_table")
    List<Project> getAllProjects();

    @Insert
    void insertProject(Project project);

    @Insert
    void insertAll(Project... project);

    @Update
    void updateProject(Project project);

    @Query("DELETE FROM project_table")
    public void nukeProjectTable();

    @Query("DELETE FROM project_table WHERE project_id = :projectId")
    void deleteProject(int projectId);


}
