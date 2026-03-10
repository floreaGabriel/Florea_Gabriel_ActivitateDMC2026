package com.florea_gabriel.labs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PrelucrareDateLab4Activity extends AppCompatActivity {

    EditText editTextName, editTextProfit;
    RadioButton radioButtonFalimentDa, radioButtonFalimentNu;
    CheckBox checkBox4, checkBox5, checkBox6;
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prelucrare_date_lab4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextName = findViewById(R.id.editTextName);
        editTextProfit = findViewById(R.id.editTextNumber2);

        radioButtonFalimentDa = findViewById(R.id.radioButtonFalimentDa);
        radioButtonFalimentNu = findViewById(R.id.radioButtonFalimentNu);

        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);

        button5 = findViewById(R.id.button5);

        button5.setOnClickListener(v -> {
            String nume = editTextName.getText().toString();
            String profitText = editTextProfit.getText().toString();

            boolean faliment = radioButtonFalimentDa.isChecked();

            int profit = 0;
            if (!profitText.isEmpty()) {
                profit = Integer.parseInt(profitText);
            }

            GFMagazin.TipMagazin tipMagazin = null;

            if (checkBox4.isChecked()) {
                tipMagazin = GFMagazin.TipMagazin.MEDICAL;
            } else if (checkBox5.isChecked()) {
                tipMagazin = GFMagazin.TipMagazin.ELECTRONICS;
            } else if (checkBox6.isChecked()) {
                tipMagazin = GFMagazin.TipMagazin.COMPUTERS;
            }

            GFMagazin magazin = new GFMagazin(nume, faliment, profit, tipMagazin);

            Intent intent = new Intent();
            intent.putExtra("magazin", magazin);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}