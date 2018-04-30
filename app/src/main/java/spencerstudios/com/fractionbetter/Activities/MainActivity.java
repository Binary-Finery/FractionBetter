package spencerstudios.com.fractionbetter.Activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import spencerstudios.com.fractionbetter.R;
import spencerstudios.com.fractionbetter.Utilities.ColorBuilder;
import spencerstudios.com.fractionbetter.Utilities.LabelBuilder;

public class MainActivity extends AppCompatActivity {

    int pieChartToEdit = 1;
    private PieChart pieChartOne, pieChartTwo;
    private TextView tvPieOne, tvPieTwo;
    private TextView tvPieOnePercent, tvPieTwoPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
        setPieChartConstants(pieChartOne, pieChartTwo);
        initPieChartDefaults();
    }

    private void setPieChartConstants(PieChart... pieChart) {

        for (PieChart aPieChart : pieChart) {
            aPieChart.setDrawHoleEnabled(false);
            aPieChart.getDescription().setEnabled(false);
            aPieChart.getLegend().setEnabled(false);
        }
    }

    private boolean checkValueValidity(String t, String p) {
        if (t.isEmpty() || p.isEmpty()) {
            return false;
        }
        int nTotal = Integer.parseInt(t), nParts = Integer.parseInt(p);
        return !(nTotal < 1 || nTotal > 25) && !(nParts > nTotal);
    }

    public void clickEvent(View view) {
        int id = view.getId();

        if (id == R.id.iv_edit_pie_one) {
            displayEditValuesDialog(1);
        } else if (id == R.id.iv_edit_pie_two) {
            displayEditValuesDialog(2);
        } else if (id == R.id.iv_next_pie_one || id == R.id.iv_next_pie_two) {
            String[] fraction = id == R.id.iv_next_pie_one ? tvPieOne.getText().toString().split("/") : tvPieTwo.getText().toString().split("/");
            int parts = Integer.parseInt(fraction[0]);
            int total = Integer.parseInt(fraction[1]);
            Intent i = new Intent(MainActivity.this, EquivalentsActivity.class);
            i.putExtra("parts", parts);
            i.putExtra("total", total);
            startActivity(i);
        }
    }

    private void initPieChartDefaults() {
        initPieChart(pieChartOne, 3, 2, 1);
        initPieChart(pieChartTwo, 4, 1, 2);
    }

    private void initViews() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        tvPieOne = findViewById(R.id.tv_pie_one);
        tvPieTwo = findViewById(R.id.tv_pie_two);
        tvPieOnePercent = findViewById(R.id.tv_pie_one_percent);
        tvPieTwoPercent = findViewById(R.id.tv_pie_two_percent);

        pieChartOne = findViewById(R.id.pie_chart);
        pieChartTwo = findViewById(R.id.pie_chart2);
    }

    private void initPieChart(PieChart pc, int total, int parts, int pieSelected) {

        double per = ((double) parts / (double) total) * 100.0;
        double dec = (double) parts / (double) total;

        if (pieSelected == 1) {
            tvPieOne.setText(Html.fromHtml("<sup>" + parts + "</sup>/<sub>" + total + "</sub>"));
            tvPieOnePercent.setText(String.format(Locale.getDefault(), "%.2f%%     %.2f", per, dec));
        } else {
            tvPieTwo.setText(Html.fromHtml("<sup>" + parts + "</sup>/<sub>" + total + "</sub>"));
            tvPieTwoPercent.setText(String.format(Locale.getDefault(), "%.2f%%     %.2f", per, dec));
        }

        int[] sliceColors = ColorBuilder.build(total, parts);
        String[] fraction = LabelBuilder.build(total, parts);

        List<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < total; i++) {
            entries.add(new PieEntry(1.0f, fraction[i]));
        }

        PieDataSet set = new PieDataSet(entries, "");

        set.setColors(sliceColors);
        set.setSliceSpace(.5f);
        set.setDrawValues(false);

        PieData data = new PieData(set);
        pc.setData(data);
        pc.animateX(750, Easing.EasingOption.EaseInOutCirc);
        pc.invalidate();
    }

    private void displayEditValuesDialog(final int pieSelected) {

        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams")
        View valuesDialog = inflater.inflate(R.layout.values_dialog, null);

        final TextView title = valuesDialog.findViewById(R.id.tv_dialog_title);
        final EditText etTotal = valuesDialog.findViewById(R.id.et_total);
        final EditText etParts = valuesDialog.findViewById(R.id.et_parts);

        final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setView(valuesDialog);

        title.setText(pieSelected == 1 ? "Top Pie Chart" : "Bottom Pie Chart");

        dialog.setPositiveButton("okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (etParts != null) {
                    if (checkValueValidity(etTotal.getText().toString(), etParts.getText().toString())) {
                        int total = Integer.parseInt(etTotal.getText().toString());
                        int parts = Integer.parseInt(etParts.getText().toString());
                        initPieChart(pieSelected == 1 ? pieChartOne : pieChartTwo, total, parts, pieSelected);
                    } else {
                        Toast.makeText(getApplicationContext(), "values are not valid", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "one or more fields has missing information", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog d = dialog.create();
        d.show();
    }
}
