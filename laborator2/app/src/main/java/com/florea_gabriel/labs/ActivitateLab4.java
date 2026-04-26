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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

public class ActivitateLab4 extends AppCompatActivity {

    ArrayList<GFMagazin> magazine;
    ListView listViewMagazine;
    MagazinAdapter adapter;
    public static final int REQUEST_ADD = 100;
    public static final int REQUEST_EDIT = 200;
    private static final String FAVORITES_FILE = "favorite.txt";

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
        Button btnSettings = findViewById(R.id.buttonSettings);
        Button btnLab5Room = findViewById(R.id.buttonLab5Room);

        adapter = new MagazinAdapter(this, magazine);

        listViewMagazine.setAdapter(adapter);
        incarcaMagazineDinFisier();
        adapter.notifyDataSetChanged();
        btnOpenForm.setOnClickListener(v -> {
            Intent it = new Intent(ActivitateLab4.this, PrelucrareDateLab4Activity.class);
            startActivityForResult(it, REQUEST_ADD);
        });

        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(ActivitateLab4.this, SettingsActivity.class);
            startActivity(intent);
        });

        btnLab5Room.setOnClickListener(v -> {
            Intent intent = new Intent(ActivitateLab4.this, Lab5RoomActivity.class);
            startActivity(intent);
        });

        listViewMagazine.setOnItemClickListener((parent, view, position, id) -> {
            GFMagazin magazinSelectat = magazine.get(position);

            Intent intent = new Intent(ActivitateLab4.this, PrelucrareDateLab4Activity.class);
            intent.putExtra("magazin", magazinSelectat);
            intent.putExtra("position", position);
            startActivityForResult(intent, REQUEST_EDIT);
        });

        listViewMagazine.setOnItemLongClickListener((parent, view, position, id) ->{
            GFMagazin magazinFavorit = magazine.get(position);
            salveazaFavoritInFisier(magazinFavorit);
            Toast.makeText(this, "Magazin salvat la favorite.", Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            GFMagazin magazin = data.getParcelableExtra("magazin");

            if (requestCode == REQUEST_ADD) {
                if (magazin != null) {
                    magazine.add(magazin);
                    adapter.notifyDataSetChanged();
                }
            } else if (requestCode == REQUEST_EDIT) {
                int position = data.getIntExtra("position", -1);

                if (magazin != null && position != -1) {
                    magazine.set(position, magazin);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void salveazaFavoritInFisier(GFMagazin magazin) {
        String text = magazin.toString() + "\n********************\n";

        try (FileOutputStream fos = openFileOutput(FAVORITES_FILE, MODE_APPEND)) {
            fos.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void incarcaMagazineDinFisier() {
        try (FileInputStream fis = openFileInput("magazine.txt");
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {

            String linie;
            while ((linie = br.readLine()) != null) {
                String[] parti = linie.split(";");

                if (parti.length == 5) {
                    String nume = parti[0];
                    boolean faliment = Boolean.parseBoolean(parti[1]);
                    int profit = Integer.parseInt(parti[2]);
                    GFMagazin.TipMagazin tipMagazin = GFMagazin.TipMagazin.valueOf(parti[3]);
                    long timestamp = Long.parseLong(parti[4]);
                    Date data = new Date(timestamp);

                    GFMagazin magazin = new GFMagazin(nume, faliment, profit, tipMagazin, data);
                    magazine.add(magazin);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}