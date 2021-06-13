package com.example.projectguru.tools;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class ConvertersTest {

    @Test
    public void fromTimestamp() {

        // If method is functioning properly, output should equal expected

        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.MONTH, 11);
        cal1.set(Calendar.DATE, 30);
        cal1.set(Calendar.YEAR, 2020);
        Date date1 = cal1.getTime();
        Long input1 = date1.getTime();
        Date output1;
        Date expected1 = date1;

        Converters convertor1 = new Converters();
        output1 = convertor1.fromTimestamp(input1);
        Assert.assertEquals(expected1, output1);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.MONTH, 04);
        cal2.set(Calendar.DATE, 30);
        cal2.set(Calendar.YEAR, 2021);
        Date date2 = cal2.getTime();
        Long input2 = date2.getTime();
        Date output2;
        Date expected2 = date2;

        Converters convertor2 = new Converters();
        output2 = convertor2.fromTimestamp(input2);
        Assert.assertEquals(expected2, output2);

        Calendar cal3 = Calendar.getInstance();
        cal3.set(Calendar.MONTH, 11);
        cal3.set(Calendar.DATE, 30);
        cal3.set(Calendar.YEAR, 2021);
        Date date3 = cal3.getTime();
        Long input3 = date3.getTime();
        Date output3;
        Date expected3 = date3;

        Converters convertor3 = new Converters();
        output3 = convertor3.fromTimestamp(input3);
        Assert.assertEquals(expected3, output3);

        Calendar cal4 = Calendar.getInstance();
        cal4.set(Calendar.MONTH, 02);
        cal4.set(Calendar.DATE, 28);
        cal4.set(Calendar.YEAR, 2022);
        Date date4 = cal4.getTime();
        Long input4 = date4.getTime();
        Date output4;
        Date expected4 = date4;

        Converters convertor4 = new Converters();
        output4 = convertor4.fromTimestamp(input4);
        Assert.assertEquals(expected4, output4);

        // if method is functioning properly, output should not equal expected

        Calendar cal5 = Calendar.getInstance();
        cal5.set(Calendar.MONTH, 02);
        cal5.set(Calendar.DATE, 28);
        cal5.set(Calendar.YEAR, 2022);
        Date date5 = cal5.getTime();
        Long input5 = date5.getTime();
        input5++;
        Date output5;
        Date expected5 = date5;

        Converters convertor5 = new Converters();
        output5 = convertor5.fromTimestamp(input5);
        Assert.assertNotEquals(expected5, output5);

        Calendar cal6 = Calendar.getInstance();
        cal6.set(Calendar.MONTH, 02);
        cal6.set(Calendar.DATE, 28);
        cal6.set(Calendar.YEAR, 2022);
        Date date6 = cal6.getTime();
        Long input6 = date6.getTime();
        input6--;
        Date output6;
        Date expected6 = date6;

        Converters convertor6 = new Converters();
        output6 = convertor6.fromTimestamp(input6);
        Assert.assertNotEquals(expected6, output6);

    }

    @Test
    public void dateToTimestamp() {

        // if method is functioning properly, output should equal expected

        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.MONTH, 11);
        cal1.set(Calendar.DATE, 30);
        cal1.set(Calendar.YEAR, 2020);
        Date input1 = cal1.getTime();
        Long output1;
        Long expected1 = input1.getTime();

        Converters convertor1 = new Converters();
        output1 = convertor1.dateToTimestamp(input1);
        Assert.assertEquals(expected1, output1);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.MONTH, 04);
        cal2.set(Calendar.DATE, 30);
        cal2.set(Calendar.YEAR, 2021);
        Date date2 = cal2.getTime();
        Long input2 = date2.getTime();
        Date output2;
        Date expected2 = date2;

        Converters convertor2 = new Converters();
        output2 = convertor2.fromTimestamp(input2);
        Assert.assertEquals(expected2, output2);

        Calendar cal3 = Calendar.getInstance();
        cal3.set(Calendar.MONTH, 11);
        cal3.set(Calendar.DATE, 30);
        cal3.set(Calendar.YEAR, 2021);
        Date date3 = cal3.getTime();
        Long input3 = date3.getTime();
        Date output3;
        Date expected3 = date3;

        Converters convertor3 = new Converters();
        output3 = convertor3.fromTimestamp(input3);
        Assert.assertEquals(expected3, output3);

        Calendar cal4 = Calendar.getInstance();
        cal4.set(Calendar.MONTH, 02);
        cal4.set(Calendar.DATE, 28);
        cal4.set(Calendar.YEAR, 2022);
        Date date4 = cal4.getTime();
        Long input4 = date4.getTime();
        Date output4;
        Date expected4 = date4;

        Converters convertor4 = new Converters();
        output4 = convertor4.fromTimestamp(input4);
        Assert.assertEquals(expected4, output4);

        // if method is functioning properly, output should not equal expected

        Calendar cal5 = Calendar.getInstance();
        cal5.set(Calendar.MONTH, 02);
        cal5.set(Calendar.DATE, 28);
        cal5.set(Calendar.YEAR, 2022);
        Date date5 = cal5.getTime();
        Long input5 = date5.getTime();
        input5--;
        Date output5;
        Date expected5 = date5;

        Converters convertor5 = new Converters();
        output5 = convertor5.fromTimestamp(input5);
        Assert.assertNotEquals(expected5, output5);

        Calendar cal6 = Calendar.getInstance();
        cal6.set(Calendar.MONTH, 02);
        cal6.set(Calendar.DATE, 28);
        cal6.set(Calendar.YEAR, 2022);
        Date date6 = cal6.getTime();
        Long input6 = date6.getTime();
        input6++;
        Date output6;
        Date expected6 = date6;

        Converters convertor6 = new Converters();
        output6 = convertor6.fromTimestamp(input6);
        Assert.assertNotEquals(expected6, output6);


    }
}