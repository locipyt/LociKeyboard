package com.loci.keyboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout root;
    private TextView statusView;
    private Button enableButton;
    private Button selectButton;
    private Button resetButton;
    private TextView infoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Корневой скролл
        ScrollView scroll = new ScrollView(this);
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20), dp(20), dp(20), dp(20));
        root.setBackgroundColor(0xFF1A0B2E);
        scroll.addView(root);

        // Заголовок
        TextView title = new TextView(this);
        title.setText("Loci Keyboard");
        title.setTextColor(0xFFFFFFFF);
        title.setTextSize(28);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 0, 0, dp(20));
        root.addView(title);

        // Статус
        statusView = new TextView(this);
        statusView.setTextColor(0xFFB0A0D0);
        statusView.setTextSize(16);
        statusView.setGravity(Gravity.CENTER);
        statusView.setPadding(0, 0, 0, dp(20));
        root.addView(statusView);

        // Кнопка "Включить клавиатуру"
        enableButton = makeButton("Включить клавиатуру");
        enableButton.setOnClickListener(v -> {
            startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
        });
        root.addView(enableButton);

        // Кнопка "Выбрать клавиатуру"
        selectButton = makeButton("Выбрать клавиатуру");
        selectButton.setOnClickListener(v -> {
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showInputMethodPicker();
        });
        root.addView(selectButton);

        // Разделитель
        root.addView(makeSeparator());

        // Инфо-блок
        infoView = new TextView(this);
        infoView.setTextColor(0xFFB0A0D0);
        infoView.setTextSize(14);
        infoView.setPadding(0, dp(10), 0, dp(10));
        infoView.setText(
                "Клавиатура Loci:\n" +
                "• Кнопка ⚙ — открывает это меню.\n" +
                "• Кнопка 🌐 — переключение языка.\n" +
                "• Кнопка 123 — режим цифр.\n" +
                "• Кнопка ⌫ — удаление; зажми для удаления слова.\n" +
                "• Кнопка ⏎ — перенос строки.\n" +
                "• Кнопка 😊 — панель смайликов.\n" +
                "• Кнопка 📋 — копировать выделенное.\n" +
                "• Кнопка 📥 — вставить из буфера.\n" +
                "• Пробел — перевод слов из словаря; зажми для вкл/выкл перевода."
        );
        root.addView(infoView);

        // Кнопка сброса (просто перезапускает приложение)
        resetButton = makeButton("Сбросить настройки");
        resetButton.setOnClickListener(v -> {
            Toast.makeText(this, "Настройки сброшены", Toast.LENGTH_SHORT).show();
            recreate();
        });
        root.addView(resetButton);

        setContentView(scroll);
        updateStatus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStatus();
    }

    /** Проверяет, включена ли наша клавиатура и выбрана ли она. */
    private void updateStatus() {
        InputMethodManager imm =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        List<InputMethodInfo> methods = imm.getEnabledInputMethodList();

        boolean enabled = false;
        String myId = getPackageName() + "/.LociKeyboardService";
        for (InputMethodInfo info : methods) {
            if (info.getId().equals(myId)) {
                enabled = true;
                break;
            }
        }

        if (!enabled) {
            statusView.setText("❌ Клавиатура отключена");
            statusView.setTextColor(0xFFFF5252);
            enableButton.setEnabled(true);
            selectButton.setEnabled(false);
        } else {
            statusView.setText("✅ Клавиатура включена");
            statusView.setTextColor(0xFF4CAF50);
            enableButton.setEnabled(true);
            selectButton.setEnabled(true);
        }
    }

    // ---- Хелперы ----

    private Button makeButton(String text) {
        Button b = new Button(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(56));
        lp.setMargins(0, dp(6), 0, dp(6));
        b.setLayoutParams(lp);
        b.setText(text);
        b.setTextSize(16);
        b.setTextColor(0xFFFFFFFF);
        b.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFF9C27B0));
        return b;
    }

    private View makeSeparator() {
        View v = new View(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dp(1));
        lp.setMargins(0, dp(20), 0, dp(20));
        v.setLayoutParams(lp);
        v.setBackgroundColor(0xFF3A2B5E);
        return v;
    }

    private int dp(int px) {
        return (int) (px * getResources().getDisplayMetrics().density);
    }
}
