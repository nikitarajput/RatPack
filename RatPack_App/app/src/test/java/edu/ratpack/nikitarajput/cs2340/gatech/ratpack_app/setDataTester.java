package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.GraphLogic;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.Rat;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.RatFB;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by soniaggarwal on 11/8/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(RatFB.class)
public class setDataTester {
    @Parameterized.Parameter
    static List<ArrayList<Integer>> actual;

    @Parameterized.Parameter
    static int startYear;

    @Parameterized.Parameter
    static int startMonthInt;

    @Parameterized.Parameter
    static int endYear;

    @Parameterized.Parameter
    static int endMonthInt;

    @Test
    public void sameYearDifferentMonth() throws Exception {
        PowerMockito.mockStatic(RatFB.class);
        Rat rat = new Rat("Name", 10, 10, "Home", "Address", "City", 19713, "Manhattan");
        Rat[] ratList = {rat};
        when(RatFB.getAllRats()).thenReturn(ratList);
        GraphLogic graphLogic = new GraphLogic(2017, 1, 2017, 12);
        actual = graphLogic.setData(2017, 1, 2017, 12);
        List<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0)));
        assertEquals(expected, actual);
    }

    @Test
    public void sameYearSameMonth() throws Exception {
        PowerMockito.mockStatic(RatFB.class);
        Rat rat = new Rat("Name", 10, 10, "Home", "Address", "City", 19713, "Manhattan");
        Rat[] ratList = {rat};
        when(RatFB.getAllRats()).thenReturn(ratList);
        GraphLogic graphLogic = new GraphLogic(2017, 2, 2017, 2);
        actual = graphLogic.setData(2017, 2, 2017, 2);
        List<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(Arrays.asList(0)));
        assertEquals(expected, actual);
    }

    @Test
    public void differentYearDifferentMonth() throws Exception {
        PowerMockito.mockStatic(RatFB.class);
        Rat rat = new Rat("Name", 10, 10, "Home", "Address", "City", 19713, "Manhattan");
        Rat[] ratList = {rat};
        when(RatFB.getAllRats()).thenReturn(ratList);
        GraphLogic graphLogic = new GraphLogic(2015, 2, 2017, 12);
        actual = graphLogic.setData(2015, 2, 2017, 12);
        List<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)));
        expected.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)));
        expected.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0)));
        assertEquals(expected, actual);
    }

    @BeforeClass
    public static void build(){
        actual = new ArrayList<>();
    }

    @AfterClass
    public static void tearDown() {
        actual = null;
    }
}
