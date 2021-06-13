package com.example.projectguru.ui;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectEditTest {

    @Test
    public void validateName() {

        // Names with at least one character should return true

        String name1 = "Andy";
        int length1 = name1.length();
        ProjectEdit project1 = new ProjectEdit();
        Assert.assertTrue(project1.validateName(length1));

        String name2 = "Bea";
        int length2 = name2.length();
        ProjectEdit project2 = new ProjectEdit();
        Assert.assertTrue(project2.validateName(length2));

        String name3 = "Yo";
        int length3 = name3.length();
        ProjectEdit project3 = new ProjectEdit();
        Assert.assertTrue(project3.validateName(length3));

        String name4 = "Q";
        int length4 = name4.length();
        ProjectEdit project4 = new ProjectEdit();
        Assert.assertTrue(project4.validateName(length4));

        // Names with less than one character should return false

        String name5 = "";
        int length5 = name5.length();
        ProjectEdit project5 = new ProjectEdit();
        Assert.assertFalse(project5.validateName(length5));

    }
}