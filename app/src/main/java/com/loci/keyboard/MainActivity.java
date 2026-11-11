package com.loci.keyboard;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Создаем простой интерфейс программно (чтобы не создавать лишние XML файлы)
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 50, 50, 50);
        layout.setBackgroundColor(0xFF1A0B2E); // Темно-фиолетовый фон

        TextView title = new TextView(this);
        title.setText("Настройки Loci Keyboard");
        title.setTextColor(0xFFFFFFFF);
        title.setTextSize(24);
        title.setPadding(0, 0, 0, 30);
        layout.addView(title);

        TextView info = new TextView(this);
        info.setText("1. Нажми кнопку ниже, чтобы открыть настройки.\n2. Найди 'Loci Keyboard' и включи её.\n3. Переключись на неё при вводе текста.");
        info.setTextColor(0xFFA0A0A0);
        info.setTextSize(16);
        info.setPadding(0, 0, 0, 40);
        layout.addView(info);

        Button btnOpenSettings = new Button(this);
        btnOpenSettings.setText("Открыть настройки клавиатуры");
        btnOpenSettings.setBackgroundColor(0xFF673AB7);
        btnOpenSettings.setTextColor(0xFFFFFFFF);
        btnOpenSettings.setOnClickListener(v -> {
            // Открываем системные настройки клавиатуры
            Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
            startActivity(intent);
        });
        layout.addView(btnOpenSettings);

        Button btnSwitch = new Button(this);
        btnSwitch.setText("Сменить клавиатуру сейчас");
        btnSwitch.setBackgroundColor(0xFF2196F3);
        btnSwitch.setTextColor(0xFFFFFFFF);
        btnSwitch.setOnClickListener(v -> {
            // Вызываем системное меню выбора клавиатуры
            android.view.inputmethod.InputMethodManager imm = 
                (android.view.inputmethod.InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showInputMethodPicker();
            }
        });
        layout.addView(btnSwitch);

        setContentView(layout);

        // Автоматически перекидываем в настройки при первом запуске
        // (Чтобы пользователь не искал, где включить клавиатуру)
        if (savedInstanceState == null) {
            Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
            startActivity(intent);
            Toast.makeText(this, "Включи Loci Keyboard в списке", Toast.LENGTH_LONG).show();
        }
    }
}
