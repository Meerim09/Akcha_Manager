package com.meerim.akchamanager.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.meerim.akchamanager.R;
import com.meerim.akchamanager.database.DatabaseHelper;
import com.meerim.akchamanager.database_expense.ExpDatabaseHelper;
import com.meerim.akchamanager.models.ExpenseModel;
import com.meerim.akchamanager.models.IncomeModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class ReportsActivity extends AppCompatActivity {
    BarChart mBarChart;
    BarChart mBarChartExpense;
    ArrayList<BarEntry> arrayList;
    ArrayList<BarEntry> arrayListExp;
    ArrayList<String> keyArrayList;
    ArrayList<String> keyArrayListExp;
    private Float sum;
    private String env;
    TextView txtSumMonthly,txtSumMonthlyb;
    int incomeD = 0;
    int expensesD = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        initViews();
//        getData();
//        GoalsDBHelper goalsDBHelper = GoalsDBHelper.getExpense(getApplicationContext());
        DatabaseHelper income = DatabaseHelper.getDB(getApplicationContext());
        ExpDatabaseHelper expense = ExpDatabaseHelper.getExpense(getApplicationContext());

        List<IncomeModel> incomeList = income.notificationDAO().getAllIncome();
        List<ExpenseModel> expenseList = expense.expenseDAO().getAllExpense();
        arrayList = new ArrayList<>();


        for (int i = 0; i < incomeList.size(); i++) {
            arrayList.add(new BarEntry(i, incomeList.get(i).getAmount()));
            incomeD += incomeList.get(i).getAmount();
        }


        for (int i = 0; i < incomeList.size(); i++) {
            keyArrayList.add(incomeList.get(i).getDate());
//            incomeD += incomeList.get(i).getAmount();
        }
        arrayListExp = new ArrayList<>();


        for (int i = 0; i < expenseList.size(); i++) {
            arrayListExp.add(new BarEntry(i, expenseList.get(i).getAmount()));
            incomeD += expenseList.get(i).getAmount();
        }


        for (int i = 0; i < expenseList.size(); i++) {
            keyArrayListExp.add(expenseList.get(i).getDate());
//            incomeD += incomeList.get(i).getAmount();
        }


        for (int i = 0; i < expenseList.size(); i++) {
            expensesD += expenseList.get(i).getAmount();
        }

        txtSumMonthly.setText(String.valueOf(incomeD));
        txtSumMonthlyb.setText(String.valueOf(expensesD));
        barConfiguration();
        barConfiguration2();


    }

    private void initViews() {
        mBarChart = findViewById(R.id.barchart_month);
        mBarChartExpense = findViewById(R.id.barchart_monthb);
        txtSumMonthly = findViewById(R.id.txtSumMonthly);
        txtSumMonthlyb = findViewById(R.id.txtSumMonthlyb);
        mBarChart.setScaleEnabled(false);
        keyArrayList = new ArrayList<>();
        keyArrayListExp = new ArrayList<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void barConfiguration() {

        mBarChart.setDrawGridBackground(false);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setGridBackgroundColor(Color.WHITE);
        mBarChart.setTouchEnabled(true);
        mBarChart.setPinchZoom(true);
        mBarChart.setDoubleTapToZoomEnabled(true);
        mBarChart.highlightValue(null);
        mBarChart.setDoubleTapToZoomEnabled(false);
        mBarChart.getAxisLeft().setEnabled(false);
        mBarChart.getAxisRight().setEnabled(false);

        if (arrayList == null) {
            getData();
        }
        BarDataSet barDataSet = new BarDataSet(arrayList, "Test");
        barDataSet.setDrawValues(false);

        mBarChart.getAxisLeft().setDrawGridLines(false);
        mBarChart.getXAxis().setDrawGridLines(false);
        XAxis xAxis = mBarChart.getXAxis();

        xAxis.setAxisLineColor(getResources().getColor(R.color.transparent));

        mBarChart.setDescription(null);    // Hide the description
        mBarChart.getAxisLeft().setDrawLabels(true);
        mBarChart.getAxisRight().setDrawLabels(true);
        mBarChart.getLegend().setEnabled(false);

        BarData barData = new BarData(barDataSet);
        mBarChart.setData(barData);
        barDataSet.setValueTextColor(Color.parseColor("#ebecf0"));
        barDataSet.setColor(Color.parseColor("#114E25"));

        mBarChart.getXAxis().setTextColor(R.color.dark_grey);
        barData.setBarWidth(0.4f);
        mBarChart.getXAxis().setSpaceMax(0.1f);


        final ArrayList<String> xVals = new ArrayList<>();
        if (keyArrayList != null) {
            for (int i = 0; i < keyArrayList.size(); i++) {
                String[] date = keyArrayList.get(i).split("/");
                xVals.add(date[0]);
            }
        } else {
            xVals.add("1");
            xVals.add("2");
            xVals.add("3");
            xVals.add("4");
            xVals.add("5");
            xVals.add("6");
            xVals.add("7");
            xVals.add("8");
            xVals.add("9");
            xVals.add("10");

            xVals.add("11");
            xVals.add("12");
            xVals.add("13");
            xVals.add("14");
            xVals.add("15");
            xVals.add("16");
            xVals.add("17");
            xVals.add("18");
            xVals.add("19");
            xVals.add("20");

            xVals.add("21");
            xVals.add("22");
            xVals.add("23");
            xVals.add("24");
            xVals.add("25");
            xVals.add("26");
            xVals.add("27");
            xVals.add("28");
        }

        xAxis.setCenterAxisLabels(false);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(xVals.size());
        xAxis.setLabelCount(xVals.size() );
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));

