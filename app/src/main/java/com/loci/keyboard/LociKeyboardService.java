package com.loci.keyboard;

import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

public class LociKeyboardService extends InputMethodService {
    
    private final Map<String, String> dictionary = new HashMap<>();
    private boolean isTranslateEnabled = true;
    private boolean isRussian = true;

    private void initDictionary() {
        // Приветствия
        dictionary.put("привет", "Ло");
        dictionary.put("приветик", "Локи");
        dictionary.put("пока", "Ло Но");
        dictionary.put("до свидания", "Ло Но");
        dictionary.put("спасибо", "Лока");
        dictionary.put("извини", "Ми Кило");

        // Ответы
        dictionary.put("да", "Ки");
        dictionary.put("нет", "Но");
        dictionary.put("хорошо", "Лока");
        dictionary.put("плохо", "Кило");
        dictionary.put("круто", "Лока");
        dictionary.put("ужасно", "Кило Кило");
        dictionary.put("не знаю", "Ми Но Кито");

        // Местоимения
        dictionary.put("я", "Ми");
        dictionary.put("ты", "Ти");
        dictionary.put("он", "Ли");
        dictionary.put("она", "Ли");
        dictionary.put("мы", "Мили");
        dictionary.put("вы", "Тили");
        dictionary.put("они", "Лили");

        // Люди
        dictionary.put("друг", "Или");
        dictionary.put("друзья", "Или");
        dictionary.put("учитель", "Учи");
        dictionary.put("фембой", "Кити");
        dictionary.put("кошкодевочка", "Кико");
        dictionary.put("котик", "Кико");

        // Чувства
        dictionary.put("люблю", "Кои");
        dictionary.put("нравится", "Кои");
        dictionary.put("больно", "Боли");
        dictionary.put("устал", "Кило Сила");
        dictionary.put("скучаю", "Ми Кои Ти");
        dictionary.put("хочу к тебе", "Ми Кои Ти");

        // Милые слова
        dictionary.put("любимка", "Кои");
        dictionary.put("любимая", "Кои");
        dictionary.put("зайка", "Лайка");
        dictionary.put("солнышко", "Луми");
        dictionary.put("малышка", "Мило");
        dictionary.put("родная", "Лило");
        dictionary.put("ангел", "Луми");
        dictionary.put("киса", "Кико");
        dictionary.put("обнимаю", "Ми Кои Ти");
        dictionary.put("целую", "Ми Кои Ти");

        // Действия
        dictionary.put("идти", "Сити");
        dictionary.put("пойдем", "Сити");
        dictionary.put("есть", "Ку");
        dictionary.put("кушать", "Ку");
        dictionary.put("спать", "Луми Но");
        dictionary.put("играть", "Кигра");
        dictionary.put("дела", "Лок");
        dictionary.put("хочу есть", "Ми Кои Ку");

        // Вопросы
        dictionary.put("где", "Кито");
        dictionary.put("как дела", "Лок Лока");
        dictionary.put("что", "Кито");
        dictionary.put("почему", "Кипо");
        dictionary.put("ты где", "Ти Кито");

        // Игры
        dictionary.put("роблокс", "Роблокс");
        dictionary.put("майнкрафт", "Майн");
        dictionary.put("победа", "Вик");

        // Ругательства
        dictionary.put("дурак", "Кило Или");
        dictionary.put("тупой", "Но Лок");
        dictionary.put("отвали", "Сити Но");
    }

