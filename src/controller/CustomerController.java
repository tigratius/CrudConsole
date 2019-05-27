package controller;
import model.Customer;

public interface CustomerController extends GenericController<Customer, Long> {

    void create(String name) throws Exception;

    void update(Long id, String name) throws Exception;
}
