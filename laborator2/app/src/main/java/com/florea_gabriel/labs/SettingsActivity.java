package com.florea_gabriel.labs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    EditText editTextDimensiune;
    RadioGroup radioGroupCulori;
    RadioButton radioRosu, radioVerde, radioAlbastru;
    Button buttonSalveazaSetari;

    public static final String PREFS_NAME = "settings_prefs";
    public static final String KEY_TEXT_SIZE = "text_size";
    public static final String KEY_TEXT_COLOR = "text_color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        editTextDimensiune = findViewById(R.id.editTextDimensiune);
        radioGroupCulori = findViewById(R.id.radioGroupCulori);
        radioRosu = findViewById(R.id.radioRosu);
        radioVerde = findViewById(R.id.radioVerde);
        radioAlbastru = findViewById(R.id.radioAlbastru);
        buttonSalveazaSetari = findViewById(R.id.buttonSalveazaSetari);

        incarcaSetari();

        buttonSalveazaSetari.setOnClickListener(v -> {
            salveazaSetari();
        });
    }

    private void salveazaSetari() {
        String dimText = editTextDimensiune.getText().toString().trim();

        int textSize = 16;
        if (!dimText.isEmpty()) {
            textSize = Integer.parseInt(dimText);
        }

        String color = "blue";
        int checkedId = radioGroupCulori.getCheckedRadioButtonId();

        if (checkedId == R.id.radioRosu) {
            color = "red";
        } else if (checkedId == R.id.radioVerde) {
            color = "green";
        } else if (checkedId == R.id.radioAlbastru) {
            color = "blue";
        }

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(KEY_TEXT_SIZE, textSize);
        editor.putString(KEY_TEXT_COLOR, color);
        editor.apply();

        Toast.makeText(this, "Setările au fost salvate.", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void incarcaSetari() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        int textSize = preferences.getInt(KEY_TEXT_SIZE, 16);
        String color = preferences.getString(KEY_TEXT_COLOR, "blue");

        editTextDimensiune.setText(String.valueOf(textSize));

        if ("red".equals(color)) {
            radioRosu.setChecked(true);
        } else if ("green".equals(color)) {
            radioVerde.setChecked(true);
        } else {
            radioAlbastru.setChecked(true);
        }
    }
}
