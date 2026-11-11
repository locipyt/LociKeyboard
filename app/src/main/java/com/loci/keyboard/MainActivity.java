package com.loci.keyboard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch switchTranslate = findViewById(R.id.switchTranslate);
        SharedPreferences prefs = getSharedPreferences("LociPrefs", MODE_PRIVATE);
        
        boolean isEnabled = prefs.getBoolean("autoTranslate", true);
        switchTranslate.setChecked(isEnabled);

        switchTranslate.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("autoTranslate", isChecked).apply();
        });
    }
}
