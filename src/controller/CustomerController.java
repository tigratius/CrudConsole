package controller;

import model.Customer;
import service.CustomerService;

import java.util.List;

public class CustomerController{

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }

    public List<Customer> getAll() throws Exception {

        return customerService.getAll();
    }

    /*public void save() {

        customerService.save();
    }*/

    public Customer getById(Long id) throws Exception {

        return customerService.getById(id);
    }

    public void create(String name) throws Exception {

        customerService.create(name);
    }

    public void update(Long id, String name) throws Exception {

        customerService.update(id, name);
    }

    public void delete(Long id) throws Exception {

        customerService.delete(id);
    }
}
