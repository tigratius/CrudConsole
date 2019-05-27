package util;

import model.Category;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CategoryFileHelper {

    private final static String FILE_NAME = "categories.txt";

    public static ArrayList<Category> read()
    {
        ArrayList<Category> categories = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(FILE_NAME), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Category category = new Category();
                category.id = Long.parseLong(parts[0]);
                category.name = parts[1];
                categories.add(category);
            }
        } catch (IOException e) {
            // log error
            System.out.println(e.getMessage());
        }

        return categories;
    }

    public static void write(ArrayList<Category> categories)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (Category c : categories) {
                bw.write(c.id + "," + c.name);
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
