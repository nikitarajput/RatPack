package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.util.Log;

import junit.framework.Assert;

import org.junit.runner.RunWith;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import static org.mockito.Mockito.when;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.GraphLogic;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.Rat;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.RatFB;

/**
 * Created by Jacob on 11/13/2017.
 */


@RunWith(PowerMockRunner.class)
@PrepareForTest(RatFB.class)
public class oddMonthTester {

    @Test
    public void evenMonth() {
        int testIndex;
        PowerMockito.mockStatic(RatFB.class);
        Rat rat = new Rat("Fake Rat", 100, 100, "Home", "44 West 4th Street", "New York", 10012, "Manhattan");
        Rat[] ratList = {rat};
        when(RatFB.getAllRats()).thenReturn(ratList);
        GraphLogic graphLogic = new GraphLogic(2016, 1, 2017, 10);

        List actual = new ArrayList();
        testIndex = 2;
        graphLogic.oddMonth(testIndex, actual);
        List expected = new ArrayList(Arrays.asList(""));
        Assert.assertEquals(expected, actual);

        actual = new ArrayList();
        testIndex = 4;
        graphLogic.oddMonth(testIndex, actual);
        expected = new ArrayList(Arrays.asList(""));
        Assert.assertEquals(expected, actual);

        actual = new ArrayList();
        testIndex = 0;
        graphLogic.oddMonth(testIndex, actual);
        expected = new ArrayList(Arrays.asList());
        Assert.assertEquals(expected, actual);

        actual = new ArrayList();
        testIndex = 12;
        graphLogic.oddMonth(testIndex, actual);
        expected = new ArrayList(Arrays.asList(""));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void oddMonth() {
        int testIndex;
        PowerMockito.mockStatic(RatFB.class);
        Rat rat = new Rat("Fake Rat", 100, 100, "Home", "44 West 4th Street", "New York", 10012, "Manhattan");
        Rat[] ratList = {rat};
        when(RatFB.getAllRats()).thenReturn(ratList);
        GraphLogic graphLogic = new GraphLogic(2016, 2, 2017, 10);

        List actual = new ArrayList();
        testIndex = 3;
        graphLogic.oddMonth(testIndex, actual);
        List expected = new ArrayList(Arrays.asList("Mar"));
        Assert.assertEquals(expected, actual);

        actual = new ArrayList();
        testIndex = 5;
        graphLogic.oddMonth(testIndex, actual);
        expected = new ArrayList(Arrays.asList("May"));
        Assert.assertEquals(expected, actual);

        actual = new ArrayList();
        testIndex = 1;
        graphLogic.oddMonth(testIndex, actual);
        expected = new ArrayList(Arrays.asList());
        Assert.assertEquals(expected, actual);

        actual = new ArrayList();
        testIndex = 13;
        graphLogic.oddMonth(testIndex, actual);
        expected = new ArrayList(Arrays.asList());
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void seriesMonths() {
        int testIndex;
        PowerMockito.mockStatic(RatFB.class);
        Rat rat = new Rat("Fake Rat", 100, 100, "Home", "44 West 4th Street", "New York", 10012, "Manhattan");
        Rat[] ratList = {rat};
        when(RatFB.getAllRats()).thenReturn(ratList);
        GraphLogic graphLogic = new GraphLogic(2016, 1, 2017, 10);

        List actual = new ArrayList();
        testIndex = 3;
        graphLogic.oddMonth(testIndex, actual);
        List expected = new ArrayList(Arrays.asList("Mar"));
        Assert.assertEquals(expected, actual);

        //actual = new ArrayList();
        testIndex = 4;
        graphLogic.oddMonth(testIndex, actual);
        expected = new ArrayList(Arrays.asList("Mar", ""));
        Assert.assertEquals(expected, actual);

        //actual = new ArrayList();
        testIndex = 5;
        graphLogic.oddMonth(testIndex, actual);
        expected = new ArrayList(Arrays.asList("Mar", "", "May"));
        Assert.assertEquals(expected, actual);

        //actual = new ArrayList();
        testIndex = 6;
        graphLogic.oddMonth(testIndex, actual);
        expected = new ArrayList(Arrays.asList("Mar", "", "May", ""));
        Assert.assertEquals(expected, actual);
    }


}
