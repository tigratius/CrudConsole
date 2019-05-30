package view;

import controller.ProjectController;
import model.*;

import java.util.*;

public class JavaIOProjectViewImpl extends ProjectView {

    private ProjectController projectController;
    /*private Scanner sc;*/
    private BaseView customerView;
    private BaseView categoryView;

    private final String mainMenuMessage = "Выберете действие над проектами:\n" +
            " 1. Создать проект\n" +
            " 2. Завершить проект\n" +
            " 3. Редактировать проект\n" +
            " 4. Удалить проект\n" +
            " 5. Вывести список проектов\n" +
            " 6. Выход";

    private final String printMenuMessage = "Список проектов\n" +
            "ID; Name; ProjectStatus; CustomerName; Categories";

    private final String createMenuMessage = "Создание проекта.";

    private final String editMenuMessage = "Редактирование проекта.\n" +
            Message.ID.getMessage();

    private final String deleteMenuMessage = "Удаление проекта.\n" +
            Message.ID.getMessage();

    private final String finishMenuMessage = "Завершение проекта.\n" +
            Message.ID.getMessage();

    private final String addSameCategoryMessage = "Категория уже добавлена! Выберете другую...\n" +
                                                    "ID = ";

    private final String wantAddCategoryMessage = "Хотите добавить еще категорию? (y/n):";

    private final String answerYes = "y";

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
            System.out.println(Message.LINE.getMessage());
            System.out.println(mainMenuMessage);
            System.out.println(Message.LINE.getMessage());

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
                    isExit = true;
                    break;
                default:
                    System.out.println(Message.ERROR_INPUT.getMessage());
                    break;
            }

            if (isExit)
                break;
        }
    }

    @Override
    public void create() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(createMenuMessage);
        String name = createProjectName();
        Long customerId = chooseCustomer();
        Set<Long> categoryIds = chooseCategories();
        try {
            projectController.create(name, ProjectStatus.ACTIVE, customerId, categoryIds);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR_OPERATION.getMessage());
        }

        System.out.println(Message.LINE.getMessage());
    }

    @Override
    public void edit() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(editMenuMessage);
        Long id = sc.nextLong();

        try
        {
            projectController.checkEdit(id);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR_OPERATION.getMessage());
            return;
        }

        String name = createProjectName();
        Long customerId = chooseCustomer();
        Set<Long> categoryIds = chooseCategories();
        try
        {
            projectController.update(id, name, customerId, categoryIds);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR_OPERATION.getMessage());
        }

        System.out.println(Message.LINE.getMessage());
    }

    @Override
    public void delete() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(deleteMenuMessage);
        Long id = sc.nextLong();
        try
        {
            projectController.delete(id);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR_OPERATION.getMessage());
        }

        System.out.println(Message.LINE.getMessage());
    }

    @Override
    public void print() {
        List<Project> projects;
        try {
            projects = projectController.getAll();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }
        Collections.sort(projects, Comparator.comparing(BaseEntity::getId));
        System.out.println(Message.LINE.getMessage());
        System.out.println(printMenuMessage);
        if (projects.size() != 0) {
            for (Project p : projects) {
                String printLine = p.getId() + "; " + p.getName() + "; " + p.getProjectStatus() + "; " + p.getCustomer().getName() + "; ";
                StringJoiner joiner = new StringJoiner("/");
                for (Category c : p.getCategories()
                ) {
                    joiner.add(c.getName());
                }
                printLine += joiner.toString();
                System.out.println(printLine);
            }
        }
        else
        {
            System.out.println(Message.EMPTY_LIST.getMessage());
        }
        System.out.println(Message.LINE.getMessage());
    }

    @Override
    public void finish() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(finishMenuMessage);
        Long id = sc.nextLong();
        try
        {
            projectController.finish(id);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR_OPERATION.getMessage());
        }

        System.out.println(Message.LINE.getMessage());
    }

    /*Private methods*/
    private String createProjectName() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(Message.NAME.getMessage());
        String name = sc.next();
        System.out.println(Message.LINE.getMessage());
        return name;
    }

    private Set<Long> chooseCategories() {

        Set<Long> categoryIds = new HashSet<>();
        while (true) {
            categoryView.print();
            System.out.println(Message.LINE.getMessage());
            System.out.println(Message.ID.getMessage());
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
                System.out.println(addSameCategoryMessage + categoryId);
            }
            else
            {
                categoryIds.add(categoryId);
            }

            System.out.println(wantAddCategoryMessage);
            String response = sc.next();
            if (!response.equalsIgnoreCase(answerYes)) {
                break;
            }
            System.out.println(Message.LINE.getMessage());
        }

        return categoryIds;
    }

    private Long chooseCustomer() {
        Long customerId;
        while (true) {
            customerView.print();
            System.out.println(Message.LINE.getMessage());
            System.out.println(Message.ID.getMessage());
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
        System.out.println(Message.LINE.getMessage());
        return customerId;
    }
}
