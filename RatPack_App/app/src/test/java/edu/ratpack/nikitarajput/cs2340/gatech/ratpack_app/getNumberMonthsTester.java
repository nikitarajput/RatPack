package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.GraphLogic;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.Rat;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.RatFB;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Shreya on 11/12/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(RatFB.class)
public class getNumberMonthsTester {

    @Test
    public void diffZeroYearsZeroMonths() {
        PowerMockito.mockStatic(RatFB.class);
        Rat rat = new Rat("Fake Rat", 100, 100, "Home", "44 West 4th Street", "New York", 10012, "Manhattan");
        Rat[] ratList = {rat};
        when(RatFB.getAllRats()).thenReturn(ratList);
        GraphLogic graphLogic = new GraphLogic(2017, 1, 2017, 1);
        int actual = graphLogic.getNumberMonths();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void diffOneYearZeroMonths() {
        PowerMockito.mockStatic(RatFB.class);
        Rat rat = new Rat("Fake Rat", 100, 100, "Home", "44 West 4th Street", "New York", 10012, "Manhattan");
        Rat[] ratList = {rat};
        when(RatFB.getAllRats()).thenReturn(ratList);
        GraphLogic graphLogic = new GraphLogic(2017, 1, 2018, 1);
        int actual = graphLogic.getNumberMonths();
        int expected = 12;
        assertEquals(expected, actual);
    }

    @Test
    public void diffOneYearFiveMonths() {
        PowerMockito.mockStatic(RatFB.class);
        Rat rat = new Rat("Fake Rat", 100, 100, "Home", "44 West 4th Street", "New York", 10012, "Manhattan");
        Rat[] ratList = {rat};
        when(RatFB.getAllRats()).thenReturn(ratList);
        GraphLogic graphLogic = new GraphLogic(2017, 1, 2018, 6);
        int actual = graphLogic.getNumberMonths();
        int expected = 17;
        assertEquals(expected, actual);
    }

    @Test
    public void diffTwoYearsZeroMonths() {
        PowerMockito.mockStatic(RatFB.class);
        Rat rat = new Rat("Fake Rat", 100, 100, "Home", "44 West 4th Street", "New York", 10012, "Manhattan");
        Rat[] ratList = {rat};
        when(RatFB.getAllRats()).thenReturn(ratList);
        GraphLogic graphLogic = new GraphLogic(2016, 1, 2018, 1);
        int actual = graphLogic.getNumberMonths();
        int expected = 24;
        assertEquals(expected, actual);
    }

    @Test
    public void diffTwoYearsFiveMonths() {
        PowerMockito.mockStatic(RatFB.class);
        Rat rat = new Rat("Fake Rat", 100, 100, "Home", "44 West 4th Street", "New York", 10012, "Manhattan");
        Rat[] ratList = {rat};
        when(RatFB.getAllRats()).thenReturn(ratList);
        GraphLogic graphLogic = new GraphLogic(2016, 1, 2018, 6);
        int actual = graphLogic.getNumberMonths();
        int expected = 29;
        assertEquals(expected, actual);
    }

}
