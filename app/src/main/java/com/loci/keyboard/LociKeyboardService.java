package com.loci.keyboard;

import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputConnection;
import java.util.HashMap;
import java.util.Map;

public class LociKeyboardService extends InputMethodService {
    
    private final Map<String, String> dictionary = new HashMap<>();

    private void initDictionary() {
        // Приветствия
        dictionary.put("привет", "Ло");
        dictionary.put("приветик", "Локи");
        dictionary.put("здравствуй", "Ло");
        dictionary.put("хай", "Ки Ло");
        dictionary.put("пока", "Ло Но");
        dictionary.put("спокойной ночи", "Луми Но");
        dictionary.put("доброе утро", "Луми Ки");
        dictionary.put("спасибо", "Лока");
        dictionary.put("пожалуйста", "Ки Лока");
        dictionary.put("извини", "Ми Кило");
        
        // Согласие/Отказ
        dictionary.put("да", "Ки");
        dictionary.put("нет", "Но");
        dictionary.put("хорошо", "Лока");
        dictionary.put("плохо", "Кило");
        dictionary.put("круто", "Лока");
        dictionary.put("ужасно", "Кило Кило");
        dictionary.put("может быть", "Моко");
        dictionary.put("не знаю", "Ми Но Кито");
        
        // Местоимения
        dictionary.put("я", "Ми");
        dictionary.put("ты", "Ти");
        dictionary.put("он", "Ли");
        dictionary.put("она", "Ли");
        dictionary.put("мы", "Мили");
        dictionary.put("вы", "Тили");
        dictionary.put("они", "Лили");
        
        // Чувства
        dictionary.put("люблю", "Кои");
        dictionary.put("нравится", "Кои");
        dictionary.put("больно", "Боли");
        dictionary.put("устал", "Кило Сила");
        dictionary.put("скучно", "Но Лок");
        dictionary.put("счастлив", "Лока");
        dictionary.put("скучаю", "Ми Кои Ти");
        
        // Милые слова
        dictionary.put("любимка", "Кои");
        dictionary.put("зайка", "Лайка");
        dictionary.put("солнышко", "Луми");
        dictionary.put("малышка", "Мило");
        dictionary.put("ангел", "Луми");
        dictionary.put("фембой", "Кити");
        dictionary.put("котик", "Кико");
        dictionary.put("кошкодевочка", "Кико");
        
        // Действия
        dictionary.put("идти", "Сити");
        dictionary.put("пойдем", "Сити");
        dictionary.put("есть", "Ку");
        dictionary.put("кушать", "Ку");
        dictionary.put("играть", "Кигра");
        dictionary.put("дела", "Лок");
        dictionary.put("хочу есть", "Ми Кои Ку");
        
        // Вопросы
        dictionary.put("где", "Кито");
        dictionary.put("как дела", "Лок Лока");
        dictionary.put("что", "Кито");
        dictionary.put("почему", "Кипо");
        dictionary.put("ты где", "Ти Кито");
    }

    @Override
    public View onCreateInputView() {
        initDictionary();

        View view = getLayoutInflater().inflate(R.layout.keyboard_layout, null);

        int[] letterIds = {
            R.id.k_й, R.id.k_ц, R.id.k_у, R.id.k_к, R.id.k_е, R.id.k_н, R.id.k_г, R.id.k_ш, R.id.k_щ, R.id.k_з, R.id.k_х, R.id.k_ъ,
            R.id.k_ф, R.id.k_ы, R.id.k_в, R.id.k_а, R.id.k_п, R.id.k_р, R.id.k_о, R.id.k_л, R.id.k_д, R.id.k_ж, R.id.k_э,
            R.id.k_я, R.id.k_ч, R.id.k_с, R.id.k_м, R.id.k_и, R.id.k_т, R.id.k_ь, R.id.k_б, R.id.k_ю
        };
        
        String[] letters = {
            "й", "ц", "у", "к", "е", "н", "г", "ш", "щ", "з", "х", "ъ",
            "ф", "ы", "в", "а", "п", "р", "о", "л", "д", "ж", "э",
            "я", "ч", "с", "м", "и", "т", "ь", "б", "ю"
        };

        // Буквы (InputConnection теперь берется внутри клика)
        for (int i = 0; i < letterIds.length; i++) {
            final String letter = letters[i];
            view.findViewById(letterIds[i]).setOnClickListener(v -> {
                InputConnection ic = getCurrentInputConnection();
                if (ic != null) ic.commitText(letter, 1);
            });
        }

        // Шестеренка (Открывает настройки клавиатуры Android)
        view.findViewById(R.id.btnSettings).setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        // Стереть
        view.findViewById(R.id.btnBackspace).setOnClickListener(v -> {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) {
                if (ic.getSelectedText(0) == null) ic.deleteSurroundingText(1, 0);
                else ic.commitText("", 1);
            }
        });

        // ПРОБЕЛ + АВТОПЕРЕВОД
        view.findViewById(R.id.btnSpace).setOnClickListener(v -> {
            InputConnection ic = getCurrentInputConnection();
            if (ic == null) return;
            
            CharSequence beforeText = ic.getTextBeforeCursor(30, 0);
            if (beforeText != null) {
                String text = beforeText.toString();
                int lastSpace = text.lastIndexOf(' ');
                String lastWord = (lastSpace == -1) ? text : text.substring(lastSpace + 1);
                
                if (dictionary.containsKey(lastWord.toLowerCase())) {
                    String translated = dictionary.get(lastWord.toLowerCase());
                    ic.deleteSurroundingText(lastWord.length(), 0);
                    ic.commitText(translated + " ", 1);
                    return;
                }
            }
            ic.commitText(" ", 1);
        });

        return view;
    }
}
