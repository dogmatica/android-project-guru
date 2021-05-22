package com.example.projectguru.data;

import android.content.Context;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

public class PopulateDatabase {

    public static String LOG_TAG = "PopData";
    MainDatabase db;
    Project tempProject1 = new Project();
    Project tempProject2 = new Project();
    Project tempProject3 = new Project();
    Project tempProject4 = new Project();
    Phase tempPhase1 = new Phase();
    Phase tempPhase2 = new Phase();
    Phase tempPhase3 = new Phase();
    Phase tempPhase4 = new Phase();
    Phase tempPhase5 = new Phase();
    Phase tempPhase6 = new Phase();
    Phase tempPhase7 = new Phase();
    Phase tempPhase8 = new Phase();
    Phase tempPhase9 = new Phase();
    Phase tempPhase10 = new Phase();
    Phase tempPhase11 = new Phase();
    WorkUnit tempWorkUnit1 = new WorkUnit();
    WorkUnit tempWorkUnit2 = new WorkUnit();
    WorkUnit tempWorkUnit3 = new WorkUnit();
    WorkUnit tempWorkUnit4 = new WorkUnit();
    WorkUnit tempWorkUnit5 = new WorkUnit();
    WorkUnit tempWorkUnit6 = new WorkUnit();
    WorkUnit tempWorkUnit7 = new WorkUnit();
    WorkUnit tempWorkUnit8 = new WorkUnit();
    WorkUnit tempWorkUnit9 = new WorkUnit();
    WorkUnit tempWorkUnit10 = new WorkUnit();
    WorkUnit tempWorkUnit11= new WorkUnit();
    WorkUnit tempWorkUnit12 = new WorkUnit();
    WorkUnit tempWorkUnit13 = new WorkUnit();
    WorkUnit tempWorkUnit14 = new WorkUnit();
    Resource tempResource1 = new Resource();
    Resource tempResource2 = new Resource();
    Resource tempResource3 = new Resource();
    Resource tempResource4 = new Resource();
    Resource tempResource5 = new Resource();
    Resource tempResource6 = new Resource();
    Resource tempResource7 = new Resource();
    Resource tempResource8 = new Resource();
    Resource tempResource9 = new Resource();
    Resource tempResource10 = new Resource();
    Resource tempResource11 = new Resource();
    Resource tempResource12 = new Resource();
    Resource tempResource13 = new Resource();
    Resource tempResource14 = new Resource();
    Resource tempResource15 = new Resource();
    Resource tempResource16 = new Resource();
    Resource tempResource17 = new Resource();
    Resource tempResource18 = new Resource();
    Resource tempResource19 = new Resource();
    Resource tempResource20 = new Resource();
    Resource tempResource21 = new Resource();
    Resource tempResource22 = new Resource();
    Resource tempResource23 = new Resource();
    Resource tempResource24 = new Resource();
    Resource tempResource25 = new Resource();
    Resource tempResource26 = new Resource();
    Resource tempResource27 = new Resource();
    Resource tempResource28 = new Resource();
    Resource tempResource29 = new Resource();
    Resource tempResource30 = new Resource();

