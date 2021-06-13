package com.example.projectguru.ui;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PinSetTest {

    @Test
    public void validatePin() {

        // Entries with exactly 4 digits should return true

        String pin1 = "1234";
        int length1 = pin1.length();
        PinSet entry1 = new PinSet();
        Assert.assertTrue(entry1.validatePin(length1));

        // Entries with fewer than 4 digits should return false

        String pin2 = "123";
        int length2 = pin2.length();
        PinSet entry2 = new PinSet();
        Assert.assertFalse(entry2.validatePin(length2));

        String pin3 = "12";
        int length3 = pin3.length();
        PinSet entry3 = new PinSet();
        Assert.assertFalse(entry3.validatePin(length3));

        String pin4 = "1";
        int length4 = pin4.length();
        PinSet entry4 = new PinSet();
        Assert.assertFalse(entry4.validatePin(length4));

    }
}