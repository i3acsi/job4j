package ru.job4j.io;

import com.google.common.base.Joiner;

import org.apache.commons.cli.*;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;


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

public class Args {
    private static final String LN = System.lineSeparator();
    private String directory;
    private String name;
    private String output;
    private String parameter;
    private boolean fail;

    public Args(String[] args) {
        String[] checked = parseArgs(args);
        this.directory = checked[0];
        this.name = checked[1];
        this.output = checked[2];
        this.parameter = checked[3];
    }

    public boolean isFail() {
        return fail;
    }

    public String getOutput() {
        return output;
    }

    public String getName() {
        return name;
    }

    public String getDirectory() {
        return directory;
    }

    public String getParameter() {
        return parameter;
    }

    private String[] parseArgs(String[] args) {
        this.fail = false;
        Map<String, Predicate<String>> map = new HashMap<>();

        map.put("-m", x -> x.startsWith("*."));
        map.put("-f", x -> x.split("\\.").length == 2);
        map.put("-r", x -> x.length() > 0);

        String[] result = new String[4];

        Options options = new Options();

        Option directory = new Option("d", true, "input directory to search");
        directory.setRequired(true);
        options.addOption(directory);

        Option name = new Option("n", true, Joiner.on(LN).join("input parameter  -m and mask to search file by mask",
                "input parameter  -f and file name to search file by name",
                "input parameter  -r and regular expression to search file by regular expression"));
        name.setRequired(true);
        name.setArgs(2);
        options.addOption(name);

        Option output = new Option("o", true, "output file");
        output.setRequired(true);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            String dir = cmd.getOptionValue("d");
            if (!checkDir(dir)) {
                throw new ParseException("wrong input directory");
            } else {
                result[0] = dir;
            }

            String[] tmp = cmd.getOptionValues("n");
            if (map.get(tmp[0]).test(tmp[1])) {
                result[1] = tmp[1];
                result[3] = tmp[0];
            } else {
                throw new ParseException("wrong file parameter name to search");
            }

            String out = cmd.getOptionValue("o");
            if (!checkOut(out)) {
                throw new ParseException("wrong output file");
            } else {
                result[2] = out;
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp(Joiner.on(LN).join("",
                    " -d c:\\project\\job4j\\ -n -m *.java -o result.txt",
                    " -d c:\\project\\job4j\\ -n -f Main.java -o result.txt",
                    " -d c:\\project\\job4j\\ -n -r Main -o result.txt",
                    "--------------------------------------------------"), options);
            this.fail = true;
        }
        return result;
    }

    private static boolean checkDir(String path) {
        File f = new File(path);
        return f.isDirectory();
    }

    private static boolean checkOut(String out) {
        int index = out.lastIndexOf(File.separator);
        String dir;
        if (index != -1) {
            dir = out.substring(0, index);
            if (!checkDir(dir)) {
                return false;
            }
        }
        return out.substring(index + 1).split("\\.").length == 2;
    }
}
