package com.florea_gabriel.labs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class PrelucrareDateLab4Activity extends AppCompatActivity {

    EditText editTextName, editTextProfit;
    RadioButton radioButtonFalimentDa, radioButtonFalimentNu;
    CheckBox checkBox4, checkBox5, checkBox6;
    Button button5;
    CalendarView calendarView;
    int an, luna, zi;
    TextView textViewTitle, textViewName, textViewFaliment, textViewProfit, textViewProduse, textViewData;

    GFMagazin magazinPrimit = null;
    int pozitieEditare = -1;
    private static final String FILE_NAME = "magazine.txt";

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

        calendarView = findViewById(R.id.calendarView2);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewName = findViewById(R.id.textViewName);
        textViewFaliment = findViewById(R.id.textViewFaliment);
        textViewProfit = findViewById(R.id.textViewProfit);
        textViewProduse = findViewById(R.id.textView9);
        textViewData = findViewById(R.id.textView3);


        Calendar calendar = Calendar.getInstance();
        an = calendar.get(Calendar.YEAR);
        luna = calendar.get(Calendar.MONTH);
        zi = calendar.get(Calendar.DAY_OF_MONTH);

        calendarView.setOnDateChangeListener((view, year, month, day) -> {
            an = year;
            luna = month;
            zi = day;
        });

        Intent intentPrimit = getIntent();

        if (intentPrimit != null && intentPrimit.hasExtra("magazin")) {
            magazinPrimit = intentPrimit.getParcelableExtra("magazin");
            pozitieEditare = intentPrimit.getIntExtra("position", -1);

            if (magazinPrimit != null) {
                completeazaCampuri(magazinPrimit);
            }
        }

        button5 = findViewById(R.id.button5);


        aplicaSetariText();

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

            Calendar calendarSelectat = Calendar.getInstance();
            calendarSelectat.set(an, luna, zi);
            Date dataSelectata = calendarSelectat.getTime();

            GFMagazin magazin = new GFMagazin(nume, faliment, profit, tipMagazin, dataSelectata);

            if (pozitieEditare == -1) {
                salveazaInFisier(magazin);
            }
            Intent intent = new Intent();
            intent.putExtra("magazin", magazin);

            if (pozitieEditare != -1) {
                intent.putExtra("position", pozitieEditare);
            }

            setResult(RESULT_OK, intent);
            finish();
        });
    }

    private void completeazaCampuri(GFMagazin magazin) {
        editTextName.setText(magazin.getNume());
        editTextProfit.setText(String.valueOf(magazin.getProfit()));

        if (magazin.getFaliment()) {
            radioButtonFalimentDa.setChecked(true);
        } else {
            radioButtonFalimentNu.setChecked(true);
        }

        if (magazin.getTipMagazin() == GFMagazin.TipMagazin.MEDICAL) {
            checkBox4.setChecked(true);
        } else if (magazin.getTipMagazin() == GFMagazin.TipMagazin.ELECTRONICS) {
            checkBox5.setChecked(true);
        } else if (magazin.getTipMagazin() == GFMagazin.TipMagazin.COMPUTERS) {
            checkBox6.setChecked(true);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(magazin.getData());

        an = calendar.get(Calendar.YEAR);
        luna = calendar.get(Calendar.MONTH);
        zi = calendar.get(Calendar.DAY_OF_MONTH);

        calendarView.setDate(magazin.getData().getTime(), true, true);
    }

    private void salveazaInFisier(GFMagazin magazin) {
        String text = magazin.getNume() + ";" +
                magazin.getFaliment() + ";" +
                magazin.getProfit() + ";" +
                magazin.getTipMagazin() + ";" +
                magazin.getData().getTime() + "\n";

        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND)) {
            fos.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void aplicaSetariText() {
        SharedPreferences preferences = getSharedPreferences(SettingsActivity.PREFS_NAME, MODE_PRIVATE);

        int textSize = preferences.getInt(SettingsActivity.KEY_TEXT_SIZE, 16);
        String colorName = preferences.getString(SettingsActivity.KEY_TEXT_COLOR, "blue");

        int color = Color.BLUE;
        if ("red".equals(colorName)) {
            color = Color.RED;
        } else if ("green".equals(colorName)) {
            color = Color.GREEN;
        } else if ("blue".equals(colorName)) {
            color = Color.BLUE;
        }

        textViewTitle.setTextSize(textSize);
        textViewName.setTextSize(textSize);
        textViewFaliment.setTextSize(textSize);
        textViewProfit.setTextSize(textSize);
        textViewProduse.setTextSize(textSize);
        textViewData.setTextSize(textSize);
        button5.setTextSize(textSize);

        textViewTitle.setTextColor(color);
        textViewName.setTextColor(color);
        textViewFaliment.setTextColor(color);
        textViewProfit.setTextColor(color);
        textViewProduse.setTextColor(color);
        textViewData.setTextColor(color);
        button5.setTextColor(color);

        radioButtonFalimentDa.setTextColor(color);
        radioButtonFalimentNu.setTextColor(color);
        checkBox4.setTextColor(color);
        checkBox5.setTextColor(color);
        checkBox6.setTextColor(color);

        radioButtonFalimentDa.setTextSize(textSize);
        radioButtonFalimentNu.setTextSize(textSize);
        checkBox4.setTextSize(textSize);
        checkBox5.setTextSize(textSize);
        checkBox6.setTextSize(textSize);
    }

}