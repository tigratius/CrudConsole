package util;

import model.Customer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CustomerFileHelper {

    private final static String FILE_NAME = "customers.txt";

    public static ArrayList<Customer> read()
    {
        ArrayList<Customer> customers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(FILE_NAME), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Customer customer = new Customer();
                customer.id = Long.parseLong(parts[0]);
                customer.name = parts[1];
                customers.add(customer);
            }
        } catch (IOException e) {
            // log error
            System.out.println(e.getMessage());
        }

        return customers;
    }

    public static void write(ArrayList<Customer> customers)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (Customer c : customers) {
                bw.write(c.id + "," + c.name);
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
