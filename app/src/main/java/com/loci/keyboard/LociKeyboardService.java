package com.loci.keyboard;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.inputmethod.InputConnection;
import java.util.HashMap;
import java.util.Map;

public class LociKeyboardService extends InputMethodService {
    
    // БОЛЬШОЙ СЛОВАРЬ Loci
    private final Map<String, String> dictionary = new HashMap<>();

    private void initDictionary() {
        // --- 1. ПРИВЕТСТВИЯ ---
        dictionary.put("привет", "Ло");
        dictionary.put("приветик", "Локи");
        dictionary.put("здравствуй", "Ло");
        dictionary.put("хай", "Ки Ло");
        dictionary.put("пока", "Ло Но");
        dictionary.put("до свидания", "Ло Но");
        dictionary.put("спокойной ночи", "Луми Но");
        dictionary.put("доброе утро", "Луми Ки");
        dictionary.put("спасибо", "Лока");
        dictionary.put("пожалуйста", "Ки Лока");
        dictionary.put("извини", "Ми Кило");
        dictionary.put("прости", "Ми Кило");

        // --- 2. ОТВЕТЫ / СОГЛАСИЕ ---
        dictionary.put("да", "Ки");
        dictionary.put("нет", "Но");
        dictionary.put("хорошо", "Лока");
        dictionary.put("плохо", "Кило");
        dictionary.put("круто", "Лока");
        dictionary.put("ужасно", "Кило Кило");
        dictionary.put("может быть", "Моко");
        dictionary.put("не знаю", "Ми Но Кито");
        dictionary.put("согласен", "Ми Ки");
        dictionary.put("не хочу", "Ми Но Кои");
        dictionary.put("окей", "Ки");
        dictionary.put("конечно", "Ки Лока");

        // --- 3. МЕСТОИМЕНИЯ ---
        dictionary.put("я", "Ми");
        dictionary.put("ты", "Ти");
        dictionary.put("он", "Ли");
        dictionary.put("она", "Ли");
        dictionary.put("мы", "Мили");
        dictionary.put("вы", "Тили");
        dictionary.put("они", "Лили");
        dictionary.put("мой", "Ми");
        dictionary.put("твой", "Ти");
        dictionary.put("наш", "Мили");

        // --- 4. ЛЮДИ И РОЛИ ---
        dictionary.put("друг", "Или");
        dictionary.put("друзья", "Или");
        dictionary.put("учитель", "Учи");
        dictionary.put("фембой", "Кити");
        dictionary.put("фембойчик", "Кити");
        dictionary.put("кошкодевочка", "Кико");
        dictionary.put("кошкомальчик", "Кико");
        dictionary.put("котик", "Кико");
        dictionary.put("коти", "Кико");

        // --- 5. ЧУВСТВА И ЭМОЦИИ ---
        dictionary.put("люблю", "Кои");
        dictionary.put("нравится", "Кои");
        dictionary.put("обожаю", "Кои Кои");
        dictionary.put("больно", "Боли");
        dictionary.put("устал", "Кило Сила");
        dictionary.put("скучно", "Но Лок");
        dictionary.put("счастлив", "Лока");
        dictionary.put("злой", "Кило");
        dictionary.put("грустно", "Кило");
        dictionary.put("страшно", "Кило");
        dictionary.put("скучаю", "Ми Кои Ти");
        dictionary.put("хочу к тебе", "Ми Кои Ти");
        dictionary.put("ты лучший", "Ти Лока Лока");

        // --- 6. МИЛЫЕ СЛОВА И КОМПЛИМЕНТЫ ---
        dictionary.put("любимка", "Кои");
        dictionary.put("любимая", "Кои");
        dictionary.put("любимый", "Кои");
        dictionary.put("зайка", "Лайка");
        dictionary.put("зая", "Лайка");
        dictionary.put("солнышко", "Луми");
        dictionary.put("малышка", "Мило");
        dictionary.put("малыш", "Мило");
        dictionary.put("родная", "Лило");
        dictionary.put("родной", "Лило");
        dictionary.put("ангел", "Луми");
        dictionary.put("красивая", "Лока Ли");
        dictionary.put("красивый", "Лока Ли");
        dictionary.put("милая", "Кити");
        dictionary.put("милый", "Кити");
        dictionary.put("самая лучшая", "Лока Лока");
        dictionary.put("киса", "Кико");
        dictionary.put("кис", "Кико");
        dictionary.put("буська", "Кои");
        dictionary.put("обнимаю", "Ми Кои Ти");
        dictionary.put("целую", "Ми Кои Ти");
        dictionary.put("поцелую", "Ми Кои Ти");

        // --- 7. ДЕЙСТВИЯ И ПОТРЕБНОСТИ ---
        dictionary.put("идти", "Сити");
        dictionary.put("идем", "Сити");
        dictionary.put("пойдем", "Сити");
        dictionary.put("погнали", "Сити");
        dictionary.put("есть", "Ку");
        dictionary.put("кушать", "Ку");
        dictionary.put("пить", "Вода");
        dictionary.put("спать", "Луми Но");
        dictionary.put("играть", "Кигра");
        dictionary.put("игра", "Кигра");
        dictionary.put("дела", "Лок");
        dictionary.put("работа", "Лок");
        dictionary.put("хочу есть", "Ми Кои Ку");
        dictionary.put("хочу пить", "Ми Кои Вода");
        dictionary.put("хочу спать", "Ми Кои Луми Но");
        dictionary.put("иду домой", "Ми Сити Дом");

        // --- 8. ВОПРОСЫ ---
        dictionary.put("где", "Кито");
        dictionary.put("как дела", "Лок Лока");
        dictionary.put("как", "Кбк");
        dictionary.put("что", "Кито");
        dictionary.put("почему", "Кипо");
        dictionary.put("где туалет", "Туалет Кито");
        dictionary.put("ты где", "Ти Кито");
        dictionary.put("скоро буду", "Ми Скоро");
        dictionary.put("как ты", "Ти Лока");
        dictionary.put("что делаешь", "Ти Лок");

        // --- 9. ИГРОВОЕ ---
        dictionary.put("роблокс", "Роблокс");
        dictionary.put("майнкрафт", "Майн");
        dictionary.put("победа", "Вик");
        dictionary.put("опасность", "Кило Лок");
        dictionary.put("пойдем играть", "Мили Сити Кигра");
        dictionary.put("я в роблоксе", "Ми Ин Роблокс");
        dictionary.put("я в майне", "Ми Ин Майн");
        dictionary.put("читер", "Кило Или");

        // --- 10. РУГАТЕЛЬСТВА (СЕКРЕТНЫЕ) ---
        dictionary.put("дурак", "Кило Или");
        dictionary.put("тупой", "Но Лок");
        dictionary.put("отвали", "Сити Но");
        dictionary.put("заткнись", "Но Ло");
    }

    @Override
    public View onCreateInputView() {
        initDictionary();

        View view = getLayoutInflater().inflate(R.layout.keyboard_layout, null);

        // ID кнопок для букв
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

        InputConnection ic = getCurrentInputConnection();

        // Буквы
        for (int i = 0; i < letterIds.length; i++) {
            final String letter = letters[i];
            view.findViewById(letterIds[i]).setOnClickListener(v -> ic.commitText(letter, 1));
        }

        // Верхние кнопки
        view.findViewById(R.id.btnLo).setOnClickListener(v -> ic.commitText("Ло ", 1));
        view.findViewById(R.id.btnKi).setOnClickListener(v -> ic.commitText("Ки ", 1));
        view.findViewById(R.id.btnNo).setOnClickListener(v -> ic.commitText("Но ", 1));

        // Стереть
        view.findViewById(R.id.btnBackspace).setOnClickListener(v -> {
            if (ic.getSelectedText(0) == null) ic.deleteSurroundingText(1, 0);
            else ic.commitText("", 1);
        });

        // ПРОБЕЛ + АВТОПЕРЕВОД
        view.findViewById(R.id.btnSpace).setOnClickListener(v -> {
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
