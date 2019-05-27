package util;

import model.Category;
import model.Project;
import model.ProjectStatus;
import repository.CategoryRepository;
import repository.CustomerRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringJoiner;

public class ProjectFileHelper {

    private final static String FILE_NAME = "projects.txt";

    public static ArrayList<Project> read(CustomerRepository customerRepository, CategoryRepository categoryRepository)
    {
        ArrayList<Project> projects = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(FILE_NAME), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Project project = new Project();

                project.id = Long.parseLong(parts[0]);
                project.name = parts[1];
                project.projectStatus = ProjectStatus.valueOf(parts[2]);

                Long customerId = Long.parseLong(parts[3]);
                project.customer = customerRepository.get(customerId);

                String[] cIds = parts[4].split("/");
                project.categories = new HashSet<>();
                for (String id : cIds
                ) {
                    Long categoryId = Long.parseLong(id);
                    project.categories.add(categoryRepository.get(categoryId));
                }

                projects.add(project);
            }
        } catch (IOException e) {
            // log error
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return projects;
    }

    public static void write(ArrayList<Project> projects)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (Project pr : projects) {
                String line = pr.id + "," + pr.name + "," + pr.projectStatus + "," + pr.customer.id + ",";
                StringJoiner joiner = new StringJoiner("/");
                for (Category c : pr.categories
                ) {
                    joiner.add(c.id.toString());
                }
                line += joiner.toString();

                bw.write(line);
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
