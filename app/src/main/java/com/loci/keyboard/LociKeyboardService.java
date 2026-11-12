package com.loci.keyboard;

import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

public class LociKeyboardService extends InputMethodService {
    
    private final Map<String, String> dictionary = new HashMap<>();
    private boolean isTranslateEnabled = true;
    private boolean isRussian = true;
    private boolean isEmojiPanelOpen = false;

    private void initDictionary() {
        // --- 1. ПРИВЕТСТВИЯ И ПРОЩАНИЯ ---
        dictionary.put("привет", "Ло");
        dictionary.put("приветик", "Локи");
        dictionary.put("здравствуй", "Ло");
        dictionary.put("хай", "Ки Ло");
        dictionary.put("доброе утро", "Луми Ки");
        dictionary.put("добрый день", "Луми Лока");
        dictionary.put("добрый вечер", "Луми Но");
        dictionary.put("спокойной ночи", "Луми Спи");
        dictionary.put("пока", "Ло Но");
        dictionary.put("до свидания", "Ло Но");
        dictionary.put("до встречи", "Ло Встреча");
        dictionary.put("увидимся", "Вид Ти");

        // --- 2. ВЕЖЛИВОСТЬ ---
        dictionary.put("спасибо", "Лока");
        dictionary.put("благодарю", "Лока Лока");
        dictionary.put("пожалуйста", "Ки Лока");
        dictionary.put("извини", "Ми Кило");
        dictionary.put("прости", "Ми Кило");
        dictionary.put("прошу прощения", "Ми Кило Лока");

        // --- 3. ОТВЕТЫ / СОГЛАСИЕ ---
        dictionary.put("да", "Ки");
        dictionary.put("конечно", "Ки Ки");
        dictionary.put("нет", "Но");
        dictionary.put("неа", "Но Но");
        dictionary.put("хорошо", "Лока");
        dictionary.put("плохо", "Кило");
        dictionary.put("нормально", "Лока Кило");
        dictionary.put("круто", "Лока Лока");
        dictionary.put("ужасно", "Кило Кило");
        dictionary.put("может быть", "Моко");
        dictionary.put("не знаю", "Ми Но Кито");
        dictionary.put("согласен", "Ми Ки");
        dictionary.put("не согласен", "Ми Но Ки");
        dictionary.put("окей", "Ки");
        dictionary.put("ладно", "Ки Лока");

        // --- 4. МЕСТОИМЕНИЯ ---
        dictionary.put("я", "Ми");
        dictionary.put("ты", "Ти");
        dictionary.put("он", "Ли");
        dictionary.put("она", "Ли");
        dictionary.put("оно", "Ли");
        dictionary.put("мы", "Мили");
        dictionary.put("вы", "Тили");
        dictionary.put("они", "Лили");
        dictionary.put("мой", "Ми");
        dictionary.put("твой", "Ти");
        dictionary.put("наш", "Мили");
        dictionary.put("ваш", "Тили");

        // --- 5. ЛЮДИ И РОЛИ ---
        dictionary.put("друг", "Или");
        dictionary.put("друзья", "Или");
        dictionary.put("подруга", "Или Ки");
        dictionary.put("учитель", "Учи");
        dictionary.put("человек", "Чел");
        dictionary.put("парень", "Бой");
        dictionary.put("девушка", "Гёрл");
        dictionary.put("фембой", "Кити");
        dictionary.put("фембойчик", "Кити");
        dictionary.put("кошкодевочка", "Кико");
        dictionary.put("кошкомальчик", "Кико");
        dictionary.put("котик", "Кико");
        dictionary.put("коти", "Кико");
        dictionary.put("киса", "Кико");
        dictionary.put("зая", "Лайка");
        dictionary.put("зайка", "Лайка");

        // --- 6. ЧУВСТВА И ЭМОЦИИ ---
        dictionary.put("люблю", "Кои");
        dictionary.put("нравится", "Кои");
        dictionary.put("обожаю", "Кои Кои");
        dictionary.put("не люблю", "Но Кои");
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
        dictionary.put("ты лучшая", "Ти Лока Лока");
        dictionary.put("ты красивый", "Ти Лока Ли");
        dictionary.put("ты красивая", "Ти Лока Ли");
        dictionary.put("мне плохо", "Ми Кило");
        dictionary.put("мне хорошо", "Ми Лока");
        dictionary.put("рад тебя видеть", "Лока Вид Ти");

        // --- 7. МИЛЫЕ СЛОВА И КОМПЛИМЕНТЫ ---
        dictionary.put("любимка", "Кои");
        dictionary.put("любимая", "Кои");
        dictionary.put("любимый", "Кои");
        dictionary.put("солнышко", "Луми");
        dictionary.put("малышка", "Мило");
        dictionary.put("малыш", "Мило");
        dictionary.put("родная", "Лило");
        dictionary.put("родной", "Лило");
        dictionary.put("ангел", "Луми");
        dictionary.put("милая", "Кити");
        dictionary.put("милый", "Кити");
        dictionary.put("самая лучшая", "Лока Лока");
        dictionary.put("буська", "Кои");
        dictionary.put("обнимаю", "Ми Кои Ти");
        dictionary.put("целую", "Ми Кои Ти");
        dictionary.put("поцелую", "Ми Кои Ти");
        dictionary.put("хочу обнять", "Ми Кои Ти");
        dictionary.put("моя любовь", "Ми Кои");

        // --- 8. ДЕЙСТВИЯ И ПОТРЕБНОСТИ ---
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
        dictionary.put("учиться", "Учи Лок");
        dictionary.put("хочу есть", "Ми Кои Ку");
        dictionary.put("хочу пить", "Ми Кои Вода");
        dictionary.put("хочу спать", "Ми Кои Луми Но");
        dictionary.put("иду домой", "Ми Сити Дом");
        dictionary.put("смотреть", "Вид");
        dictionary.put("слушать", "Хёр");
        dictionary.put("думать", "Мозг");
        dictionary.put("знать", "Кито Лока");

        // --- 9. ВОПРОСЫ ---
        dictionary.put("где", "Кито");
        dictionary.put("как дела", "Лок Лока");
        dictionary.put("как", "Кбк");
        dictionary.put("что", "Кито");
        dictionary.put("почему", "Кипо");
        dictionary.put("зачем", "Кипо Лок");
        dictionary.put("когда", "Кида");
        dictionary.put("где туалет", "Туалет Кито");
        dictionary.put("ты где", "Ти Кито");
        dictionary.put("скоро буду", "Ми Скоро");
        dictionary.put("как ты", "Ти Лока");
        dictionary.put("что делаешь", "Ти Лок");
        dictionary.put("ты дома", "Ти Дом");
        dictionary.put("ты поел", "Ти Ку");

        // --- 10. ИГРЫ И ТЕХНИКА ---
        dictionary.put("роблокс", "Роблокс");
        dictionary.put("майнкрафт", "Майн");
        dictionary.put("победа", "Вик");
        dictionary.put("поражение", "Кило Вик");
        dictionary.put("опасность", "Кило Лок");
        dictionary.put("пойдем играть", "Мили Сити Кигра");
        dictionary.put("я в роблоксе", "Ми Ин Роблокс");
        dictionary.put("я в майне", "Ми Ин Майн");
        dictionary.put("читер", "Кило Или");
        dictionary.put("телефон", "Фон");
        dictionary.put("компьютер", "ПК");
        dictionary.put("интернет", "Нет");
        dictionary.put("связь", "Линк");

        // --- 11. ЕДА ---
        dictionary.put("вода", "Вода");
        dictionary.put("еда", "Ку");
        dictionary.put("чай", "Ти");
        dictionary.put("кофе", "Кофе");
        dictionary.put("пицца", "Пицца");
        dictionary.put("суши", "Суши");
        dictionary.put("бургер", "Бургер");

        // --- 12. РУГАТЕЛЬСТВА (СЕКРЕТНЫЕ) ---
        dictionary.put("дурак", "Кило Или");
        dictionary.put("тупой", "Но Лок");
        dictionary.put("отвали", "Сити Но");
        dictionary.put("заткнись", "Но Ло");
        dictionary.put("идиот", "Кило Мозг");
        dictionary.put("блин", "Кило Лок");
        dictionary.put("черт", "Кило");
    }

