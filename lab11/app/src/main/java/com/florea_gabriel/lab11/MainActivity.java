package com.florea_gabriel.lab11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText text_introdus = findViewById(R.id.editTextText);
        Button btn_grafic = findViewById(R.id.button);

        btn_grafic.setOnClickListener(v -> {
            String input = text_introdus.getText().toString().trim();
            String[] parts = input.split(",");
            float[] values = new float[parts.length];
            for (int i = 0; i < parts.length; i++) {
                values[i] = Float.parseFloat(parts[i].trim());
            }

            Bundle bundle = new Bundle();
            bundle.putFloatArray("values", values);
            Intent it = new Intent(MainActivity.this, grafic_activity.class);
            it.putExtras(bundle);
            startActivity(it);
        });

    }
}