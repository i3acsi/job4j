package ru.job4j.templates;

import java.util.*;
import java.util.regex.*;

/**
 * Класс SimpleGenerator - Класс для работы с меню, предстваленным классом MenuParagraph.
 * Генератор должен получать входную строку с ключами в тексте и список значений по этим ключам.
 * Например. Входящая строка String template = "I am a ${name}, Who are ${subject}? "
 * и список значений ассоциированных по ключу name -> "Petr", subject -> "you"
 * На выходе должна быть строка - "I am Petr, Who are you?"
 * <p>
 * Другой пример. " Help, ${sos}, ${sos}, ${sos}",
 * sos -> "Aaaa". Должно получится " Help, Ааа, Ааа, Ааа"
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version 0.1
 */
public class SimpleGenerator implements Template {

    private static final Pattern KEYS = Pattern.compile("\\$\\{(\\w+)}");

    @Override
    public String generate(String template, String[] data) {
        return generate(template, getData(data));
    }

    public String generate(String template, Map<String, String> keys) {
        String result = template;
        Matcher matcher = KEYS.matcher(result);
        Set<String> temp = new HashSet<>();

        result = matcher.replaceAll(matchResult -> {
            temp.add(matchResult.group(0));
            String replace = keys.get(matchResult.group(0));
            if (replace == null) {
                throw new NotEnoughKeysException("not enough key");
            }
            return replace;
        });

        if (keys.size() > temp.size()) {
            throw new TooMuchKeysException("too much keys");
        } else if (keys.size() < temp.size()) {
            throw new NotEnoughKeysException("not enough key");
        }
        return result;
    }

    private Map<String, String> getData(String[] data) {
        Map<String, String> result = new HashMap<>();
        for (String d : data) {
            String[] strings = d.split("=");
            result.put(String.format("${%s}", strings[0]), strings[1]);
        }
        return result;
    }

    public static void main(String[] args) {
        SimpleGenerator generator = new SimpleGenerator();
        System.out.println(generator.generate(
                "A ${name} finds matches in a subset of its input called the ${reg}.\n" +
                        "By default, the region contains all of the ${name}'s input.\n" +
                        "The ${reg} can be modified via the ${reg} method and queried via the ${reg}Start and ${reg}End methods.\n" +
                        "The way that the ${reg} boundaries interact with some pattern constructs can be changed.",
                new String[]{"name=matcher"}
        ));
    }
}

class NotEnoughKeysException extends RuntimeException {
    private String msg;

    NotEnoughKeysException(String load) {
        super("", null, false, false);
        this.msg = load;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}

class TooMuchKeysException extends RuntimeException {
    private String msg;

    TooMuchKeysException(String load) {
        super("", null, false, false);
        this.msg = load;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}