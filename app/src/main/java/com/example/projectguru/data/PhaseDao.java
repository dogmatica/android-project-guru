package com.example.projectguru.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PhaseDao {

    @Query("SELECT * FROM phase_table WHERE project_id_fk = :projectId ORDER BY phase_id")
    List<Phase> getPhaseList(int projectId);

    @Query("SELECT * FROM phase_table WHERE project_id_fk = :projectId AND phase_id = :phaseId")
    Phase getPhase(int projectId, int phaseId);

    @Query("SELECT * FROM phase_table WHERE project_id_fk = :projectId AND phase_name = :phaseName")
    Phase getPhaseByName(int projectId, String phaseName);

    @Query("SELECT * FROM phase_table WHERE phase_id = :phaseId")
    Phase getPhaseById(int phaseId);

    @Query("INSERT INTO phase_table (project_id_fk, phase_name) VALUES (:projectId, \"Phase Name\"); ")
    void addPhase(int projectId);

    @Query("SELECT * FROM phase_table")
    List<Phase> getAllPhases();

    @Insert
    void insertPhase(Phase phase);

    @Update
    void updatePhase(Phase phase);

    @Query("DELETE FROM phase_table")
    public void nukePhaseTable();

    @Query("DELETE FROM phase_table WHERE phase_id = :phaseId")
    void deletePhase(int phaseId);


}
