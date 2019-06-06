package ru.job4j.io;

/*
1. Создать программу для поиска файла.
2. Программа должна искать данные в заданном каталоге и подкаталогах.
3. Имя файла может задаваться, целиком, по маске, по регулярному выражение(не обязательно).
4. Программа должна собираться в jar и запускаться через java -jar find.jar -d c:/ -n *.txt -m -o log.txt
Ключи
-d - директория в которая начинать поиск.
-n - имя файл, маска, либо регулярное выражение.
-m - искать по макс, либо -f - полное совпадение имени. -r регулярное выражение.
-o - результат записать в файл.
5. Программа должна записывать результат в файл.
6. В программе должна быть валидация ключей и подсказка.
 */

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class FileSearch {
    private List<String> result;

    public FileSearch() {
        result = new ArrayList<>();
    }

    public static void main(String[] args) {
        Args param = new Args(args);
        if (!param.isFail()) {
            FileSearch fileSearch = new FileSearch();
            fileSearch.find(param.getDirectory(),
                    param.getName(),
                    param.getParameter(),
                    param.getOutput());
        }
    }

    public void find(String dir, String name, String parameter, String target) {
        Predicate<File> p = null;
        if (parameter.equals("-m")) {
            String ext = name.replace("*.", "").toLowerCase();
            p = (x -> MyFiles.getFileExtension(x).equals(ext));
        } else if (parameter.equals("-f")) {
            p = (x -> MyFiles.getFileShortName(x).equals(name.toLowerCase()));
        } else if (parameter.equals("-r")) {
            p = (x -> MyFiles.getFileShortName(x).split("\\.")[0].contains(name.toLowerCase()));
        }
        if (p != null) {
            File root = new File(dir);
            Queue<File> data = new LinkedList<>();
            data.offer(root);
            try {
                while (!data.isEmpty()) {
                    File file = data.poll();
                    File[] files = file.listFiles();
                    if (files != null) {
                        for (File f : files) {
                            if (f.isDirectory()) {
                                data.offer(f);
                            } else if (f.isFile()) {
                                if (p.test(f)) {
                                    result.add(f.getCanonicalPath());
                                }
                            }
                        }
                    }

                }
                wright(target);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void wright(String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("./" + target))) {
            for (String s : result) {
                out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
