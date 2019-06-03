package main.java.com.tigratius.crud.service;

import main.java.com.tigratius.crud.model.Customer;

public interface CustomerService extends GenericService<Customer, Long> {

    void create(String name) throws Exception;

    void update(Long id, String name) throws Exception;

}
