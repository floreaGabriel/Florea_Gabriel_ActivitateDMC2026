package com.florea_gabriel.labs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Lab10Activity extends AppCompatActivity {

    private static final String API_KEY = "";
    private EditText editTextCity;
    private Button buttonSearch;
    private TextView textViewResult;
    private Spinner spinnerDays;

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab10);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextCity = findViewById(R.id.editTextCity);
        buttonSearch = findViewById(R.id.buttonSearch);
        textViewResult = findViewById(R.id.textViewResult);
        spinnerDays = findViewById(R.id.spinnerDays);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.days_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDays.setAdapter(adapter);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = editTextCity.getText().toString().trim();
                if (city.isEmpty()) {
                    Toast.makeText(Lab10Activity.this,
                            "Introduceti un oras", Toast.LENGTH_SHORT).show();
                    return;
                }
                textViewResult.setText("Se incarca...");
                new CitySearchTask().execute(city);
            }
        });
    }

    private class CitySearchTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String cityName = params[0];
            String url = "http://dataservice.accuweather.com/locations/v1/cities/search"
                    + "?apikey=" + API_KEY
                    + "&q=" + cityName.replace(" ", "%20");
            try {
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful() || response.body() == null) {
                    return "ERROR:HTTP " + response.code();
                }
                String json = response.body().string();
                JSONArray array = new JSONArray(json);
                if (array.length() == 0) {
                    return "ERROR:Oras negasit";
                }
                JSONObject first = array.getJSONObject(0);
                return first.getString("Key");
            } catch (Exception e) {
                return "ERROR:" + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.startsWith("ERROR:")) {
                textViewResult.setText(result.substring(6));
                return;
            }
            textViewResult.setText("Cod oras: " + result);

            int pos = spinnerDays.getSelectedItemPosition();
            String endpoint;
            if (pos == 0) endpoint = "1day";
            else if (pos == 1) endpoint = "5day";
            else endpoint = "10day";

            new ForecastTask(endpoint, result).execute();
        }
    }

    private class ForecastTask extends AsyncTask<Void, Void, String> {

        private final String endpoint; // "1day", "5day", "10day"
        private final String cityKey;

        ForecastTask(String endpoint, String cityKey) {
            this.endpoint = endpoint;
            this.cityKey = cityKey;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String url = "http://dataservice.accuweather.com/forecasts/v1/daily/"
                    + endpoint + "/" + cityKey
                    + "?apikey=" + API_KEY
                    + "&metric=true";
            try {
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful() || response.body() == null) {
                    return "ERROR:HTTP " + response.code();
                }
                String json = response.body().string();

                JSONObject root = new JSONObject(json);
                JSONArray forecasts = root.getJSONArray("DailyForecasts");

                StringBuilder sb = new StringBuilder();
                sb.append("Cod oras: ").append(cityKey).append("\n\n");

                for (int i = 0; i < forecasts.length(); i++) {
                    JSONObject day = forecasts.getJSONObject(i);
                    String date = day.getString("Date").substring(0, 10);
                    JSONObject temp = day.getJSONObject("Temperature");
                    double min = temp.getJSONObject("Minimum").getDouble("Value");
                    double max = temp.getJSONObject("Maximum").getDouble("Value");

                    sb.append("Ziua ").append(i + 1).append(" (").append(date).append(")\n");
                    sb.append("  Min: ").append(min).append("°C\n");
                    sb.append("  Max: ").append(max).append("°C\n\n");
                }
                return sb.toString();
            } catch (IOException | org.json.JSONException e) {
                return "ERROR:" + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.startsWith("ERROR:")) {
                textViewResult.setText("Eroare forecast: " + result.substring(6));
            } else {
                textViewResult.setText(result);
            }
        }
    }
}