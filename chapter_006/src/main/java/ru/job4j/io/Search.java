package ru.job4j.io;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Search {
    private List<File> result = new ArrayList<>();
    private List<String> exts;

    /**
     * В этой задаче нужно использовать методы.
     * File file = new File(path);
     * file.listFiles() - возвращает список всех каталогов и файлов находящихся в папке  file.
     * file.isDirectory() - проверяет, что файл является директорией.
     * file.getName() - возвращает имя файла с расширением.
     * В этой задаче нужно написать тесты. Для тестов нужно создать временную структуру с файлами.
     * Структуру нужно создавать в папке System.getProperty("java.io.tmpdir")
     * Здесь нельзя использовать FileVisitor. Это задание на работу с деревом каталогов.
     *
     * @param parent -  путь до каталога, с которого нужно осуществлять поиск.
     * @param exts   - расширения файлов, которые мы ходим получить.
     * @return List<File> - список всех файлов с конкретным расширением
     */
    public List<File> files(String parent, List<String> exts, boolean include) {
        this.exts = exts;
        inOrder(parent, include);
        return this.result;
    }

    private void inOrder(String root, boolean include) {
        File file = new File(root);
        File[] temp = file.listFiles();
        List<File> files = new ArrayList<>();
        List<File> dirs = new ArrayList<>();
        if (temp != null && temp.length != 0) {
            for (File f : temp) {
                if (f.isDirectory()) {
                    dirs.add(f);
                } else {
                    if (MyFiles.checkFileExtension(f, exts) == include) {
                        files.add(f);
                    }
                }
            }
            if (dirs.size() != 0) {
                inOrder(dirs.remove(0).toString(), include);
            }
            result.addAll(files);
            for (File f : dirs) {
                inOrder(f.toString(), include);
            }
        }
    }
}
/*   ///Post-order///
        File file = new File(root);
        var temp = file.listFiles();
        List<File> files = new ArrayList<>();
        if (temp.length != 0) {
            for (File f : temp) {
                if (f.isDirectory()) {
                    inOrder(f.toString());
                } else {
                    files.add(f);
                }
            }
            result.addAll(files);
        }
 */
/*   ///Pre-order///
        File file = new File(root);
        var temp = file.listFiles();
        List<File> files = new ArrayList<>();
        if (temp.length != 0) {
            for (File f : temp) {
                if (f.isDirectory()) {
                    inOrder(f.toString());
                } else {
                    result.add(f);
                }
            }
        }
    }
 */

