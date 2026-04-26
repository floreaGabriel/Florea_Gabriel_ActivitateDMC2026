package com.florea_gabriel.labs;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Lab8RoomActivity extends AppCompatActivity {

    MagazinDatabase db;
    MagazinDao dao;

    EditText editNume, editProfit, editTip;
    CheckBox checkFaliment;
    EditText editProfitMin, editProfitMax;
    EditText editCautareNume;
    EditText editProfitStergere;
    EditText editLitera;
    ListView listViewRoom;
    ArrayAdapter<String> adapter;
    List<String> listaAfisare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab8_room);

        db = MagazinDatabase.getInstance(this);
        dao = db.magazinDao();

        editNume = findViewById(R.id.editNume);
        editProfit = findViewById(R.id.editProfit);
        editTip = findViewById(R.id.editTip);
        checkFaliment = findViewById(R.id.checkFaliment);
        editProfitMin = findViewById(R.id.editProfitMin);
        editProfitMax = findViewById(R.id.editProfitMax);
        editCautareNume = findViewById(R.id.editCautareNume);
        editProfitStergere = findViewById(R.id.editProfitStergere);
        editLitera = findViewById(R.id.editLitera);
        listViewRoom = findViewById(R.id.listViewRoom);

        listaAfisare = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaAfisare);
        listViewRoom.setAdapter(adapter);

        Button btnInsert = findViewById(R.id.btnInsert);
        Button btnAfisareTot = findViewById(R.id.btnAfisareTot);
        Button btnFiltreazaProfit = findViewById(R.id.btnFiltreazaProfit);
        Button btnCautareNume = findViewById(R.id.btnCautareNume);
        Button btnSterge = findViewById(R.id.btnSterge);
        Button btnIncrementProfit = findViewById(R.id.btnIncrementProfit);

        // Metoda 1 - Inserare
        btnInsert.setOnClickListener(v -> {
            String nume = editNume.getText().toString().trim();
            String profitStr = editProfit.getText().toString().trim();
            String tip = editTip.getText().toString().trim().toUpperCase();
            boolean faliment = checkFaliment.isChecked();

            if (nume.isEmpty() || profitStr.isEmpty() || tip.isEmpty()) {
                Toast.makeText(this, "Completeaza toate campurile!", Toast.LENGTH_SHORT).show();
                return;
            }

            int profit = Integer.parseInt(profitStr);
            MagazinEntity entity = new MagazinEntity(nume, faliment, profit, tip);
            dao.insert(entity);
            Toast.makeText(this, "Magazin adaugat in BD!", Toast.LENGTH_SHORT).show();
            editNume.setText("");
            editProfit.setText("");
            editTip.setText("");
            checkFaliment.setChecked(false);
            afiseazaLista(dao.getAll());
        });

        // Metoda 2 - Afiseaza toate
        btnAfisareTot.setOnClickListener(v -> afiseazaLista(dao.getAll()));

        // Metoda 4 - Filtrare dupa profit interval
        btnFiltreazaProfit.setOnClickListener(v -> {
            String minStr = editProfitMin.getText().toString().trim();
            String maxStr = editProfitMax.getText().toString().trim();
            if (minStr.isEmpty() || maxStr.isEmpty()) {
                Toast.makeText(this, "Introdu profit min si max!", Toast.LENGTH_SHORT).show();
                return;
            }
            int min = Integer.parseInt(minStr);
            int max = Integer.parseInt(maxStr);
            afiseazaLista(dao.getByProfitInterval(min, max));
        });

        // Metoda 3 - Cautare dupa nume
        btnCautareNume.setOnClickListener(v -> {
            String nume = editCautareNume.getText().toString().trim();
            if (nume.isEmpty()) {
                Toast.makeText(this, "Introdu un nume!", Toast.LENGTH_SHORT).show();
                return;
            }
            afiseazaLista(dao.getByNume(nume));
        });

        // Metoda 5 - Stergere dupa profit
        btnSterge.setOnClickListener(v -> {
            String profitStr = editProfitStergere.getText().toString().trim();
            if (profitStr.isEmpty()) {
                Toast.makeText(this, "Introdu valoarea profitului!", Toast.LENGTH_SHORT).show();
                return;
            }
            int profit = Integer.parseInt(profitStr);
            dao.deleteWhereProfit(profit);
            Toast.makeText(this, "Stergere efectuata!", Toast.LENGTH_SHORT).show();
            afiseazaLista(dao.getAll());
        });

        // Metoda 6 - Increment profit dupa litera
        btnIncrementProfit.setOnClickListener(v -> {
            String litera = editLitera.getText().toString().trim();
            if (litera.isEmpty()) {
                Toast.makeText(this, "Introdu o litera!", Toast.LENGTH_SHORT).show();
                return;
            }
            dao.incrementProfitByLitera(litera);
            Toast.makeText(this, "Profit crescut cu 1 pentru magazine ce incep cu '" + litera + "'", Toast.LENGTH_SHORT).show();
            afiseazaLista(dao.getAll());
        });

        afiseazaLista(dao.getAll());
    }

    private void afiseazaLista(List<MagazinEntity> lista) {
        listaAfisare.clear();
        if (lista.isEmpty()) {
            listaAfisare.add("Nu exista inregistrari.");
        } else {
            for (MagazinEntity m : lista) {
                listaAfisare.add(m.toString());
            }
        }
        adapter.notifyDataSetChanged();
    }
}
