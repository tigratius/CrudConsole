package controller;

import service.CustomerService;
import model.Customer;

import java.util.ArrayList;

public class JavaIOCustomerControllerImpl implements CustomerController {

    CustomerService customerService;

    public JavaIOCustomerControllerImpl(CustomerService customerService) {

        this.customerService = customerService;
    }

    @Override
    public ArrayList<Customer> getAll() {

        return customerService.getAll();
    }

    @Override
    public void save() {

        customerService.save();
    }

    @Override
    public Customer get(Long id) throws Exception {

        return customerService.get(id);
    }

    @Override
    public void create(String name) throws Exception {

        customerService.create(name);
    }

    @Override
    public void update(Long id, String name) throws Exception {

        customerService.update(id, name);
    }

    @Override
    public void delete(Long id) throws Exception {

        customerService.delete(id);
    }
}
