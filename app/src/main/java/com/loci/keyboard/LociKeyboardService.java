package com.loci.keyboard;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.content.SharedPreferences;

public class LociKeyboardService extends InputMethodService {
    @Override
    public View onCreateInputView() {
        View view = getLayoutInflater().inflate(R.layout.keyboard_layout, null);

        Button btnLo = view.findViewById(R.id.btnLo);
        Button btnKi = view.findViewById(R.id.btnKi);
        Button btnNo = view.findViewById(R.id.btnNo);
        Button btnBackspace = view.findViewById(R.id.btnBackspace);

        InputConnection ic = getCurrentInputConnection();
        SharedPreferences prefs = getSharedPreferences("LociPrefs", MODE_PRIVATE);

        btnLo.setOnClickListener(v -> ic.commitText("Ло ", 1));
        btnKi.setOnClickListener(v -> ic.commitText("Ки ", 1));
        btnNo.setOnClickListener(v -> ic.commitText("Но ", 1));
        
        btnBackspace.setOnClickListener(v -> {
            if (ic.getSelectedText(0) == null) ic.deleteSurroundingText(1, 0);
            else ic.commitText("", 1);
        });

        return view;
    }

    // АВТОПЕРЕВОД: ловим ввод с обычной клавиатуры и меняем слова
    @Override
    public void onUpdateSelection(int oldSelStart, int oldSelEnd, int newSelStart, int newSelEnd, int candidatesStart, int candidatesEnd) {
        super.onUpdateSelection(oldSelStart, oldSelEnd, newSelStart, newSelEnd, candidatesStart, candidatesEnd);
        
        SharedPreferences prefs = getSharedPreferences("LociPrefs", MODE_PRIVATE);
        if (!prefs.getBoolean("autoTranslate", true)) return;

        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;

        // Получаем текст, который сейчас перед курсором
        CharSequence beforeText = ic.getTextBeforeCursor(20, 0);
        if (beforeText == null) return;
        
        String text = beforeText.toString().toLowerCase();
        
        // Если пользователь ввел русское слово и поставил пробел, заменяем
        if (text.endsWith("привет ")) {
            ic.deleteSurroundingText(7, 0);
            ic.commitText("Ло ", 1);
        } else if (text.endsWith("да ")) {
            ic.deleteSurroundingText(3, 0);
            ic.commitText("Ки ", 1);
        } else if (text.endsWith("нет ")) {
            ic.deleteSurroundingText(4, 0);
            ic.commitText("Но ", 1);
        }
        // Можешь добавить сюда другие слова по аналогии
    }
}
