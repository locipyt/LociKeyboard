package com.loci.keyboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

public class LociKeyboardService extends InputMethodService {
    
    private final Map<String, String> dictionary = new HashMap<>();
    private boolean isTranslateEnabled = true;
    private boolean isShiftPressed = false;

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

        // --- 6. МИЛЫЕ СЛОВА ---
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

        // --- 7. ДЕЙСТВИЯ ---
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

        // --- 10. РУГАТЕЛЬСТВА ---
        dictionary.put("дурак", "Кило Или");
        dictionary.put("тупой", "Но Лок");
        dictionary.put("отвали", "Сити Но");
        dictionary.put("заткнись", "Но Ло");
    }

    @Override
    public View onCreateInputView() {
        initDictionary();

        View view = getLayoutInflater().inflate(R.layout.keyboard_layout, null);

        // --- БУКВЫ ---
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

        for (int i = 0; i < letterIds.length; i++) {
            final String letter = letters[i];
            view.findViewById(letterIds[i]).setOnClickListener(v -> {
                InputConnection ic = getCurrentInputConnection();
                if (ic != null) {
                    String textToInsert = isShiftPressed ? letter.toUpperCase() : letter;
                    ic.commitText(textToInsert, 1);
                    if (isShiftPressed) isShiftPressed = false; // Сброс Shift
                }
            });
        }

        // --- ШЕСТЕРЕНКА (Настройки) ---
        view.findViewById(R.id.btnSettings).setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        // --- КОПИРОВАТЬ ---
        view.findViewById(R.id.btnCopy).setOnClickListener(v -> {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) {
                CharSequence text = ic.getSelectedText(0);
                if (text != null) {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Loci", text);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(this, "Скопировано", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // --- ВСТАВИТЬ ---
        view.findViewById(R.id.btnPaste).setOnClickListener(v -> {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if (clipboard.hasPrimaryClip()) {
                    ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                    ic.commitText(item.getText(), 1);
                }
            }
        });

        // --- СМАЙЛИКИ ---
        view.findViewById(R.id.btnEmoji).setOnClickListener(v -> {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) {
                String[] emojis = {"😊", "😸", "❤️", "✨", "🐱", "💖", "🥺", "🔥", "😎", "🌸", "😻", "💕"};
                String randomEmoji = emojis[(int) (Math.random() * emojis.length)];
                ic.commitText(randomEmoji, 1);
            }
        });

        // --- SHIFT (Заглавные) ---
        view.findViewById(R.id.btnShift).setOnClickListener(v -> {
            isShiftPressed = !isShiftPressed;
            Toast.makeText(this, isShiftPressed ? "Caps: ВКЛ" : "Caps: ВЫКЛ", Toast.LENGTH_SHORT).show();
        });

        // --- УДАЛИТЬ СЛОВО ---
        view.findViewById(R.id.btnDelWord).setOnClickListener(v -> {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) {
                CharSequence beforeText = ic.getTextBeforeCursor(30, 0);
                if (beforeText != null) {
                    String text = beforeText.toString();
                    int lastSpace = text.lastIndexOf(' ');
                    if (lastSpace != -1) {
                        ic.deleteSurroundingText(text.length() - lastSpace, 0);
                    } else {
                        ic.deleteSurroundingText(text.length(), 0);
                    }
                }
            }
        });

        // --- ENTER ---
        view.findViewById(R.id.btnEnter).setOnClickListener(v -> {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) {
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER));
            }
        });

        // --- ПРОБЕЛ + ПЕРЕВОД ---
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

        // --- ДОЛГОЕ НАЖАТИЕ НА ПРОБЕЛ (Вкл/Выкл перевод) ---
        btnSpace.setOnLongClickListener(v -> {
            isTranslateEnabled = !isTranslateEnabled;
            Toast.makeText(this, isTranslateEnabled ? "Автоперевод ВКЛ" : "Автоперевод ВЫКЛ", Toast.LENGTH_SHORT).show();
            return true;
        });

        return view;
    }
}