//        CustomMarkerView2 mv = new CustomMarkerView2(getContext(), R.layout.mileage_marker, keyArrayList, "f");
//        mBarChart.setMarkerView(mv);
    }
    private void barConfiguration2() {

        mBarChartExpense.setDrawGridBackground(false);
        mBarChartExpense.setDrawBarShadow(false);
        mBarChartExpense.setGridBackgroundColor(Color.WHITE);
        mBarChartExpense.setTouchEnabled(true);
        mBarChartExpense.setPinchZoom(true);
        mBarChartExpense.setDoubleTapToZoomEnabled(true);
        mBarChartExpense.highlightValue(null);
        mBarChartExpense.setDoubleTapToZoomEnabled(false);
        mBarChartExpense.getAxisLeft().setEnabled(false);
        mBarChartExpense.getAxisRight().setEnabled(false);

        if (arrayList == null) {
            getData2();
        }
        BarDataSet barDataSet = new BarDataSet(arrayListExp, "Test");
        barDataSet.setDrawValues(false);

        mBarChartExpense.getAxisLeft().setDrawGridLines(false);
        mBarChartExpense.getXAxis().setDrawGridLines(false);
        XAxis xAxis = mBarChartExpense.getXAxis();

        xAxis.setAxisLineColor(getResources().getColor(R.color.transparent));

        mBarChartExpense.setDescription(null);    // Hide the description
        mBarChartExpense.getAxisLeft().setDrawLabels(true);
        mBarChartExpense.getAxisRight().setDrawLabels(true);
        mBarChartExpense.getLegend().setEnabled(false);

        BarData barData = new BarData(barDataSet);
        mBarChartExpense.setData(barData);
        barDataSet.setValueTextColor(Color.parseColor("#ebecf0"));
        barDataSet.setColor(Color.parseColor("#114E25"));

        mBarChartExpense.getXAxis().setTextColor(R.color.dark_grey);
        barData.setBarWidth(0.4f);
        mBarChartExpense.getXAxis().setSpaceMax(0.1f);


        final ArrayList<String> xVals = new ArrayList<>();
        if (keyArrayListExp != null) {
            for (int i = 0; i < keyArrayListExp.size(); i++) {
                String[] date = keyArrayListExp.get(i).split("/");
                xVals.add(date[0]);
            }
        } else {
            xVals.add("1");
            xVals.add("2");
            xVals.add("3");
            xVals.add("4");
            xVals.add("5");
            xVals.add("6");
            xVals.add("7");
            xVals.add("8");
            xVals.add("9");
            xVals.add("10");

            xVals.add("11");
            xVals.add("12");
            xVals.add("13");
            xVals.add("14");
            xVals.add("15");
            xVals.add("16");
            xVals.add("17");
            xVals.add("18");
            xVals.add("19");
            xVals.add("20");

            xVals.add("21");
            xVals.add("22");
            xVals.add("23");
            xVals.add("24");
            xVals.add("25");
            xVals.add("26");
            xVals.add("27");
            xVals.add("28");
        }

        xAxis.setCenterAxisLabels(false);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(xVals.size());
        xAxis.setLabelCount(xVals.size() );
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));

