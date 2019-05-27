package view;

import controller.CustomerController;
import model.Category;
import model.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class JavaIOCustomerViewImpl implements BaseView {

    private CustomerController customerController;
    private Scanner sc;

    public JavaIOCustomerViewImpl(CustomerController customerController, Scanner sc) {
        this.customerController = customerController;
        this.sc = sc;
    }

    @Override
    public void show() {
        boolean isExit = false;
        while (true) {
            print();
            System.out.println("----------------------------------------------");
            System.out.println("Выберете действие над покупателями:");
            System.out.println(" 1. Создать покупателя");
            System.out.println(" 2. Редактировать покупателя");
            System.out.println(" 3. Удалить покупателя");
            System.out.println(" 4. Вывести список покупателей");
            System.out.println(" 5. Выход");
            System.out.println("----------------------------------------------");

            String response = sc.next();
            switch (response) {
                case "1":
                    create();
                    break;
                case "2":
                    edit();
                    break;
                case "3":
                    delete();
                    break;
                case "4":
                    print();
                    break;
                case "5":
                    customerController.save();
                    isExit = true;
                    break;
                default:
                    System.out.println("Неправильный ввод. Повторите попытку!");
                    break;
            }
            if (isExit)
                break;
        }
    }

    @Override
    public void create() {
        System.out.println("----------------------------------------------");
        System.out.println("Создание покупателя");
        System.out.println("Введите имя:");
        String name = sc.next();
        try {
            customerController.create(name);
            System.out.println("Покупатель успешно создан.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("При создании покупателя возникла ошибка! Покупатель не создан!");
        }
        System.out.println("----------------------------------------------");
    }

    @Override
    public void edit() {
        System.out.println("----------------------------------------------");
        System.out.println("Редактирование покупателя");
        System.out.println("Введите ID покупателя, которого редактировать:");
        Long id = sc.nextLong();
        System.out.println("Введите новое имя: ");
        String name = sc.next();
        try {
            customerController.update(id, name);
            System.out.println("Покупатель успешно отредактирован.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("При редактировании покупателя возникла ошибка!");
        }
        System.out.println("----------------------------------------------");
    }

    @Override
    public void delete() {
        System.out.println("----------------------------------------------");
        System.out.println("Удаление покупателя");
        System.out.println(" Введите ID покупателя, которого удалить:");
        Long id = sc.nextLong();
        try {
            customerController.delete(id);
            System.out.println("Покупатель удален.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("При удалении покупателя возникла ошибка!");
        }

        System.out.println("----------------------------------------------");
    }

    @Override
    public void print() {
        ArrayList<Customer> customers = customerController.getAll();
        System.out.println("----------------------------------------------");
        System.out.println("Список покупателей:");
        System.out.println("ID; NAME");
        if (customers.size() != 0) {
            for (Customer c : customers) {
                System.out.println(c.id + "; " + c.name);
            }
        } else {
            System.out.println("Покупателей нет");
        }
        System.out.println("----------------------------------------------");
    }
}
