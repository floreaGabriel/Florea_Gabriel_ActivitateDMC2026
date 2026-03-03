package com.florea_gabriel.labs;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "INFO";
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "My onStart");
        Log.e(TAG, "My Log.e");
        Log.w(TAG, "My Log.w");
        Log.i(TAG, "My Log.i");
        Log.v(TAG, "My Log.v");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "My onResume");
        Log.e(TAG, "My Log.e");
        Log.w(TAG, "My Log.w");
        Log.i(TAG, "My Log.i");
        Log.v(TAG, "My Log.v");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "My onPause");
        Log.e(TAG, "My Log.e");
        Log.w(TAG, "My Log.w");
        Log.i(TAG, "My Log.i");
        Log.v(TAG, "My Log.v");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "My onStop");
        Log.e(TAG, "My Log.e");
        Log.w(TAG, "My Log.w");
        Log.i(TAG, "My Log.i");
        Log.v(TAG, "My Log.v");
    }
}