import controller.*;
import repository.*;
import service.*;
import view.*;

import java.util.Scanner;

public class ConsoleRunner {

    BaseView categoryView;
    BaseView projectView;
    BaseView customerView;

    private Scanner sc = new Scanner(System.in);

    public ConsoleRunner()
    {
        //create repo
        CategoryRepository categoryRepo = new JavaIOCategoryRepositoryImpl();
        CustomerRepository customerRepo = new JavaIOCustomerRepositoryImpl();
        ProjectRepository projectRepo = new JavaIOProjectRepositoryImpl(categoryRepo, customerRepo);

        //create services
        CustomerService customerService = new JavaIOCustomerServiceImpl(customerRepo, projectRepo);
        CategoryService categoryService= new JavaIOCategoryServiceImpl(categoryRepo, projectRepo);
        ProjectService projectService = new JavaIOProjectServiceImpl(projectRepo);

        //create controllers
        ProjectController projectController = new JavaIOProjectControllerImpl(projectService);
        CategoryController categoryController = new JavaIOCategoryControllerImpl(categoryService);
        CustomerController customerController = new JavaIOCustomerControllerImpl(customerService);

        //create views
        categoryView = new JavaIOCategoryViewImpl(categoryController, sc);
        customerView = new JavaIOCustomerViewImpl(customerController, sc);
        projectView = new JavaIOProjectViewImpl(projectController, sc, customerView, categoryView);
    }

    public void run()  {
        boolean isExit = false;
        while (true) {
            System.out.println("----------------------------------------------");
            System.out.println("Выберете действие:");
            System.out.println(" 1. Категории");
            System.out.println(" 2. Покупатели");
            System.out.println(" 3. Проекты");
            System.out.println(" 4. Выход");
            System.out.println("----------------------------------------------");
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
                    System.out.println("Неправильный ввод. Повторите попытку!");
                    break;
            }
            if (isExit)
                break;
        }
        sc.close();
    }
}
