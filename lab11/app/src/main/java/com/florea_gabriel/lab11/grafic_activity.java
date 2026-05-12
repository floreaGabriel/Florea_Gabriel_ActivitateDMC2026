package com.florea_gabriel.lab11;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class grafic_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grafic);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        //primire date din main activity bundle prin intent
        Bundle bundle = getIntent().getExtras();
        float[] values = bundle.getFloatArray("values");

        // barchart creare prin clasa paint si canvas
        ChartView chartView = findViewById(R.id.view);
        chartView.setValues(values);

        RadioGroup rgroup = findViewById(R.id.radioGroup);
        rgroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButtonPieChart) {
                chartView.setChartType(1);
            } else if (checkedId == R.id.radioButtonBarChart) {
                chartView.setChartType(0);
            } else if (checkedId == R.id.radioButtonColumnChart) {
                chartView.setChartType(2);
            }
        });

        rgroup.check(R.id.radioButtonBarChart);







    }
}