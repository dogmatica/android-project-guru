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

    @Query("SELECT * FROM resource_table WHERE resource_name = :resourceName")
    Resource getResourceByName(String resourceName);

    @Query("SELECT * FROM resource_table WHERE workunit_id = :workunitId")
    List<Resource> getAllWorkUnitResources(int workunitId);

    @Query("SELECT * FROM resource_table WHERE resource_type = :resourceType")
    List<Resource> getAllResourcesOfType(String resourceType);

    @Query("SELECT * FROM resource_table WHERE instr(resource_name, :search)")
    List<Resource> getAllResourcesNamed(String search);

    @Query("DELETE FROM resource_table WHERE resource_id = :resourceId")
    void deleteResource(int resourceId);

    @Insert
    void insertResource(Resource resource);

    @Insert
    void insertAll(Resource... resource);

    @Update
    void updateResource(Resource resource);

    @Query("DELETE FROM resource_table")
    public void nukeResourceTable();

}
