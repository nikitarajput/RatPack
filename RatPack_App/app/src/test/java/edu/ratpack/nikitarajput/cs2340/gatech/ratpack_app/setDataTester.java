package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.GraphLogic;

/**
 * Created by soniaggarwal on 11/8/17.
 */

public class setDataTester {
    @Parameterized.Parameter
    static List<ArrayList<Integer>> test;

    @Parameterized.Parameter
    static int startYear;

    @Parameterized.Parameter
    static int startMonthInt;

    @Parameterized.Parameter
    static int endYear;

    @Parameterized.Parameter
    static int endMonthInt;

    @Test
    public void invalid_nums() throws Exception {
        GraphLogic graphLogic = new GraphLogic(2017, 1, 2017, 1);
        test = graphLogic.setData(2017, 1, 2017, 1);
    }
    @Test
    public void large_nums() throws Exception {

    }
    @Test
    public void null_input() throws Exception {

    }

    @Test
    public void normal() throws Exception {

    }

    @Test
    public void incomplete_inputs() throws Exception {

    }

    @BeforeClass
    public static void build(){
        test = new ArrayList<>();
    }

    @AfterClass
    public static void tearDown(){
        test = null;
    }
}
