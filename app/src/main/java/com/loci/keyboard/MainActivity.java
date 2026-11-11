package com.loci.keyboard;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Создаем красивый интерфейс программно
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(60, 60, 60, 60);
        layout.setBackgroundColor(0xFF1A0B2E); // Темно-фиолетовый фон

        // Заголовок
        TextView title = new TextView(this);
        title.setText("Loci Keyboard");
        title.setTextColor(0xFFFFFFFF);
        title.setTextSize(28);
        title.setPadding(0, 0, 0, 30);
        layout.addView(title);

        // Инструкция
        TextView info = new TextView(this);
        info.setText("1. Нажми 'Включить клавиатуру' ниже.\n2. В открывшемся списке найди Loci Keyboard и включи тумблер.\n3. Нажми 'Выбрать клавиатуру' и выбери Loci.\n\nДолгое нажатие на пробел в клавиатуре - включение/отключение автоперевода.");
        info.setTextColor(0xFFA0A0A0);
        info.setTextSize(16);
        info.setPadding(0, 0, 0, 40);
        layout.addView(info);

        // Кнопка включения (открывает системные настройки)
        Button btnEnable = new Button(this);
        btnEnable.setText("Включить клавиатуру в системе");
        btnEnable.setBackgroundColor(0xFF673AB7); // Фиолетовый
        btnEnable.setTextColor(0xFFFFFFFF);
        btnEnable.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
            startActivity(intent);
            Toast.makeText(this, "Найди Loci Keyboard и включи её", Toast.LENGTH_LONG).show();
        });
        layout.addView(btnEnable);

        // Кнопка смены клавиатуры (вызывает меню выбора)
        Button btnSwitch = new Button(this);
        btnSwitch.setText("Сменить клавиатуру сейчас");
        btnSwitch.setBackgroundColor(0xFF2196F3); // Синий
        btnSwitch.setTextColor(0xFFFFFFFF);
        btnSwitch.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showInputMethodPicker(); // Вызывает системное меню выбора клавиатуры
            }
        });
        layout.addView(btnSwitch);

        setContentView(layout);

        // При первом запуске автоматически открываем настройки
        if (savedInstanceState == null) {
            Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
            startActivity(intent);
            Toast.makeText(this, "Включи Loci Keyboard в списке", Toast.LENGTH_LONG).show();
        }
    }
}