    @Override
    public View onCreateInputView() {
        initDictionary();
        View view = getLayoutInflater().inflate(R.layout.keyboard_layout, null);

        // --- БЛОКИ СМАЙЛИКОВ ---
        LinearLayout emojiContainer = view.findViewById(R.id.emojiContainer);
        View emojiPanel = view.findViewById(R.id.emojiPanel);
        String[] emojis = {"😊", "😸", "❤️", "✨", "🐱", "💖", "🥺", "🔥", "😎", "🌸", "😻", "💕", "😂", "🥰", "😘", "😍"};
        
        for (String emoji : emojis) {
            Button b = new Button(this);
            b.setText(emoji);
            b.setTextSize(20);
            b.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0x00000000));
            b.setOnClickListener(v -> {
                InputConnection ic = getCurrentInputConnection();
                if (ic != null) ic.commitText(emoji, 1);
            });
            emojiContainer.addView(b);
        }

        view.findViewById(R.id.btnEmojiToggle).setOnClickListener(v -> {
            isEmojiPanelOpen = !isEmojiPanelOpen;
            emojiPanel.setVisibility(isEmojiPanelOpen ? View.VISIBLE : View.GONE);
        });

        // --- БЛОКИ ЯЗЫКОВ ---
        LinearLayout rowRu1 = view.findViewById(R.id.rowRu1); LinearLayout rowRu2 = view.findViewById(R.id.rowRu2); LinearLayout rowRu3 = view.findViewById(R.id.rowRu3);
        LinearLayout rowEn1 = view.findViewById(R.id.rowEn1); LinearLayout rowEn2 = view.findViewById(R.id.rowEn2); LinearLayout rowEn3 = view.findViewById(R.id.rowEn3);

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

        // --- БУКВЫ (Русские) ---
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
        for (int i = 0; i < ruIds.length; i++) {
            final String letter = ruLetters[i];
            view.findViewById(ruIds[i]).setOnClickListener(v -> {
                InputConnection ic = getCurrentInputConnection();
                if (ic != null) ic.commitText(letter, 1);
            });
        }

        // --- БУКВЫ (Английские) ---
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
        for (int i = 0; i < enIds.length; i++) {
            final String letter = enLetters[i];
            view.findViewById(enIds[i]).setOnClickListener(v -> {
                InputConnection ic = getCurrentInputConnection();
                if (ic != null) ic.commitText(letter, 1);
            });
        }

        // --- ВЕРХНЯЯ ПАНЕЛЬ ---
        view.findViewById(R.id.btnSettings).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        // --- КНОПКА 123 (Пока просто уведомление) ---
        view.findViewById(R.id.btnNumbers).setOnClickListener(v -> {
            Toast.makeText(this, "Режим цифр скоро появится", Toast.LENGTH_SHORT).show();
        });

        // --- КОРЗИНА (Удаляет 1 букву, при зажатии - слово) ---
        View btnBackspace = view.findViewById(R.id.btnBackspace);
        btnBackspace.setOnClickListener(v -> {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) ic.deleteSurroundingText(1, 0);
        });
        btnBackspace.setOnLongClickListener(v -> {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) {
                CharSequence beforeText = ic.getTextBeforeCursor(30, 0);
                if (beforeText != null) {
                    String text = beforeText.toString();
                    int lastSpace = text.lastIndexOf(' ');
                    if (lastSpace != -1) ic.deleteSurroundingText(text.length() - lastSpace, 0);
                    else ic.deleteSurroundingText(text.length(), 0);
                }
            }
            return true;
        });

        // --- ENTER (Перенос строки) ---
        view.findViewById(R.id.btnEnter).setOnClickListener(v -> {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) {
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER));
            }
        });

        // --- ПРОБЕЛ (Перевод) ---
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

        btnLongPress(btnSpace);

        return view;
    }

    private void btnLongPress(View btnSpace) {
        btnSpace.setOnLongClickListener(v -> {
            isTranslateEnabled = !isTranslateEnabled;
            Toast.makeText(this, isTranslateEnabled ? "Перевод ВКЛ" : "Перевод ВЫКЛ", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
            }
