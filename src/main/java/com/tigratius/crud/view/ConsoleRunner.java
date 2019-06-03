package main.java.com.tigratius.crud.view;

import main.java.com.tigratius.crud.controller.*;
import main.java.com.tigratius.crud.repository.*;
import main.java.com.tigratius.crud.repository.io.JavaIOCategoryRepositoryImpl;
import main.java.com.tigratius.crud.repository.io.JavaIOCustomerRepositoryImpl;
import main.java.com.tigratius.crud.repository.io.JavaIOProjectRepositoryImpl;
import main.java.com.tigratius.crud.service.*;
import main.java.com.tigratius.crud.service.impl.JavaIOCategoryServiceImpl;
import main.java.com.tigratius.crud.service.impl.JavaIOCustomerServiceImpl;
import main.java.com.tigratius.crud.service.impl.JavaIOProjectServiceImpl;
import main.java.com.tigratius.crud.model.Message;

import java.util.Scanner;

public class ConsoleRunner {

    BaseView categoryView;
    BaseView projectView;
    BaseView customerView;

    private final String damagedDataMessage = "Данные повреждены!";

    private final String menuMessage = "Выберете действие:\n" +
                                        "1. Категории\n" +
                                        "2. Покупатели\n" +
                                        "3. Проекты\n" +
                                        "4. Выход";


    private Scanner sc = new Scanner(System.in);

    public ConsoleRunner(){
        try {
            //create repo
            CategoryRepository categoryRepo = new JavaIOCategoryRepositoryImpl();
            CustomerRepository customerRepo = new JavaIOCustomerRepositoryImpl();
            ProjectRepository projectRepo = new JavaIOProjectRepositoryImpl(categoryRepo, customerRepo);

            //create services
            CustomerService customerService = new JavaIOCustomerServiceImpl(customerRepo, projectRepo);
            CategoryService categoryService = new JavaIOCategoryServiceImpl(categoryRepo, projectRepo);
            ProjectService projectService = new JavaIOProjectServiceImpl(projectRepo);

            //create controllers
            ProjectController projectController = new ProjectController(projectService);
            CategoryController categoryController = new CategoryController(categoryService);
            CustomerController customerController = new CustomerController(customerService);

            //create views
            categoryView = new JavaIOCategoryViewImpl(categoryController, sc);
            customerView = new JavaIOCustomerViewImpl(customerController, sc);
            projectView = new JavaIOProjectViewImpl(projectController, sc, customerView, categoryView);
        }
        catch (Exception ex)
        {
            System.out.println(damagedDataMessage);
        }
    }

    public void run()  {
        boolean isExit = false;
        while (true) {
            System.out.println(Message.LINE.getMessage());
            System.out.println(menuMessage);
            System.out.println(Message.LINE.getMessage());
            String response = sc.next();
            switch (response)
            {
                case "1":
                    categoryView.show();
                    break;
                case "2":
                    customerView.show();
                    break;
                case "3":
                    projectView.show();
                    break;
                case "4":
                    isExit = true;
                    break;
                default:
                    System.out.println(Message.ERROR_INPUT.getMessage());
                    break;
            }
            if (isExit)
                break;
        }
        sc.close();
    }
}
