package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class XAxisFormatter implements IAxisValueFormatter {
    private String[] months;

    public XAxisFormatter(String[] values) {
        this.months = values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        // "value" represents the position of the label on the axis (x or y)
        return months[(int) value];
    }

    /** this is only needed if numbers are returned, else return 0 */

    public int getDecimalDigits() { return 0; }
}

