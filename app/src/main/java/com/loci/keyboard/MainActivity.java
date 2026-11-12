package com.loci.keyboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Создаем интерфейс
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(60, 60, 60, 60);
        layout.setBackgroundColor(0xFF1A0B2E);

        TextView title = new TextView(this);
        title.setText("Loci Keyboard");
        title.setTextColor(0xFFFFFFFF);
        title.setTextSize(28);
        title.setPadding(0, 0, 0, 30);
        layout.addView(title);

        TextView info = new TextView(this);
        info.setTextColor(0xFFA0A0A0);
        info.setTextSize(16);
        info.setPadding(0, 0, 0, 40);
        layout.addView(info);

        Button btnEnable = new Button(this);
        btnEnable.setText("Включить клавиатуру в системе");
        btnEnable.setBackgroundColor(0xFF673AB7);
        btnEnable.setTextColor(0xFFFFFFFF);
        btnEnable.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
            startActivity(intent);
        });
        layout.addView(btnEnable);

        Button btnSwitch = new Button(this);
        btnSwitch.setText("Сменить клавиатуру сейчас");
        btnSwitch.setBackgroundColor(0xFF2196F3);
        btnSwitch.setTextColor(0xFFFFFFFF);
        btnSwitch.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showInputMethodPicker();
            }
        });
        layout.addView(btnSwitch);

        setContentView(layout);

        // --- ПРОВЕРКА: ВКЛЮЧЕНА ЛИ КЛАВИАТУРА ---
        if (isKeyboardEnabled()) {
            // Если включена - показываем, что все ок
            info.setText("Статус: Клавиатура включена.\n\nИнструкция:\n1. Выбери Loci Keyboard при вводе текста (через глобус 🌐).\n2. Долгое нажатие на пробел - вкл/выкл перевод.");
            btnEnable.setVisibility(View.GONE); // Скрываем кнопку включения, она не нужна
            Toast.makeText(this, "Клавиатура готова к работе!", Toast.LENGTH_SHORT).show();
        } else {
            // Если не включена - показываем инструкцию и перекидываем в настройки
            info.setText("Статус: Клавиатура выключена.\n\n1. Нажми 'Включить клавиатуру' ниже.\n2. Найди Loci Keyboard в списке и включи её.");
            Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
            startActivity(intent);
        }
    }

    // Метод проверки: есть ли наша клавиатура в списке включенных
    private boolean isKeyboardEnabled() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        List<InputMethodInfo> methodList = imm.getEnabledInputMethodList();
        
        for (InputMethodInfo method : methodList) {
            if (method.getPackageName().equals(getPackageName())) {
                return true; // Нашли нашу клавиатуру в списке включенных
            }
        }
        return false; // Не нашли
    }
}
