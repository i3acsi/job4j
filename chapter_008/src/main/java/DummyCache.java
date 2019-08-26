import com.google.common.collect.HashBasedTable;
import com.google.common.io.Files;

import java.nio.file.Path;
import java.nio.file.Paths;

/*
Создать структуру данных типа кеш. Кеш должен быть абстрактный.
То есть необходимо, что бы можно было задать ключ получения объекта кеша и в случае если его нет в памяти, задать поведение загрузки этого объекта в кеш.

Создать программу эмулирующее поведение данного кеша.
Программа должна считывать текстовые файлы из системы и выдавать текст при запросе имени файла.
Если в кеше файла нет. Кеш должен загрузить себе данные. По умолчанию в кеше нет ни одного файла.
Текстовые файл должны лежать в одной директории. Пример. Names.txt, Address.txt - файлы в системе.
При запросе по ключу Names.txt - кеш должен вернуть содержимое файла Names.txt.
 */

public class DummyCache {
    public static void main(String[] args) {
        ClassSaveKey key = new ClassSaveKey();
//        DummyCache cache=new DummyCache();
//        cache.parseArg(args);
    }

    private void parseArg(String[] args) { //maybe it should return stream?

        if (args.length!=1) {
            System.out.println("wrong parameter!");
        } else {
            Path p = Paths.get(args[0]);
            if (Files.isFile().apply(p.toFile())){

            }
        }
    }
}

class ClassSaveKey {

    private static HashBasedTable<String, String, String> languageTable = HashBasedTable.create();

    public static void main(String[] args) {
        /*первоначальные данные*/
        setText("Object-oriented programming language", "1990", "Python");
        setText("Object-oriented programming language", "1995", "Java");
        setText("Functional programming language", "2005", "F#");

        /*вывод в консоль*/
        System.out.println("Языки: " + languageTable.row("Object-oriented programming language"));
        System.out.println("Ключи строк: " + languageTable.rowKeySet());
        System.out.println("Структура относительно столбцов: " + languageTable.columnMap());
        System.out.println("Получение значения с использованием ключа столбца и строки: " + languageTable.column("2005").get("Functional programming language"));
    }

    private static void setText(String rowStr, String keyStr, String text) {
        languageTable.put(rowStr, keyStr, text);
    }
}