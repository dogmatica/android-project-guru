package com.example.projectguru.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ResourceDao {

    @Query("SELECT * FROM resource_table ORDER BY resource_id")
    List<Resource> getResourceList();

    @Query("SELECT * FROM resource_table WHERE resource_id = :resourceId")
    Resource getResource(int resourceId);

    @Query("SELECT * FROM resource_table WHERE workunit_id = :workunitId")
    List<Resource> getAllWorkUnitResources(int workunitId);

    @Insert
    void insertResource(Resource resource);

    @Insert
    void insertAll(Resource... resource);

    @Update
    void updateResource(Resource resource);

    @Query("DELETE FROM resource_table")
    public void nukeResourceTable();

}