//        CustomMarkerView2 mv = new CustomMarkerView2(getContext(), R.layout.mileage_marker, keyArrayList, "f");
//        mBarChart.setMarkerView(mv);
    }

    private void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new BarEntry(0, 0));
        arrayList.add(new BarEntry(1, 0));
        arrayList.add(new BarEntry(2, 0));
        arrayList.add(new BarEntry(3, 0));
        arrayList.add(new BarEntry(4, 0));
        arrayList.add(new BarEntry(5, 0));
        arrayList.add(new BarEntry(6, 0));


        arrayList.add(new BarEntry(7, 0));
        arrayList.add(new BarEntry(8, 0));
        arrayList.add(new BarEntry(9, 0));
        arrayList.add(new BarEntry(10, 0));
        arrayList.add(new BarEntry(11, 0));
        arrayList.add(new BarEntry(12, 0));
        arrayList.add(new BarEntry(13, 0));


        arrayList.add(new BarEntry(14, 0));
        arrayList.add(new BarEntry(15, 0));
        arrayList.add(new BarEntry(16, 0));
        arrayList.add(new BarEntry(17, 0));
        arrayList.add(new BarEntry(18, 0));
        arrayList.add(new BarEntry(19, 0));
        arrayList.add(new BarEntry(20, 0));


        arrayList.add(new BarEntry(21, 0));
        arrayList.add(new BarEntry(22, 0));
        arrayList.add(new BarEntry(23, 0));
        arrayList.add(new BarEntry(24, 0));
        arrayList.add(new BarEntry(25, 0));
        arrayList.add(new BarEntry(26, 0));


        arrayList.add(new BarEntry(27, 0));

    }
    private void getData2() {
        arrayListExp = new ArrayList<>();
        arrayListExp.add(new BarEntry(0, 0));
        arrayListExp.add(new BarEntry(1, 0));
        arrayListExp.add(new BarEntry(2, 0));
        arrayListExp.add(new BarEntry(3, 0));
        arrayListExp.add(new BarEntry(4, 0));
        arrayListExp.add(new BarEntry(5, 0));
        arrayListExp.add(new BarEntry(6, 0));


        arrayListExp.add(new BarEntry(7, 0));
        arrayListExp.add(new BarEntry(8, 0));
        arrayListExp.add(new BarEntry(9, 0));
        arrayListExp.add(new BarEntry(10, 0));
        arrayListExp.add(new BarEntry(11, 0));
        arrayListExp.add(new BarEntry(12, 0));
        arrayListExp.add(new BarEntry(13, 0));


        arrayListExp.add(new BarEntry(14, 0));
        arrayListExp.add(new BarEntry(15, 0));
        arrayListExp.add(new BarEntry(16, 0));
        arrayListExp.add(new BarEntry(17, 0));
        arrayListExp.add(new BarEntry(18, 0));
        arrayListExp.add(new BarEntry(19, 0));
        arrayListExp.add(new BarEntry(20, 0));


        arrayListExp.add(new BarEntry(21, 0));
        arrayListExp.add(new BarEntry(22, 0));
        arrayListExp.add(new BarEntry(23, 0));
        arrayListExp.add(new BarEntry(24, 0));
        arrayListExp.add(new BarEntry(25, 0));
        arrayListExp.add(new BarEntry(26, 0));


        arrayListExp.add(new BarEntry(27, 0));

    }
}