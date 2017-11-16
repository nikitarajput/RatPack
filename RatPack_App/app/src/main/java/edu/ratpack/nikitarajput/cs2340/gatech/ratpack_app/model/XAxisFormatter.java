package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.Arrays;

/**
 * Used by Graph Tool to create XAxis
 */
@SuppressWarnings("WeakerAccess")
public class XAxisFormatter implements IAxisValueFormatter {
    private final String[] months;

    /**
     * sets the months to values
     * @param values the String values which are given by GraphLogic
     */
    XAxisFormatter(String[] values) {
        months = Arrays.copyOf(values, values.length);
    }

    /**
     * Returns the value for each data point
     * @param value the xAxis number
     * @param axis the Axis to format
     * @return The string to replace the value
     */
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        // "value" represents the position of the label on the axis (x or y)
        return months[(int) value];
    }

}

