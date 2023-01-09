package ru.furnygo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileManager {
    public static void initFile(final String name) {
        try {
            final File myObj = new File(name);
            myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(final String str, final String name, final boolean append) {
        initFile(name);
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name, append), StandardCharsets.UTF_8));
            out.write((append ? "\n" : "") + str);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                out.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } finally {
            try {
                out.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static String readFile(final String filename) {
        initFile(filename);
        try {
            final List<String> list = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
            final StringBuilder builder = new StringBuilder();
            for (final String s : list) {
                builder.append(s + "\n");
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}