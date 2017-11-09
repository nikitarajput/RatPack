package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;


import android.util.Log;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.controller.DaterActivity;

public class GraphLogic {


    private String[] allDates;
    private List<ArrayList<Integer>> sumData;
    private LineDataSet dataSet;
    private int startYear, endYear, startMonthInt, endMonthInt;


    public GraphLogic(int startYear, int startMonth, int endYear, int endMonth){
        Rat[] ratlist = RatFB.getAllRats();
        allDates = new String[ratlist.length];
        for(int i = 0; i < ratlist.length; i++){
            allDates[i] = ratlist[i].getDate();
        }

        this.startYear = startYear;
        this.endYear = endYear;
        this.startMonthInt = startMonth;
        this.endMonthInt = endMonth;

        sumData = new ArrayList<>();
        setData(this.startYear, startMonthInt, this.endYear, endMonthInt);
        List<Entry> entries = new ArrayList<>();

        int l = 0;
        for(int i = 0; i < sumData.size(); i++){
            for(int j = 0; j < sumData.get(i).size(); j++){
                entries.add(new Entry(l, sumData.get(i).get(j)));
                l++;
            }
        }

        dataSet = new LineDataSet(entries, "Number of Rats"); // add entries to dataset
    }

    /**
     * Populates setData which will then be used to populate entries
     * @param startYear literally start year
     * @param startMonthInt january is 1
     * @param endYear literally end year
     * @param endMonthInt last month december is 12
     */
    private void setData(int startYear, int startMonthInt, int endYear, int endMonthInt){
        //this adds data of all moths of each year in range
        for(int i = startYear; i <= endYear; i++){
            sumData.add(getMonthData("" + i));
        }

        //below trims to for year data to start and end at dictated months
        //sets first years first month
        sumData.set(0, new ArrayList<>(sumData.get(0).subList(startMonthInt - 1, 12)));
        if (endYear == startYear) {
            //sets last years last month
            sumData.set(sumData.size() - 1,
                    new ArrayList<>(sumData.get(sumData.size() - 1)
                            .subList(0, endMonthInt - (startMonthInt - 1))));
        }
        else {
            //sets last years last month
            sumData.set(sumData.size() - 1,
                    new ArrayList<>(sumData.get(sumData.size() - 1).subList(0, endMonthInt)));
        }
    }

    /**
     * Gets an arraylist for month values for year
     * @param year First three letters of the month
     * @return returns the arraylist of month values
     */
    private ArrayList<Integer> getMonthData(String year){
        ArrayList<Integer> monthData = new ArrayList<>();
        for(int i = 0; i < 12; i++)
            monthData.add(0);
        for(int i = 0; i < allDates.length; i++){
            if(allDates[i].contains("Jan") && allDates[i].contains(year)) {
                monthData.set(0, monthData.get(0) + 1);
            }
            else if(allDates[i].contains("Feb") && allDates[i].contains(year))
                monthData.set(1, monthData.get(1) + 1);
            else if(allDates[i].contains("Mar") && allDates[i].contains(year))
                monthData.set(2, monthData.get(2) + 1);
            else if(allDates[i].contains("Apr") && allDates[i].contains(year))
                monthData.set(3, monthData.get(3) + 1);
            else if(allDates[i].contains("May") && allDates[i].contains(year))
                monthData.set(4, monthData.get(4) + 1);
            else if(allDates[i].contains("Jun") && allDates[i].contains(year))
                monthData.set(5, monthData.get(5) + 1);
            else if(allDates[i].contains("Jul") && allDates[i].contains(year))
                monthData.set(6, monthData.get(6) + 1);
            else if(allDates[i].contains("Aug") && allDates[i].contains(year))
                monthData.set(7, monthData.get(7) + 1);
            else if(allDates[i].contains("Sep") && allDates[i].contains(year))
                monthData.set(8, monthData.get(8) + 1);
            else if(allDates[i].contains("Oct") && allDates[i].contains(year))
                monthData.set(9, monthData.get(9) + 1);
            else if(allDates[i].contains("Nov") && allDates[i].contains(year))
                monthData.set(10, monthData.get(10) + 1);
            else if(allDates[i].contains("Dec") && allDates[i].contains(year))
                monthData.set(11, monthData.get(11) + 1);
        }

        return monthData;
    }

    public LineDataSet getSet(){
        return dataSet;
    }

    /**
     * Makes the labels for the xAxis. 3 cases, year difference of more than 1.
     * difference of 1. difference of 0.
     * @param xAxis
     */
    public void formatXAxis(XAxis xAxis){
        /*
        String[] months = {"2017", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new XAxisFormatter(months));
        xAxis.setLabelCount(12, true);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
*/
        String firstMonth = DaterActivity.monthsArray[startMonthInt - 1];
        String lastMonth = DaterActivity.monthsArray[endMonthInt - 1];
        ArrayList<String> xFormat = new ArrayList<String>();
        xFormat.add(firstMonth);
        int counter = getNumberMonths() - 1;// for counting purposes. only testing
        if(endYear - startYear >= 2) {//full year between starting year and ending year
            for (int i = startMonthInt + 1; i <= 12; i++) {
                xFormat.add("");
                counter--;
            }
            int j;
            for (j = startYear + 1; j < endYear; j++) {
                xFormat.add("'" + (j - 2000));
                counter--;
                for (int i = 0; i < 11; i++) {
                    xFormat.add("");
                    counter--;
                }

            }
            xFormat.add("'" + (j - 2000));
            counter--;
            for (int k = 1; k < endMonthInt - 1; k++) {
                xFormat.add("");
                counter--;
            }
            xFormat.add(lastMonth);
        }
        else if(endYear - startYear == 0){//both months in same year
            for(int i = startMonthInt + 1; i < endMonthInt; i++){
                xFormat.add(DaterActivity.monthsArray[i - 1]);
            }
            xFormat.add(lastMonth);
        }
        else{//starting year and end year back to back
            for(int i = startMonthInt + 1; i <= 12; i++) {
                if(i % 2 == 1)
                    xFormat.add(DaterActivity.monthsArray[i - 1]);
                else
                    xFormat.add("");
            }
            xFormat.add("'" + (endYear - 2000));
            for(int i = 2; i < endMonthInt; i++) {
                if(i % 2 == 1)
                    xFormat.add(DaterActivity.monthsArray[i - 1]);
                else
                    xFormat.add("");
            }
            if(endMonthInt % 2 == 1)
                xFormat.add(lastMonth);
            else
                xFormat.add("");

        }

        counter--;
        Log.d("counter", "formatXAxis: " + counter);
        int labelCount = getNumberMonths();
        xAxis.setLabelCount(labelCount);
        xAxis.setValueFormatter(new XAxisFormatter(xFormat.toArray(new String[0])));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    /**
     *
     * @return the total number of months in the data set
     */
    private int getNumberMonths(){
        int dif = endYear - startYear;
        if(dif == 0){
            return endMonthInt - startMonthInt;
        }
        else if(dif == 1){
            return (12 - startMonthInt) + endMonthInt;
        }
        else{
            return (12 - startMonthInt) + endMonthInt + 12*(endYear - startYear - 1);
        }
    }
}