package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.controller;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;
import java.util.List;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.R;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.GraphLogic;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.Rat;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.RatFB;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.XAxisFormatter;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        LineChart chart = (LineChart) findViewById(R.id.chart);

        Bundle extras = getIntent().getExtras();
        int startYear = extras.getInt("startYear");
        int startMonth = extras.getInt("startMonth");
        int endYear = extras.getInt("endYear");
        int endMonth = extras.getInt("endMonth");
        GraphLogic gl = new GraphLogic(startYear, startMonth, endYear, endMonth);

        LineData lineData = new LineData(gl.getSet());
        chart.setData(lineData);

        XAxis xAxis = chart.getXAxis();
        gl.formatXAxis(xAxis);

        Description desc = new Description();
        desc.setText("Rats per month");
        chart.setDescription(desc);
        chart.invalidate(); // refresh
    }
}
