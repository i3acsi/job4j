package ru.job4j.zip;

public class ZipMain {

    public static void main(String[] args) {
        Args param = new Args(args);
        if (!param.isFail()) {
            Zip zip = new Zip();
            zip.pack(param.directory(), param.exclude(), param.output());
        }
    }
}
