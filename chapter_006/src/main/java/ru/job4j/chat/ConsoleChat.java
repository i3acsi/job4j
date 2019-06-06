package ru.job4j.chat;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConsoleChat {
    private final Input input;
    private TextRND textRND;
    private Map<String, ChatAction> map;

    public ConsoleChat(Input input, String file) {
        this.input = input;
        this.map = new HashMap<>();
        this.textRND = new TextRND(file);
    }

    public void mapInit() {
        // Пропишем разные варианты приветствий
        Greetings greetings = new Greetings();
        map.put("привет", greetings);
        map.put("здравствуй", greetings);
        //
        Exit exit = new Exit();
        map.put("закончить", exit);
        //
        Stop stop = new Stop();
        map.put("стоп", stop);
        //
        Phrase phrase = new Phrase(textRND);
        map.put(null, phrase);
    }

    public void select(String key) {
       if (map.get(key) == null) {
           map.get(null).execute(this.input);
       } else {
           map.get(key).execute(this.input);
       }
    }

    public void start() {
        mapInit();
        String word;
        do {
            word = input.ask();
            select(word);
        } while (!"закончить".equals(word));
    }

    public static void main(String[] args) {
        ConsoleChat chat = new ConsoleChat(new ConsoleInput(), String.format(".%spom.xml", File.separator));
        chat.start();
    }
}
