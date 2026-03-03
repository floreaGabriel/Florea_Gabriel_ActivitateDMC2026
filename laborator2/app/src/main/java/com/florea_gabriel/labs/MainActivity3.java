package com.florea_gabriel.labs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        String msg = bundle.getString("msg");
        int x = bundle.getInt("x");
        int y = bundle.getInt("y");;

        Toast.makeText(this, "Received " + msg + " " + x + " " + y, Toast.LENGTH_SHORT).show();

        Button btn = findViewById(R.id.button3);
        btn.setOnClickListener(v -> {
            int sum = x + y;

            Intent it = new Intent(this, MainActivity2.class);
            it.putExtra("msgBack", "Hello from activity 3");
            it.putExtra("sum", sum);

            setResult(RESULT_OK,it);
            finish();
        });
    }

}