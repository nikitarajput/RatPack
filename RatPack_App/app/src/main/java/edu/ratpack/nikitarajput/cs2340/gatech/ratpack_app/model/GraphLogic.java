package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;


import android.util.Log;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class GraphLogic {

    private Rat[] ratlist;
    private String[] allDates;
    private ArrayList<ArrayList<Integer>> sumData;
    private LineDataSet dataSet;
    private List<Entry> entries;
    private int startYear, endYear, startMonthInt, endMonthInt;

    public GraphLogic(){
        ratlist = RatFB.getAllRats();
        allDates = new String[ratlist.length];
        for(int i = 0; i < ratlist.length; i++){
            allDates[i] = ratlist[i].getDate();
        }
//TODO get starting values from activity
        startYear = 2016;
        endYear = 2017;
        startMonthInt = 2;
        endMonthInt = 10;

        sumData = new ArrayList<ArrayList<Integer>>();
        setData(startYear, startMonthInt, endYear, endMonthInt);
        entries = new ArrayList<Entry>();


        for(int i = 0; i < sumData.size(); i++){
            for(int j = 0; j < sumData.get(i).size(); j++){
                entries.add(new Entry(i*12 + j, sumData.get(i).get(j)));
            }
        }

        dataSet = new LineDataSet(entries, "Number of Rats"); // add entries to dataset
    }


    private void setData(int startYear, int startMonthInt, int endYear, int endMonthInt){
        for(int i = startYear; i <= endYear; i++){
            sumData.add(getMonthData("" + i));
        }
        sumData.set(0, new ArrayList<Integer>(sumData.get(0).subList(startMonthInt, 12)));
        sumData.set(sumData.size() - 1, new ArrayList<Integer>(sumData.get(sumData.size() - 1).subList(0, endMonthInt)));
    }

    private ArrayList<Integer> getMonthData(String year){
        ArrayList<Integer> monthData = new ArrayList<Integer>();
        for(int i = 0; i < 12; i++)
            monthData.add(0);
        for(int i = 0; i < allDates.length; i++){
            if(allDates[i].contains("Jan") && allDates[i].contains(year)) {
                monthData.set(0, monthData.get(0) + new Integer(1));
            }
            else if(allDates[i].contains("Feb") && allDates[i].contains(year))
                monthData.set(1, monthData.get(1) + new Integer(1));
            else if(allDates[i].contains("Mar") && allDates[i].contains(year))
                monthData.set(2, monthData.get(2) + new Integer(1));
            else if(allDates[i].contains("Apr") && allDates[i].contains(year))
                monthData.set(3, monthData.get(3) + new Integer(1));
            else if(allDates[i].contains("May") && allDates[i].contains(year))
                monthData.set(4, monthData.get(4) + new Integer(1));
            else if(allDates[i].contains("Jun") && allDates[i].contains(year))
                monthData.set(5, monthData.get(5) + new Integer(1));
            else if(allDates[i].contains("Jul") && allDates[i].contains(year))
                monthData.set(6, monthData.get(6) + new Integer(1));
            else if(allDates[i].contains("Aug") && allDates[i].contains(year))
                monthData.set(7, monthData.get(7) + new Integer(1));
            else if(allDates[i].contains("Sep") && allDates[i].contains(year))
                monthData.set(8, monthData.get(8) + new Integer(1));
            else if(allDates[i].contains("Oct") && allDates[i].contains(year))
                monthData.set(9, monthData.get(9) + new Integer(1));
            else if(allDates[i].contains("Nov") && allDates[i].contains(year))
                monthData.set(10, monthData.get(10) + new Integer(1));
            else if(allDates[i].contains("Dec") && allDates[i].contains(year))
                monthData.set(11, monthData.get(11) + new Integer(1));
        }

        return monthData;
    }

    public LineDataSet getSet(){
        return dataSet;
    }

    public void formatXAxis(XAxis xAxis){
        //TODO
    }

}


