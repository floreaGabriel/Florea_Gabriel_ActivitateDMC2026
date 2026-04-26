package com.florea_gabriel.labs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Lab9ImageActivity extends AppCompatActivity {

    private ListView listViewImagini;
    private TextView txtStatus;
    private MagazinImagineAdapter adapter;
    private List<MagazinImagine> listaImagini;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab9_imagini);

        listViewImagini = findViewById(R.id.listViewImagini);
        txtStatus = findViewById(R.id.txtStatus);

        listaImagini = new ArrayList<>();
        listaImagini.add(new MagazinImagine(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Gare_du_Nord_-_Primark.jpg/1280px-Gare_du_Nord_-_Primark.jpg",
                "Primark - Magazin de moda accesibila cu mii de produse la preturi mici",
                "https://www.primark.com"
        ));
        listaImagini.add(new MagazinImagine(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Apple_Store_-_Lincoln_Park_%288055378725%29.jpg/1280px-Apple_Store_-_Lincoln_Park_%288055378725%29.jpg",
                "Apple Store - Magazin oficial Apple cu produse electronice de top",
                "https://www.apple.com/retail/"
        ));
        listaImagini.add(new MagazinImagine(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/Ikea_logo.svg/800px-Ikea_logo.svg.png",
                "IKEA - Lant international de mobila si obiecte de uz casnic",
                "https://www.ikea.com"
        ));
        listaImagini.add(new MagazinImagine(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Walmart_logo.svg/1024px-Walmart_logo.svg.png",
                "Walmart - Cel mai mare retailer din lume cu milioane de produse",
                "https://www.walmart.com"
        ));
        listaImagini.add(new MagazinImagine(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Zara_Logo.svg/1280px-Zara_Logo.svg.png",
                "Zara - Brand de moda spaniol cunoscut la nivel mondial",
                "https://www.zara.com"
        ));

        adapter = new MagazinImagineAdapter(this, listaImagini);
        listViewImagini.setAdapter(adapter);

        listViewImagini.setOnItemClickListener((parent, view, position, id) -> {
            MagazinImagine item = listaImagini.get(position);
            Intent intent = new Intent(Lab9ImageActivity.this, WebViewActivity.class);
            intent.putExtra("url", item.getWebUrl());
            intent.putExtra("titlu", item.getDescriere());
            startActivity(intent);
        });

        incarcaImagini();
    }

    private void incarcaImagini() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        AtomicInteger incarcate = new AtomicInteger(0);
        int total = listaImagini.size();

        for (int i = 0; i < total; i++) {
            final int index = i;
            final MagazinImagine item = listaImagini.get(i);

            executor.execute(() -> {
                Bitmap bitmap = descarcaImagine(item.getImageUrl());
                mainHandler.post(() -> {
                    if (bitmap != null) {
                        item.setBitmap(bitmap);
                    }
                    int nr = incarcate.incrementAndGet();
                    txtStatus.setText("Incarcate: " + nr + "/" + total);
                    adapter.notifyDataSetChanged();
                    if (nr == total) {
                        txtStatus.setText("Toate imaginile incarcate!");
                    }
                });
            });
        }

        executor.shutdown();
    }

    private Bitmap descarcaImagine(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
