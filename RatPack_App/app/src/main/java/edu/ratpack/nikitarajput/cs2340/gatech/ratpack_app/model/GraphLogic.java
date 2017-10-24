package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;


import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class GraphLogic {

    private Rat[] ratlist;
    private String[] allDates;
    private int[][] monthData;
    private int[][] yearData;
    private LineDataSet dataSet;

    public GraphLogic(){
        ratlist = RatFB.getAllRats();
        allDates = new String[ratlist.length];
        monthData = new int[12][2];
        yearData = new int[10][2];
        for(int i = 0; i < ratlist.length; i++){
            allDates[i] = ratlist[i].getDate();
        }

        setMonthData();
        List<Entry> entries = new ArrayList<Entry>();

        for(int i = 0; i < monthData.length; i++){
            entries.add(new Entry(i, monthData[i][1]));
        }

        dataSet = new LineDataSet(entries, "Number of Rats"); // add entries to dataset


    }


    private void setMonthData(){
        for(int i = 0; i < allDates.length; i++){
            if(allDates[i].contains("Jan"))
                monthData[0][1]++;
            else if(allDates[i].contains("Feb"))
                monthData[1][1]++;
            else if(allDates[i].contains("Mar"))
                monthData[2][1]++;
            else if(allDates[i].contains("Apr"))
                monthData[3][1]++;
            else if(allDates[i].contains("May"))
                monthData[4][1]++;
            else if(allDates[i].contains("Jun"))
                monthData[5][1]++;
            else if(allDates[i].contains("Jul"))
                monthData[6][1]++;
            else if(allDates[i].contains("Aug"))
                monthData[7][1]++;
            else if(allDates[i].contains("Sep"))
                monthData[8][1]++;
            else if(allDates[i].contains("Oct"))
                monthData[9][1]++;
            else if(allDates[i].contains("Nov"))
                monthData[10][1]++;
            else if(allDates[i].contains("Dec"))
                monthData[11][1]++;
        }

    }


    public LineDataSet getMonthSet(){
        return dataSet;
    }


}


