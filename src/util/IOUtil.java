package util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IOUtil {

    private final static String pathToFile = "\\src\\main\\resources\\files\\";

    public static List<String> read(String filename)
    {
        List<String> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(GetPath(filename)), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            // log error
            System.out.println(e.getMessage());
        }

        return data;
    }

    public static void write(String filename, String data)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GetPath(filename), true))) {
            bw.write(data);
            bw.newLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void writeList(String filename, List<String> data)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GetPath(filename), false))) {
            for (String str : data) {
                bw.write(str);
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static String GetPath(String filename)
    {
        Path currentAbsolutePath = Paths.get("").toAbsolutePath();
        String path = currentAbsolutePath + pathToFile + filename;
        return path;
    }
}
