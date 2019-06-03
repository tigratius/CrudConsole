package main.java.com.tigratius.crud.view;

import main.java.com.tigratius.crud.controller.CategoryController;
import main.java.com.tigratius.crud.model.BaseEntity;
import main.java.com.tigratius.crud.model.Category;
import main.java.com.tigratius.crud.model.Message;

import java.util.*;

public class JavaIOCategoryViewImpl extends BaseView {

    CategoryController categoryController;
    /*private Scanner sc;*/

    private final String mainMenuMessage = "Выберете действие над категориями:\n" +
            " 1. Создать\n" +
            " 2. Редактировать\n" +
            " 3. Удалить\n" +
            " 4. Вывести список категорий\n" +
            " 5. Выход";

    private final String printMenuMessage = "Список категорий:\n" +
            "ID; NAME";

    private final String createMenuMessage = "Создание категории.\n" +
            Message.NAME.getMessage();

    private final String editMenuMessage = "Редактирование категории.\n" +
            Message.ID.getMessage();

    private final String deleteMenuMessage = "Удаление категории\n" +
            Message.ID.getMessage();


    public JavaIOCategoryViewImpl(CategoryController categoryController, Scanner sc) {
        this.categoryController = categoryController;
        this.sc = sc;
        this.message = mainMenuMessage;
    }

    public void create()
    {
        System.out.println(Message.LINE.getMessage());
        System.out.println(createMenuMessage);
        String name = sc.next();
        try {
            categoryController.create(name);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR_OPERATION.getMessage());
        }
        System.out.println(Message.LINE.getMessage());
    }

    public void edit()
    {
        System.out.println(Message.LINE.getMessage());
        System.out.println(editMenuMessage);
        Long id = sc.nextLong();
        System.out.println(Message.NAME.getMessage());
        String name = sc.next();
        try {
            categoryController.update(id, name);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR_OPERATION.getMessage());
        }

        System.out.println(Message.LINE.getMessage());
    }

    public void delete()
    {
        System.out.println(Message.LINE.getMessage());
        System.out.println(deleteMenuMessage);
        Long id = sc.nextLong();
        try {
            categoryController.delete(id);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR_OPERATION.getMessage());
        }
        System.out.println(Message.LINE.getMessage());
    }

    public void print()
    {
        List<Category> categories;
        try {
            categories = categoryController.getAll();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println(Message.LINE.getMessage());
        System.out.println(printMenuMessage);
        Collections.sort(categories, Comparator.comparing(BaseEntity::getId));
        if (categories.size() != 0) {
            for (Category c : categories) {
                System.out.println(c.getId() + "; " + c.getName());
            }
        }
        else
        {
            System.out.println(Message.EMPTY_LIST.getMessage());
        }
        System.out.println(Message.LINE.getMessage());
    }
}
