package view;

import controller.CustomerController;
import model.BaseEntity;
import model.Customer;
import model.Message;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class JavaIOCustomerViewImpl extends BaseView {

    private CustomerController customerController;
/*    private Scanner sc;*/

    private final String mainMenuMessage = "Выберете действие над покупателями:\n" +
            " 1. Создать\n" +
            " 2. Редактировать\n" +
            " 3. Удалить\n" +
            " 4. Вывести список\n" +
            " 5. Выход";

    private final String printMenuMessage = "Список покупателей:\n" +
            "ID; NAME";

    private final String createMenuMessage = "Создание покупателя.\n" +
            Message.NAME.getMessage();

    private final String editMenuMessage = "Редактирование покупателя.\n" +
            Message.ID.getMessage();

    private final String deleteMenuMessage = "Удаление покупателя\n" +
            Message.ID.getMessage();

    public JavaIOCustomerViewImpl(CustomerController customerController, Scanner sc) {
        this.customerController = customerController;
        this.sc = sc;
        this.message = mainMenuMessage;
    }

    @Override
    public void create() {
        System.out.println(Message.LINE.getMessage());
        System.out.println(createMenuMessage);
        String name = sc.next();
        try {
            customerController.create(name);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        } catch (Exception e) {
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
        System.out.println(Message.NAME.getMessage());
        String name = sc.next();
        try {
            customerController.update(id, name);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        } catch (Exception e) {
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
        try {
            customerController.delete(id);
            System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Message.ERROR_OPERATION.getMessage());
        }

        System.out.println(Message.LINE.getMessage());
    }

    @Override
    public void print() {
        List<Customer> customers;
        try {
            customers = customerController.getAll();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }
        Collections.sort(customers, Comparator.comparing(BaseEntity::getId));
        System.out.println(Message.LINE.getMessage());
        System.out.println(printMenuMessage);
        if (customers.size() != 0) {
            for (Customer c : customers) {
                System.out.println(c.getId() + "; " + c.getName());
            }
        } else {
            System.out.println(Message.EMPTY_LIST.getMessage());
        }
        System.out.println(Message.LINE.getMessage());
    }
}
