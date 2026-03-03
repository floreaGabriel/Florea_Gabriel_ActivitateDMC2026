package com.florea_gabriel.labs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button btn = findViewById(R.id.button2);
        btn.setOnClickListener( v -> {

            String msg = "Hello from activity 2";
            int x = 7;
            int y = 8;

            Bundle bundle = new Bundle();
            bundle.putString("msg", msg);
            bundle.putInt("x", x);
            bundle.putInt("y", y);

            Intent it = new Intent(this, MainActivity3.class);
            it.putExtras(bundle);

            startActivityForResult(it, 100, bundle);


        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String msgBack = data.getStringExtra("msgBack");
        int sum = data.getIntExtra("sum",0);

        Toast.makeText(this, "Primit: " + msgBack + " sum: " + sum,Toast.LENGTH_SHORT).show();
    }
}