    public void populate(Context context) {
        db = MainDatabase.getInstance(context);
        try {
            insertProjects();
            insertPhases();
            insertWorkUnits();
            insertResources();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "Populate DB Failed");
        }
    }

    private void insertProjects() {
        Calendar start;
        Calendar end;

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, -6);
        end.add(Calendar.MONTH, -4);
        tempProject1.setProject_name("Security Review");
        tempProject1.setProject_start(start.getTime());
        tempProject1.setProject_end(end.getTime());
        tempProject1.setProject_status("Completed");

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, -1);
        end.add(Calendar.MONTH, 3);
        tempProject2.setProject_name("Mobile App Documentation");
        tempProject2.setProject_start(start.getTime());
        tempProject2.setProject_end(end.getTime());
        tempProject2.setProject_status("In Progress");

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 6);
        end.add(Calendar.MONTH, 8);
        tempProject3.setProject_name("Data Cleaning");
        tempProject3.setProject_start(start.getTime());
        tempProject3.setProject_end(end.getTime());
        tempProject3.setProject_status("Planned");

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 9);
        end.add(Calendar.MONTH, 13);
        tempProject4.setProject_name("Provision Cloud");
        tempProject4.setProject_start(start.getTime());
        tempProject4.setProject_end(end.getTime());
        tempProject4.setProject_status("Planned");

        db.projectDao().insertAll(tempProject1, tempProject2, tempProject3, tempProject4);
    }

    private void insertPhases() {
        Calendar start;
        Calendar end;
        List<Project> projectList = db.projectDao().getProjectList();
        if (projectList == null) return;

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, -6);
        end.add(Calendar.MONTH, -5);
        tempPhase1.setPhase_name("Sec Phase 1");
        tempPhase1.setPhase_start(start.getTime());
        tempPhase1.setPhase_end(end.getTime());
        tempPhase1.setPhase_notes("Notes go here.");
        tempPhase1.setPhase_status("Completed");
        tempPhase1.setProject_id_fk(projectList.get(0).getProject_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, -6);
        end.add(Calendar.MONTH, -5);
        tempPhase2.setPhase_name("Sec Phase 2");
        tempPhase2.setPhase_start(start.getTime());
        tempPhase2.setPhase_end(end.getTime());
        tempPhase2.setPhase_notes("Notes go here.");
        tempPhase2.setPhase_status("Completed");
        tempPhase2.setProject_id_fk(projectList.get(0).getProject_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, -5);
        end.add(Calendar.MONTH, -4);
        tempPhase3.setPhase_name("Sec Phase 3");
        tempPhase3.setPhase_start(start.getTime());
        tempPhase3.setPhase_end(end.getTime());
        tempPhase3.setPhase_notes("Notes go here.");
        tempPhase3.setPhase_status("Completed");
        tempPhase3.setProject_id_fk(projectList.get(0).getProject_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, -1);
        end.add(Calendar.MONTH, 0);
        tempPhase4.setPhase_name("Doc Phase 1");
        tempPhase4.setPhase_start(start.getTime());
        tempPhase4.setPhase_end(end.getTime());
        tempPhase4.setPhase_notes("Notes go here.");
        tempPhase4.setPhase_status("Completed");
        tempPhase4.setProject_id_fk(projectList.get(1).getProject_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 0);
        end.add(Calendar.MONTH, 2);
        tempPhase5.setPhase_name("Doc Phase 2");
        tempPhase5.setPhase_start(start.getTime());
        tempPhase5.setPhase_end(end.getTime());
        tempPhase5.setPhase_notes("Notes go here.");
        tempPhase5.setPhase_status("In Progress");
        tempPhase5.setProject_id_fk(projectList.get(1).getProject_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 2);
        end.add(Calendar.MONTH, 3);
        tempPhase6.setPhase_name("Doc Phase 3");
        tempPhase6.setPhase_start(start.getTime());
        tempPhase6.setPhase_end(end.getTime());
        tempPhase6.setPhase_notes("Notes go here.");
        tempPhase6.setPhase_status("Planned");
        tempPhase6.setProject_id_fk(projectList.get(1).getProject_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 6);
        end.add(Calendar.MONTH, 7);
        tempPhase7.setPhase_name("Clean Phase 1");
        tempPhase7.setPhase_start(start.getTime());
        tempPhase7.setPhase_end(end.getTime());
        tempPhase7.setPhase_notes("Notes go here.");
        tempPhase7.setPhase_status("Planned");
        tempPhase7.setProject_id_fk(projectList.get(2).getProject_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 6);
        end.add(Calendar.MONTH, 7);
        tempPhase8.setPhase_name("Clean Phase 2");
        tempPhase8.setPhase_start(start.getTime());
        tempPhase8.setPhase_end(end.getTime());
        tempPhase8.setPhase_notes("Notes go here.");
        tempPhase8.setPhase_status("Planned");
        tempPhase8.setProject_id_fk(projectList.get(2).getProject_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 7);
        end.add(Calendar.MONTH, 8);
        tempPhase9.setPhase_name("Clean Phase 3");
        tempPhase9.setPhase_start(start.getTime());
        tempPhase9.setPhase_end(end.getTime());
        tempPhase9.setPhase_notes("Notes go here.");
        tempPhase9.setPhase_status("Planned");
        tempPhase9.setProject_id_fk(projectList.get(2).getProject_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 9);
        end.add(Calendar.MONTH, 11);
        tempPhase10.setPhase_name("Cloud Phase 1");
        tempPhase10.setPhase_start(start.getTime());
        tempPhase10.setPhase_end(end.getTime());
        tempPhase10.setPhase_notes("Notes go here.");
        tempPhase10.setPhase_status("Planned");
        tempPhase10.setProject_id_fk(projectList.get(3).getProject_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 11);
        end.add(Calendar.MONTH, 13);
        tempPhase11.setPhase_name("Cloud Phase 2");
        tempPhase11.setPhase_start(start.getTime());
        tempPhase11.setPhase_end(end.getTime());
        tempPhase11.setPhase_notes("Notes go here.");
        tempPhase11.setPhase_status("Planned");
        tempPhase11.setProject_id_fk(projectList.get(3).getProject_id());

        db.phaseDao().insertPhase(tempPhase1);
        db.phaseDao().insertPhase(tempPhase2);
        db.phaseDao().insertPhase(tempPhase3);
        db.phaseDao().insertPhase(tempPhase4);
        db.phaseDao().insertPhase(tempPhase5);
        db.phaseDao().insertPhase(tempPhase6);
        db.phaseDao().insertPhase(tempPhase7);
        db.phaseDao().insertPhase(tempPhase8);
        db.phaseDao().insertPhase(tempPhase9);
        db.phaseDao().insertPhase(tempPhase10);
        db.phaseDao().insertPhase(tempPhase11);

    }

    private void insertWorkUnits() {
        Calendar start;
        Calendar end;
        List<Phase> phaseList = db.phaseDao().getAllPhases();
        if (phaseList == null) return;

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, -6);
        end.add(Calendar.MONTH, -5);
        tempWorkUnit1.setWorkUnit_title("Sec 1 Unit 1");
        tempWorkUnit1.setWorkUnit_start(start.getTime());
        tempWorkUnit1.setWorkUnit_end(end.getTime());
        tempWorkUnit1.setWorkUnit_status("Completed");
        tempWorkUnit1.setWorkUnit_notes("Notes go here.");
        tempWorkUnit1.setPhase_id_fk(phaseList.get(0).getPhase_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, -6);
        end.add(Calendar.MONTH, -5);
        tempWorkUnit2.setWorkUnit_title("Sec 1 Unit 2");
        tempWorkUnit2.setWorkUnit_start(start.getTime());
        tempWorkUnit2.setWorkUnit_end(end.getTime());
        tempWorkUnit2.setWorkUnit_status("Completed");
        tempWorkUnit2.setWorkUnit_notes("Notes go here.");
        tempWorkUnit2.setPhase_id_fk(phaseList.get(0).getPhase_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, -6);
        end.add(Calendar.MONTH, -5);
        tempWorkUnit3.setWorkUnit_title("Sec 2 Unit 1");
        tempWorkUnit3.setWorkUnit_start(start.getTime());
        tempWorkUnit3.setWorkUnit_end(end.getTime());
        tempWorkUnit3.setWorkUnit_status("Completed");
        tempWorkUnit3.setWorkUnit_notes("Notes go here.");
        tempWorkUnit3.setPhase_id_fk(phaseList.get(1).getPhase_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, -5);
        end.add(Calendar.MONTH, -4);
        tempWorkUnit4.setWorkUnit_title("Sec 3 Unit 1");
        tempWorkUnit4.setWorkUnit_start(start.getTime());
        tempWorkUnit4.setWorkUnit_end(end.getTime());
        tempWorkUnit4.setWorkUnit_status("Completed");
        tempWorkUnit4.setWorkUnit_notes("Notes go here.");
        tempWorkUnit4.setPhase_id_fk(phaseList.get(2).getPhase_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, -1);
        end.add(Calendar.MONTH, 0);
        tempWorkUnit5.setWorkUnit_title("Docs 1 Unit 1");
        tempWorkUnit5.setWorkUnit_start(start.getTime());
        tempWorkUnit5.setWorkUnit_end(end.getTime());
        tempWorkUnit5.setWorkUnit_status("Completed");
        tempWorkUnit5.setWorkUnit_notes("Notes go here.");
        tempWorkUnit5.setPhase_id_fk(phaseList.get(3).getPhase_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, -1);
        end.add(Calendar.MONTH, 0);
        tempWorkUnit6.setWorkUnit_title("Docs 1 Unit 2");
        tempWorkUnit6.setWorkUnit_start(start.getTime());
        tempWorkUnit6.setWorkUnit_end(end.getTime());
        tempWorkUnit6.setWorkUnit_status("Completed");
        tempWorkUnit6.setWorkUnit_notes("Notes go here.");
        tempWorkUnit6.setPhase_id_fk(phaseList.get(3).getPhase_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 0);
        end.add(Calendar.MONTH, 2);
        tempWorkUnit7.setWorkUnit_title("Docs 2 Unit 1");
        tempWorkUnit7.setWorkUnit_start(start.getTime());
        tempWorkUnit7.setWorkUnit_end(end.getTime());
        tempWorkUnit7.setWorkUnit_status("In Progress");
        tempWorkUnit7.setWorkUnit_notes("Notes go here.");
        tempWorkUnit7.setPhase_id_fk(phaseList.get(4).getPhase_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 0);
        end.add(Calendar.MONTH, 2);
        tempWorkUnit8.setWorkUnit_title("Docs 2 Unit 2");
        tempWorkUnit8.setWorkUnit_start(start.getTime());
        tempWorkUnit8.setWorkUnit_end(end.getTime());
        tempWorkUnit8.setWorkUnit_status("In Progress");
        tempWorkUnit8.setWorkUnit_notes("Notes go here.");
        tempWorkUnit8.setPhase_id_fk(phaseList.get(4).getPhase_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 2);
        end.add(Calendar.MONTH, 3);
        tempWorkUnit9.setWorkUnit_title("Docs 3 Unit 1");
        tempWorkUnit9.setWorkUnit_start(start.getTime());
        tempWorkUnit9.setWorkUnit_end(end.getTime());
        tempWorkUnit9.setWorkUnit_status("Planned");
        tempWorkUnit9.setWorkUnit_notes("Notes go here.");
        tempWorkUnit9.setPhase_id_fk(phaseList.get(5).getPhase_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 6);
        end.add(Calendar.MONTH, 7);
        tempWorkUnit10.setWorkUnit_title("Clean 1 Unit 1");
        tempWorkUnit10.setWorkUnit_start(start.getTime());
        tempWorkUnit10.setWorkUnit_end(end.getTime());
        tempWorkUnit10.setWorkUnit_status("Planned");
        tempWorkUnit10.setWorkUnit_notes("Notes go here.");
        tempWorkUnit10.setPhase_id_fk(phaseList.get(6).getPhase_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 6);
        end.add(Calendar.MONTH, 7);
        tempWorkUnit11.setWorkUnit_title("Clean 2 Unit 1");
        tempWorkUnit11.setWorkUnit_start(start.getTime());
        tempWorkUnit11.setWorkUnit_end(end.getTime());
        tempWorkUnit11.setWorkUnit_status("Planned");
        tempWorkUnit11.setWorkUnit_notes("Notes go here.");
        tempWorkUnit11.setPhase_id_fk(phaseList.get(7).getPhase_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 7);
        end.add(Calendar.MONTH, 8);
        tempWorkUnit12.setWorkUnit_title("Clean 3 Unit 1");
        tempWorkUnit12.setWorkUnit_start(start.getTime());
        tempWorkUnit12.setWorkUnit_end(end.getTime());
        tempWorkUnit12.setWorkUnit_status("Planned");
        tempWorkUnit12.setWorkUnit_notes("Notes go here.");
        tempWorkUnit12.setPhase_id_fk(phaseList.get(8).getPhase_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 11);
        end.add(Calendar.MONTH, 12);
        tempWorkUnit13.setWorkUnit_title("Cloud 1 Unit 1");
        tempWorkUnit13.setWorkUnit_start(start.getTime());
        tempWorkUnit13.setWorkUnit_end(end.getTime());
        tempWorkUnit13.setWorkUnit_status("Planned");
        tempWorkUnit13.setWorkUnit_notes("Notes go here.");
        tempWorkUnit13.setPhase_id_fk(phaseList.get(9).getPhase_id());

        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.add(Calendar.MONTH, 12);
        end.add(Calendar.MONTH, 13);
        tempWorkUnit14.setWorkUnit_title("Cloud 2 Unit 1");
        tempWorkUnit14.setWorkUnit_start(start.getTime());
        tempWorkUnit14.setWorkUnit_end(end.getTime());
        tempWorkUnit14.setWorkUnit_status("Planned");
        tempWorkUnit14.setWorkUnit_notes("Notes go here.");
        tempWorkUnit14.setPhase_id_fk(phaseList.get(10).getPhase_id());

        db.workUnitDao().insertAll(tempWorkUnit1, tempWorkUnit2, tempWorkUnit3, tempWorkUnit4,
                tempWorkUnit5, tempWorkUnit6, tempWorkUnit7, tempWorkUnit8, tempWorkUnit9,
                tempWorkUnit10, tempWorkUnit11, tempWorkUnit12, tempWorkUnit13, tempWorkUnit14);

    }

    private void insertResources() {
        List<WorkUnit> workUnitList = db.workUnitDao().getAllWorkUnits();
        if (workUnitList == null) return;

        tempResource1.setResource_name("Joey Joejoe");
        tempResource1.setResource_type("Dedicated");
        tempResource1.setResource_phone("555-555-5555");
        tempResource1.setResource_email("joejoe@splendid.xyz");
        tempResource1.setAssigned(0);
        tempResource1.setRequested(0);
        tempResource1.setWorkUnit_id(workUnitList.get(4).getWorkUnit_id());

        tempResource2.setResource_name("Maximilian Baird");
        tempResource2.setResource_type("Dedicated");
        tempResource2.setResource_phone("555-555-5555");
        tempResource2.setResource_email("Baird@splendid.xyz");
        tempResource2.setAssigned(0);
        tempResource2.setRequested(0);
        tempResource2.setWorkUnit_id(workUnitList.get(4).getWorkUnit_id());

        tempResource3.setResource_name("Isis Adkins");
        tempResource3.setResource_type("Dedicated");
        tempResource3.setResource_phone("555-555-5555");
        tempResource3.setResource_email("Adkins@splendid.xyz");
        tempResource3.setAssigned(0);
        tempResource3.setRequested(0);
        tempResource3.setWorkUnit_id(workUnitList.get(5).getWorkUnit_id());

        tempResource4.setResource_name("Janet Blackwell");
        tempResource4.setResource_type("Dedicated");
        tempResource4.setResource_phone("555-555-5555");
        tempResource4.setResource_email("Blackwell@splendid.xyz");
        tempResource4.setAssigned(0);
        tempResource4.setRequested(0);
        tempResource4.setWorkUnit_id(workUnitList.get(5).getWorkUnit_id());

        tempResource5.setResource_name("Izayah Lozano");
        tempResource5.setResource_type("Dedicated");
        tempResource5.setResource_phone("555-555-5555");
        tempResource5.setResource_email("Lozano@splendid.xyz");
        tempResource5.setAssigned(0);
        tempResource5.setRequested(0);
        tempResource5.setWorkUnit_id(workUnitList.get(6).getWorkUnit_id());

        tempResource6.setResource_name("Ruben Estes");
        tempResource6.setResource_type("Dedicated");
        tempResource6.setResource_phone("555-555-5555");
        tempResource6.setResource_email("Estes@splendid.xyz");
        tempResource6.setAssigned(0);
        tempResource6.setRequested(0);
        tempResource6.setWorkUnit_id(workUnitList.get(7).getWorkUnit_id());

        tempResource7.setResource_name("Diya Dorsey");
        tempResource7.setResource_type("Dedicated");
        tempResource7.setResource_phone("555-555-5555");
        tempResource7.setResource_email("Dorsey@splendid.xyz");
        tempResource7.setAssigned(0);
        tempResource7.setRequested(0);
        tempResource7.setWorkUnit_id(workUnitList.get(8).getWorkUnit_id());

        tempResource8.setResource_name("Marcel Foley");
        tempResource8.setResource_type("Dedicated");
        tempResource8.setResource_phone("555-555-5555");
        tempResource8.setResource_email("Foley@splendid.xyz");
        tempResource8.setAssigned(0);
        tempResource8.setRequested(0);

        tempResource9.setResource_name("Zachery Lynn");
        tempResource9.setResource_type("Dedicated");
        tempResource9.setResource_phone("555-555-5555");
        tempResource9.setResource_email("Lynn@splendid.xyz");
        tempResource9.setAssigned(0);
        tempResource9.setRequested(0);

        tempResource10.setResource_name("Rolando Good");
        tempResource10.setResource_type("Dedicated");
        tempResource10.setResource_phone("555-555-5555");
        tempResource10.setResource_email("Good@splendid.xyz");
        tempResource10.setAssigned(0);
        tempResource10.setRequested(0);

        tempResource11.setResource_name("Amber Preston");
        tempResource11.setResource_type("Dedicated");
        tempResource11.setResource_phone("555-555-5555");
        tempResource11.setResource_email("Preston@splendid.xyz");
        tempResource11.setAssigned(0);
        tempResource11.setRequested(0);

        tempResource12.setResource_name("Lexie Marshall");
        tempResource12.setResource_type("Dedicated");
        tempResource12.setResource_phone("555-555-5555");
        tempResource12.setResource_email("Marshall@splendid.xyz");
        tempResource12.setAssigned(0);
        tempResource12.setRequested(0);

        tempResource13.setResource_name("Mckayla Johnson");
        tempResource13.setResource_type("Dedicated");
        tempResource13.setResource_phone("555-555-5555");
        tempResource13.setResource_email("Mckayla@splendid.xyz");
        tempResource13.setAssigned(0);
        tempResource13.setRequested(0);

        tempResource14.setResource_name("Larry Dudley");
        tempResource14.setResource_type("Dedicated");
        tempResource14.setResource_phone("555-555-5555");
        tempResource14.setResource_email("Dudley@splendid.xyz");
        tempResource14.setAssigned(0);
        tempResource14.setRequested(0);

        tempResource15.setResource_name("Jake Bullock");
        tempResource15.setResource_type("Dedicated");
        tempResource15.setResource_phone("555-555-5555");
        tempResource15.setResource_email("Bullock@splendid.xyz");
        tempResource15.setAssigned(0);
        tempResource15.setRequested(0);

        tempResource16.setResource_name("Lea Campbell");
        tempResource16.setResource_type("Dedicated");
        tempResource16.setResource_phone("555-555-5555");
        tempResource16.setResource_email("Campbell@splendid.xyz");
        tempResource16.setAssigned(0);
        tempResource16.setRequested(0);

        tempResource17.setResource_name("Liberty Floyd");
        tempResource17.setResource_type("Dedicated");
        tempResource17.setResource_phone("555-555-5555");
        tempResource17.setResource_email("Floyd@splendid.xyz");
        tempResource17.setAssigned(0);
        tempResource17.setRequested(0);

        tempResource18.setResource_name("Mitchell Cruz");
        tempResource18.setResource_type("Dedicated");
        tempResource18.setResource_phone("555-555-5555");
        tempResource18.setResource_email("Cruz@splendid.xyz");
        tempResource18.setAssigned(0);
        tempResource18.setRequested(0);

        tempResource19.setResource_name("Darion Case");
        tempResource19.setResource_type("Dedicated");
        tempResource19.setResource_phone("555-555-5555");
        tempResource19.setResource_email("Case@splendid.xyz");
        tempResource19.setAssigned(0);
        tempResource19.setRequested(0);

        tempResource20.setResource_name("Derick Harmon");
        tempResource20.setResource_type("Dedicated");
        tempResource20.setResource_phone("555-555-5555");
        tempResource20.setResource_email("Harmon@splendid.xyz");
        tempResource20.setAssigned(0);
        tempResource20.setRequested(0);

        tempResource21.setResource_name("Adalyn Archer");
        tempResource21.setResource_type("Shared");
        tempResource21.setResource_phone("555-555-5555");
        tempResource21.setResource_email("Archer@Kovacek.xyz");
        tempResource21.setAssigned(0);
        tempResource21.setRequested(0);

        tempResource22.setResource_name("Alexandria Jordan");
        tempResource22.setResource_type("Shared");
        tempResource22.setResource_phone("555-555-5555");
        tempResource22.setResource_email("Jordan@Bernier.xyz");
        tempResource22.setAssigned(0);
        tempResource22.setRequested(0);

        tempResource23.setResource_name("Emery Ray");
        tempResource23.setResource_type("Shared");
        tempResource23.setResource_phone("555-555-5555");
        tempResource23.setResource_email("Emery@Koelpin.xyz");
        tempResource23.setAssigned(0);
        tempResource23.setRequested(0);

        tempResource24.setResource_name("Kristina Carney");
        tempResource24.setResource_type("Shared");
        tempResource24.setResource_phone("555-555-5555");
        tempResource24.setResource_email("Carney@Johnston.xyz");
        tempResource24.setAssigned(0);
        tempResource24.setRequested(0);

        tempResource25.setResource_name("Joanna Riggs");
        tempResource25.setResource_type("Shared");
        tempResource25.setResource_phone("555-555-5555");
        tempResource25.setResource_email("Riggs@Beier.xyz");
        tempResource25.setAssigned(0);
        tempResource25.setRequested(0);

        tempResource26.setResource_name("Ellis Adkins");
        tempResource26.setResource_type("Shared");
        tempResource26.setResource_phone("555-555-5555");
        tempResource26.setResource_email("Adkins@Doyle.xyz");
        tempResource26.setAssigned(0);
        tempResource26.setRequested(0);

        tempResource27.setResource_name("Kamari Gomez");
        tempResource27.setResource_type("Shared");
        tempResource27.setResource_phone("555-555-5555");
        tempResource27.setResource_email("Gomez@Sipes.xyz");
        tempResource27.setAssigned(0);
        tempResource27.setRequested(0);

        tempResource28.setResource_name("Michelle Ray");
        tempResource28.setResource_type("Shared");
        tempResource28.setResource_phone("555-555-5555");
        tempResource28.setResource_email("Michelle@Glover.xyz");
        tempResource28.setAssigned(0);
        tempResource28.setRequested(0);

        tempResource29.setResource_name("Zander Davidson");
        tempResource29.setResource_type("Shared");
        tempResource29.setResource_phone("555-555-5555");
        tempResource29.setResource_email("Zander@Gerlach.xyz");
        tempResource29.setAssigned(0);
        tempResource29.setRequested(0);

        tempResource30.setResource_name("Madalynn Nichols");
        tempResource30.setResource_type("Shared");
        tempResource30.setResource_phone("555-555-5555");
        tempResource30.setResource_email("Madalynn@Schimmel.xyz");
        tempResource30.setAssigned(0);
        tempResource30.setRequested(0);

        db.resourceDao().insertResource(tempResource1);
        db.resourceDao().insertResource(tempResource2);
        db.resourceDao().insertResource(tempResource3);
        db.resourceDao().insertResource(tempResource4);
        db.resourceDao().insertResource(tempResource5);
        db.resourceDao().insertResource(tempResource6);
        db.resourceDao().insertResource(tempResource7);
        db.resourceDao().insertResource(tempResource8);
        db.resourceDao().insertResource(tempResource9);
        db.resourceDao().insertResource(tempResource10);
        db.resourceDao().insertResource(tempResource11);
        db.resourceDao().insertResource(tempResource12);
        db.resourceDao().insertResource(tempResource13);
        db.resourceDao().insertResource(tempResource14);
        db.resourceDao().insertResource(tempResource15);
        db.resourceDao().insertResource(tempResource16);
        db.resourceDao().insertResource(tempResource17);
        db.resourceDao().insertResource(tempResource18);
        db.resourceDao().insertResource(tempResource19);
        db.resourceDao().insertResource(tempResource20);
        db.resourceDao().insertResource(tempResource21);
        db.resourceDao().insertResource(tempResource22);
        db.resourceDao().insertResource(tempResource23);
        db.resourceDao().insertResource(tempResource24);
        db.resourceDao().insertResource(tempResource25);
        db.resourceDao().insertResource(tempResource26);
        db.resourceDao().insertResource(tempResource27);
        db.resourceDao().insertResource(tempResource28);
        db.resourceDao().insertResource(tempResource29);
        db.resourceDao().insertResource(tempResource30);

    }
}
