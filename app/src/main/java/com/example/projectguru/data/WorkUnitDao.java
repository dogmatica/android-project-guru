package com.example.projectguru.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WorkUnitDao {

    @Query("SELECT * FROM workunit_table ORDER BY workunit_id")
    List<WorkUnit> getWorkUnitList();

    @Query("SELECT * FROM workunit_table WHERE phase_id_fk = :phaseId ORDER BY workunit_id")
    List<WorkUnit> getPhaseWorkUnitList(int phaseId);

    @Query("SELECT * FROM workunit_table WHERE phase_id_fk = :phaseId AND workunit_id = :workunitId")
    WorkUnit getWorkUnit(int phaseId, int workunitId);

    @Query("SELECT * FROM workunit_table WHERE workunit_id = :workunitId")
    WorkUnit getWorkUnitById(int workunitId);

    @Query("SELECT * FROM workunit_table WHERE workunit_title = :workunitTitle")
    WorkUnit getWorkUnitByTitle(String workunitTitle);

    @Query("SELECT * FROM workunit_table")
    List<WorkUnit> getAllWorkUnits();

    @Insert
    void insertWorkUnit(WorkUnit workunit);

    @Insert
    void insertAll(WorkUnit... workunit);

    @Update
    void updateWorkUnit(WorkUnit workunit);

    @Query("DELETE FROM workunit_table")
    public void nukeWorkUnitTable();

    @Query("DELETE FROM workunit_table WHERE workunit_id = :workunitId")
    void deleteWorkUnit(int workunitId);


}
