package ru.job4j.zip;

import ru.job4j.io.MyFiles;

import org.apache.commons.cli.*;
import java.io.File;
import java.util.List;

public class Args {
    private String directory;
    private String exclude;
    private String output;
    private boolean fail;

    public Args(String[] args) {
        String[] checked = parseArgs(args);
        this.directory = checked[0];
        this.exclude = checked[1];
        this.output = checked[2];
    }

    public String output() {
        return output;
    }

    public String exclude() {
        return exclude;
    }

    public String directory() {
        return directory;
    }

    private String[] parseArgs(String[] args) {
        this.fail = false;

        String[] result = new String[3];

        Options options = new Options();

        Option directory = new Option("d", true, "input directory to archive");
        directory.setRequired(true);
        options.addOption(directory);

        Option exclude = new Option("e", true, "input file extensions to exclude");
        exclude.setRequired(true);
        options.addOption(exclude);

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
            String exts = cmd.getOptionValue("e");
            if (!checkExts(exts)) {
                throw new ParseException("wrong file extensions");
            } else {
                result[1] = exts;
            }
            String out = cmd.getOptionValue("o");
            if (!checkOut(out)) {
                throw new ParseException("wrong output file");
            } else {
                result[2] = out;
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp(" -d c:\\project\\job4j\\ -e *.java -o project.zip", options);
            this.fail = true;
        }
        return result;
    }

    public boolean isFail() {
        return fail;
    }

    private static boolean checkDir(String path) {
        File f = new File(path);
        return f.exists() && f.isDirectory();
    }

    private static boolean checkExts(String exts) {
        String[] parts = exts.split(",");
        for (String s : parts) {
            if (!s.startsWith("*.")) {
                return false;
            }
        }
        return true;
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
        String path = out.substring(index + 1);
        File file = new File(path);
        return  (MyFiles.checkFileExtension(file, List.of("zip")));
    }
}
