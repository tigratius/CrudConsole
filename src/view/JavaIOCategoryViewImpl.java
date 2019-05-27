package view;

import controller.CategoryController;
import model.Category;

import java.util.ArrayList;
import java.util.Scanner;

public class JavaIOCategoryViewImpl implements BaseView {

    CategoryController categoryController;
    private Scanner sc;

    public JavaIOCategoryViewImpl(CategoryController categoryController, Scanner sc) {
        this.categoryController = categoryController;
        this.sc = sc;
    }

    @Override
    public void show() {

        boolean isExit = false;
        while (true) {
            print();
            System.out.println("----------------------------------------------");
            System.out.println("Выберете действие над категориями:");
            System.out.println(" 1. Создать");
            System.out.println(" 2. Редактировать");
            System.out.println(" 3. Удалить");
            System.out.println(" 4. Вывести список категорий");
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
                    categoryController.save();
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

    public void create()
    {
        System.out.println("----------------------------------------------");
        System.out.println("Создание категории.");
        System.out.println("Введите имя категории:");
        String name = sc.next();
        try {
            categoryController.create(name);
            System.out.println("Категория успешно создана.");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("При создании категории возникла ошибка! Категория не создана!");
        }
        System.out.println("----------------------------------------------");
    }

    public void edit()
    {
        System.out.println("----------------------------------------------");
        System.out.println("Редактирование категории.");
        System.out.println("Введите ID категории, которую редактировать:");
        Long id = sc.nextLong();
        System.out.println("Введите новое имя:");
        String name = sc.next();
        try {
            categoryController.update(id, name);
            System.out.println("Категория успешно отредактирована.");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("При редактировании категории возникла ошибка!");
        }

        System.out.println("----------------------------------------------");
    }

    public void delete()
    {
        System.out.println("----------------------------------------------");
        System.out.println("Удаление категории");
        System.out.println("Введите ID категории, которую удалить:");
        Long id = sc.nextLong();
        try {
            categoryController.delete(id);
            System.out.println("Категория удалена.");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("При удалении категории возникла ошибка!");
        }
        System.out.println("----------------------------------------------");
    }

    public void print()
    {
        ArrayList<Category> categories = categoryController.getAll();
        System.out.println("----------------------------------------------");
        System.out.println("Список категорий:");
        System.out.println("ID; NAME");
        if (categories.size() != 0) {
            for (Category c : categories) {
                System.out.println(c.id + "; " + c.name);
            }
        }
        else
        {
            System.out.println("Категорий нет");
        }
        System.out.println("----------------------------------------------");
    }
}
