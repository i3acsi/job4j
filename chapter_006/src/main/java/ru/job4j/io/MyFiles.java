package ru.job4j.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class MyFiles {
    private MyFiles() {
    }

    public static boolean checkFileExtension(File file, List<String> exts) {
        boolean result = false;
        String ext = getFileExtension(file);
        for (String e : exts) {
            if (ext.equals(e)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    public static boolean deleteDirectory(File dir) {
        boolean result = false;
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                File f = new File(dir, children[i]);
                deleteDirectory(f);
            }
            result = dir.delete();
        } else {
            result = dir.delete();
        }
        return result;
    }

    public static boolean makeDir(String path) {
        boolean result = false;
        File file = new File(path);
        if (!file.exists()) {
            result = file.mkdir();
        }
        return result;
    }

    public static boolean addFile(String name) {
        boolean result = false;
        try (PrintWriter out = new PrintWriter(new FileOutputStream(name))) {
            out.println("i");
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
