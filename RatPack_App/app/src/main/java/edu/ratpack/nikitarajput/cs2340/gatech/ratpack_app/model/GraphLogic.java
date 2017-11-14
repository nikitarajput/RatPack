package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;


import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app
        .controller.DaterActivity.monthsArray;

/**
 * Creates entries for data
 */
public final class GraphLogic {


    private final String[] allDates;
    private final LineDataSet dataSet;
    private final int startYear;
    private final int endYear;
    private final int startMonthInt;
    private final int endMonthInt;
    private final int numMonthInYear = 12;

    /**
     * Initializes values of dataSet given the param
     * @param startYear integer year starts
     * @param startMonth first month of first year
     * @param endYear last year
     * @param endMonth last month of last year
     */
    public GraphLogic(int startYear, int startMonth, int endYear, int endMonth){
        Rat[] ratList = RatFB.getAllRats();
        allDates = new String[ratList.length];
        for(int i = 0; i < ratList.length; i++){
            allDates[i] = ratList[i].getDate();
        }

        this.startYear = startYear;
        this.endYear = endYear;
        this.startMonthInt = startMonth;
        this.endMonthInt = endMonth;

        List<ArrayList<Integer>> sumData =
                setData(this.startYear, startMonthInt, this.endYear, endMonthInt);
        List<Entry> entries = new ArrayList<>();

        int l = 0;
        for(int i = 0; i < sumData.size(); i++){
            ArrayList<Integer> year = sumData.get(i);
            for(int j = 0; j < year.size(); j++){
                entries.add(new Entry(l, year.get(j)));
                l++;
            }
        }

        dataSet = new LineDataSet(entries, "Number of Rats"); // add entries to dataSet
    }

    /**
     * Populates setData which will then be used to populate entries
     * @param startYear literally start year
     * @param startMonthInt january is 1
     * @param endYear literally end year
     * @param endMonthInt last month december is 12
     * @return List of year lists which contain data for each month
     */
    public List<ArrayList<Integer>> setData(int startYear,
                                            int startMonthInt, int endYear, int endMonthInt){
        List<ArrayList<Integer>> sumData = new ArrayList<>();
        //this adds data of all moths of each year in range
        for(int i = startYear; i <= endYear; i++){
            sumData.add(getMonthData("" + i));
        }
        //below trims to for year data to start and end at dictated months
        //sets first years first month
        List<Integer> firstYear = sumData.get(0);
        sumData.set(0, new ArrayList<>(firstYear.subList(startMonthInt - 1, numMonthInYear)));
        if (endYear == startYear) {
            //sets last years last month
            List<Integer> lastYear = sumData.get(sumData.size() - 1);
            sumData.set(sumData.size() - 1,
                    new ArrayList<>(lastYear
                            .subList(0, endMonthInt - (startMonthInt - 1))));
        }
        else {
            //sets last years last month
            List<Integer> lastYear = sumData.get(sumData.size() - 1);
            sumData.set(sumData.size() - 1,
                    new ArrayList<>(lastYear.subList(0, endMonthInt)));
        }

        return sumData;
    }

    /**
     * Gets an arrayList for month values for year
     * @param year First three letters of the month
     * @return returns the arrayList of month values
     */
    private ArrayList<Integer> getMonthData(CharSequence year){
        ArrayList<Integer> monthData = new ArrayList<>();
        for(int i = 0; i < numMonthInYear; i++) {
            monthData.add(0);
        }
        for (String allDate : allDates) {
            if (allDate.contains(year)) {
                for (int j = 0; j < numMonthInYear; j++) {
                    if (allDate.contains(monthsArray[j])) {
                        monthData.set(j, monthData.get(j) + 1);
                    }
                }
            }
        }

        return monthData;
    }

    /**
     * Getter for dataSet which has all the numbers for the set
     * @return dataSet which has all the numbers for the set
     */
    public LineDataSet getSet(){
        return dataSet;
    }

    /**
     * Makes the labels for the xAxis. 3 cases, year difference of more than 1.
     * difference of 1. difference of 0.
     * @param xAxis is of Graph widget. Need reference to customize it
     */
    public void formatXAxis(XAxis xAxis){
        ArrayList<String> xFormat = new ArrayList<>();
        addLabels(xFormat);

        int labelCount = getNumberMonths();
        xAxis.setLabelCount(labelCount);
        xAxis.setValueFormatter(new XAxisFormatter(xFormat.toArray(new String[0])));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    /**
     * Adds labels to xFormat
     * @param xFormat List of strings from which the label is made
     * @return xFormat for testing purposes
     */
    private Collection<String> addLabels(Collection<String> xFormat) {
        String firstMonth = monthsArray[startMonthInt - 1];
        String lastMonth = monthsArray[endMonthInt - 1];
        xFormat.add(firstMonth);
        if((endYear - startYear) >= 2) {//full year between starting year and ending year
            for (int i = startMonthInt + 1; i <= numMonthInYear; i++) {
                xFormat.add("");
            }

            fillYears(startYear, endYear, xFormat);

            for (int k = 1; k < (endMonthInt - 1); k++) {
                xFormat.add("");

            }
            xFormat.add(lastMonth);
        }
        else if((endYear - startYear) == 0){//both months in same year
            List<String> monthsList = Arrays.asList(monthsArray);
            xFormat.addAll(monthsList.subList(startMonthInt, endMonthInt));
            //xFormat.add(lastMonth);
        }
        else{//starting year and end year back to back
            for(int i = startMonthInt + 1; i <= numMonthInYear; i++) {
                oddMonth(i, xFormat);
            }
            String lastYearAbr = String.valueOf(endYear);
            xFormat.add("'" + lastYearAbr.substring(2));
            for(int i = 2; i < endMonthInt; i++) {
                oddMonth(i, xFormat);
            }
            oddMonth(endMonthInt, xFormat);


        }
        return xFormat;
    }

    /**
     * Fills in years for when year difference is greater than 2 years
     * Starts at second year. Ends at last year
     * @param startYear year at which data starts
     * @param endYear year at which data ends
     * @param xFormat List of strings from which the label is made
     * @return xFormat for testing purposes
     */
    private Collection<String> fillYears(int startYear, int endYear, Collection<String> xFormat) {
        if(endYear - startYear > 1) {
            int j;
            String yearAbr;
            for (j = startYear + 1; j < endYear; j++) {
                yearAbr = String.valueOf(j);
                xFormat.add("'" + yearAbr.substring(2));

                for (int i = 0; i < (numMonthInYear - 1); i++) {
                    xFormat.add("");
                }
            }
            yearAbr = String.valueOf(j);
            xFormat.add("'" + yearAbr.substring(2));
        }
        return xFormat;
    }

    /**
     * Skips every other month when working in less than 2 years difference
     * @param monthInd index of month starting at 1
     * @param xFormat List of strings from which the label is made
     */
    public void oddMonth(int monthInd, Collection<String> xFormat) {
        if(monthInd > 1 && monthInd <= numMonthInYear) {
            if ((monthInd % 2) == 1) {//every second month
                xFormat.add(monthsArray[monthInd - 1]);
            } else {
                xFormat.add("");
            }
        }
    }
    /**
     * @return the total number of months in the data set
     */
    public int getNumberMonths(){
        int dif = endYear - startYear;
        if (dif == 0) {
            return endMonthInt - startMonthInt;
        }
        else if(dif == 1) {
            return (numMonthInYear - startMonthInt) + endMonthInt;
        }
        else {
            return (numMonthInYear - startMonthInt) +
                    endMonthInt + (numMonthInYear * (endYear - startYear - 1));
        }
    }
}