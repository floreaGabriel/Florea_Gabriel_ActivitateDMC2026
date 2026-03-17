package com.florea_gabriel.labs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ActivitateLab4 extends AppCompatActivity {

    ArrayList<GFMagazin> magazine;
    ListView listViewMagazine;
    ArrayAdapter<GFMagazin> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_lab4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        magazine = new ArrayList<>();

        Button btnOpenForm = findViewById(R.id.open_form);
        listViewMagazine = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                magazine
        );

        listViewMagazine.setAdapter(adapter);

        btnOpenForm.setOnClickListener(v -> {
            Intent it = new Intent(ActivitateLab4.this, PrelucrareDateLab4Activity.class);
            startActivityForResult(it, 100);
        });

        listViewMagazine.setOnItemClickListener((parent, view, position, id) -> {
            GFMagazin magazinSelectat = magazine.get(position);
            Toast.makeText(this, magazinSelectat.toString(), Toast.LENGTH_SHORT).show();
        });

        listViewMagazine.setOnItemLongClickListener((parent, view, position, id) ->{
            magazine.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Magazin sters.", Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            GFMagazin magazin = (GFMagazin) data.getSerializableExtra("magazin");

            if (magazin != null) {
                magazine.add(magazin);
                adapter.notifyDataSetChanged();
            }
        }
    }
}