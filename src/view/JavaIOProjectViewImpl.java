package view;

import controller.ProjectController;
import model.Category;
import model.Customer;
import model.Project;
import model.ProjectStatus;
import repository.ProjectRepository;

import java.util.*;

public class JavaIOProjectViewImpl implements ProjectView {

    private ProjectController projectController;
    private Scanner sc;
    private BaseView customerView;
    private BaseView categoryView;

    public JavaIOProjectViewImpl(ProjectController projectController, Scanner sc, BaseView customerView, BaseView categoryView) {
        this.projectController = projectController;
        this.sc = sc;
        this.categoryView = categoryView;
        this.customerView = customerView;
    }

    @Override
    public void show() {
        boolean isExit = false;
        while (true) {
            print();
            System.out.println("----------------------------------------------");
            System.out.println("Выберете действие над проектами:");
            System.out.println(" 1. Создать проект");
            System.out.println(" 2. Завершить проект");
            System.out.println(" 3. Редактировать проект");
            System.out.println(" 4. Удалить проект");
            System.out.println(" 5. Вывести список проектов");
            System.out.println(" 6. Выход");
            System.out.println("----------------------------------------------");

            String response = sc.next();

            switch (response) {
                case "1":
                    create();
                    break;
                case "2":
                    finish();
                    break;
                case "3":
                    edit();
                    break;
                case "4":
                    delete();
                    break;
                case "5":
                    print();
                    break;
                case "6":
                    projectController.save();
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
        System.out.println("Создание проекта");
        String name = createProjectName();
        /*ProjectStatus projectStatus = chooseProjectStatus();*/
        Long customerId = chooseCustomer();
        Set<Long> categoryIds = chooseCategories();
        try {
            projectController.create(name, ProjectStatus.ACTIVE, customerId, categoryIds);
            System.out.println("Проект создан!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("При создании проекта возникла ошибка! Проект не создан!");
        }

        System.out.println("----------------------------------------------");
    }

    @Override
    public void edit() {
        System.out.println("----------------------------------------------");
        System.out.println("Редактирование проекта");
        System.out.println("Введите ID проекта, который редактировать: ");
        Long id = sc.nextLong();

        try
        {
            projectController.checkEdit(id);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("При редактировании проекта возникла ошибка!");
            return;
        }

        String name = createProjectName();
        /*ProjectStatus projectStatus = chooseProjectStatus();*/
        Long customerId = chooseCustomer();
        Set<Long> categoryIds = chooseCategories();
        try
        {
            projectController.update(id, name, customerId, categoryIds);
            System.out.println("Проект отредактирован!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("При редактировании проекта возникла ошибка!");
        }

        System.out.println("----------------------------------------------");
    }

    @Override
    public void delete() {
        System.out.println("----------------------------------------------");
        System.out.println("Удаление проекта");
        System.out.println("Введите ID проекта, который удалить: ");
        Long id = sc.nextLong();
        try
        {
            projectController.delete(id);
            System.out.println(" Проект удален!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("При удалении проекта возникла ошибка!");
        }

        System.out.println("----------------------------------------------");
    }

    @Override
    public void print() {
        ArrayList<Project> projects = projectController.getAll();
        System.out.println("----------------------------------------------");
        System.out.println("Список проектов");
        System.out.println("ID; Name; ProjectStatus; CustomerName; Categories");
        if (projects.size() != 0) {
            for (Project p : projects) {
                String printLine = p.id + "; " + p.name + "; " + p.projectStatus + "; " + p.customer.name + "; ";
                StringJoiner joiner = new StringJoiner("/");
                for (Category c : p.categories
                ) {
                    joiner.add(c.name);
                }
                printLine += joiner.toString();
                System.out.println(printLine);
            }
        }
        else
        {
            System.out.println("Проектов нет");
        }
        System.out.println("----------------------------------------------");
    }

    @Override
    public void finish() {
        System.out.println("----------------------------------------------");
        System.out.println("Завершение проекта");
        System.out.println("Введите ID проекта, который завершить: ");
        Long id = sc.nextLong();
        try
        {
            projectController.finish(id);
            System.out.println(" Проект завершен!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("При завершении проекта возникла ошибка!");
        }

        System.out.println("----------------------------------------------");
    }

    /*Private methods*/
    private String createProjectName() {
        System.out.println("----------------------------------------------");
        System.out.println("Введите имя проекта:");
        String name = sc.next();
        System.out.println("----------------------------------------------");
        return name;
    }

    /*private ProjectStatus chooseProjectStatus() {
        System.out.println("----------------------------------------------");
        System.out.println("Выберете статус:");
        List<ProjectStatus> projectStatusList = Arrays.asList(ProjectStatus.values());
        for (ProjectStatus ps : projectStatusList
        ) {
            System.out.print(ps.getValue() + ". " + ps);
        }
        int status = sc.nextInt();
        System.out.println("----------------------------------------------");
        return projectStatusList.get(status - 1);
    }*/

    private Set<Long> chooseCategories() {

        Set<Long> categoryIds = new HashSet<>();
        while (true) {
            categoryView.print();
            System.out.println("----------------------------------------------");
            System.out.println("Выберете ID категории:");
            Long categoryId = sc.nextLong();
            try {
                projectController.checkCategory(categoryId);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                continue;
            }

            if (categoryIds.contains(categoryId))
            {
                System.out.println("Категория с ID = " + categoryId + " уже добавлена! Выберете другую...");
            }
            else
            {
                categoryIds.add(categoryId);
            }

            System.out.println("Хотите добавить еще категорию? (y/n): ");
            String response = sc.next();
            if (!response.equalsIgnoreCase("y")) {
                break;
            }
            System.out.println("----------------------------------------------");
        }

        return categoryIds;
    }

    private Long chooseCustomer() {
        Long customerId;
        while (true) {
            customerView.print();
            System.out.println("----------------------------------------------");
            System.out.println("Выберете ID покупателя:");
            customerId = sc.nextLong();
            try {
                projectController.checkCustomer(customerId);
                break;
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                continue;
            }
        }
        System.out.println("----------------------------------------------");
        return customerId;
    }
}
