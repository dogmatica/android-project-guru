package com.example.projectguru.data;

import android.content.Context;
import android.util.Log;

public class NukeDatabase {

    public static String LOG_TAG = "NukeData";
    MainDatabase db;

    public void nuke(Context context) {
        db = MainDatabase.getInstance(context);
        try {
            deleteResources();
            deleteWorkUnits();
            deletePhases();
            deleteProjects();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "Nuke DB Failed");
        }
    }

    private void deleteResources() {
        db.resourceDao().nukeResourceTable();
    }

    private void deleteWorkUnits() {
        db.workUnitDao().nukeWorkUnitTable();
    }

    private void deletePhases() {
        db.phaseDao().nukePhaseTable();
    }

    private void deleteProjects() {
        db.projectDao().nukeProjectTable();
    }
}