    @Override
    public View onCreateInputView() {
        initDictionary();

        View view = getLayoutInflater().inflate(R.layout.keyboard_layout, null);

        LinearLayout rowRu1 = view.findViewById(R.id.rowRu1); LinearLayout rowRu2 = view.findViewById(R.id.rowRu2); LinearLayout rowRu3 = view.findViewById(R.id.rowRu3);
        LinearLayout rowEn1 = view.findViewById(R.id.rowEn1); LinearLayout rowEn2 = view.findViewById(R.id.rowEn2); LinearLayout rowEn3 = view.findViewById(R.id.rowEn3);

        // Русские ID и буквы
        int[] ruIds = {
            R.id.k_й, R.id.k_ц, R.id.k_у, R.id.k_к, R.id.k_е, R.id.k_н, R.id.k_г, R.id.k_ш, R.id.k_щ, R.id.k_з, R.id.k_х, R.id.k_ъ,
            R.id.k_ф, R.id.k_ы, R.id.k_в, R.id.k_а, R.id.k_п, R.id.k_р, R.id.k_о, R.id.k_л, R.id.k_д, R.id.k_ж, R.id.k_э,
            R.id.k_я, R.id.k_ч, R.id.k_с, R.id.k_м, R.id.k_и, R.id.k_т, R.id.k_ь, R.id.k_б, R.id.k_ю
        };
        String[] ruLetters = {
            "й", "ц", "у", "к", "е", "н", "г", "ш", "щ", "з", "х", "ъ",
            "ф", "ы", "в", "а", "п", "р", "о", "л", "д", "ж", "э",
            "я", "ч", "с", "м", "и", "т", "ь", "б", "ю"
        };

        // Английские ID и буквы
        int[] enIds = {
            R.id.e_q, R.id.e_w, R.id.e_e, R.id.e_r, R.id.e_t, R.id.e_y, R.id.e_u, R.id.e_i, R.id.e_o, R.id.e_p,
            R.id.e_a, R.id.e_s, R.id.e_d, R.id.e_f, R.id.e_g, R.id.e_h, R.id.e_j, R.id.e_k, R.id.e_l,
            R.id.e_z, R.id.e_x, R.id.e_c, R.id.e_v, R.id.e_b, R.id.e_n, R.id.e_m
        };
        String[] enLetters = {
            "q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
            "a", "s", "d", "f", "g", "h", "j", "k", "l",
            "z", "x", "c", "v", "b", "n", "m"
        };

        // Русские буквы
        for (int i = 0; i < ruIds.length; i++) {
            final String letter = ruLetters[i];
            view.findViewById(ruIds[i]).setOnClickListener(v -> {
                InputConnection ic = getCurrentInputConnection();
                if (ic != null) ic.commitText(letter, 1);
            });
        }

        // Английские буквы
        for (int i = 0; i < enIds.length; i++) {
            final String letter = enLetters[i];
            view.findViewById(enIds[i]).setOnClickListener(v -> {
                InputConnection ic = getCurrentInputConnection();
                if (ic != null) ic.commitText(letter, 1);
            });
        }

        // Смена языка (Глобус)
        view.findViewById(R.id.btnLang).setOnClickListener(v -> {
            isRussian = !isRussian;
            if (isRussian) {
                rowRu1.setVisibility(View.VISIBLE); rowRu2.setVisibility(View.VISIBLE); rowRu3.setVisibility(View.VISIBLE);
                rowEn1.setVisibility(View.GONE); rowEn2.setVisibility(View.GONE); rowEn3.setVisibility(View.GONE);
            } else {
                rowRu1.setVisibility(View.GONE); rowRu2.setVisibility(View.GONE); rowRu3.setVisibility(View.GONE);
                rowEn1.setVisibility(View.VISIBLE); rowEn2.setVisibility(View.VISIBLE); rowEn3.setVisibility(View.VISIBLE);
            }
        });

        // Настройки (Шестеренка) - открывает наше приложение
        view.findViewById(R.id.btnSettings).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        // Смайлики
        view.findViewById(R.id.btnEmoji).setOnClickListener(v -> {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) ic.commitText("😊", 1);
        });

        // Корзина (Удаляет ровно по одной букве)
        view.findViewById(R.id.btnBackspace).setOnClickListener(v -> {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) {
                if (ic.getSelectedText(0) == null) {
                    ic.deleteSurroundingText(1, 0);
                } else {
                    ic.commitText("", 1);
                }
            }
        });

        // ПРОБЕЛ (Перевод + пробел)
        View btnSpace = view.findViewById(R.id.btnSpace);
        btnSpace.setOnClickListener(v -> {
            InputConnection ic = getCurrentInputConnection();
            if (ic == null) return;
            
            if (isTranslateEnabled) {
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
            }
            ic.commitText(" ", 1);
        });

        // ДОЛГОЕ НАЖАТИЕ НА ПРОБЕЛ (Вкл/Выкл перевод)
        btnSpace.setOnLongClickListener(v -> {
            isTranslateEnabled = !isTranslateEnabled;
            Toast.makeText(this, isTranslateEnabled ? "Автоперевод ВКЛ" : "Автоперевод ВЫКЛ", Toast.LENGTH_SHORT).show();
            return true;
        });

        return view;
    }
}
