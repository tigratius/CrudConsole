package main.java.com.tigratius.crud.service.impl;

import main.java.com.tigratius.crud.repository.CustomerRepository;
import main.java.com.tigratius.crud.model.Customer;
import main.java.com.tigratius.crud.repository.ProjectRepository;
import main.java.com.tigratius.crud.service.CustomerService;

import java.util.List;

public class JavaIOCustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepo;
    private ProjectRepository projectRepository;

    private final String cannotDeleteCustomerMessage = "Невозможно удалить покупателя, т.к. он привязан к проекту!";

    public JavaIOCustomerServiceImpl(CustomerRepository customerRepo, ProjectRepository projectRepository)
    {
        this.customerRepo = customerRepo;
        this.projectRepository = projectRepository;
    }

    @Override
    public Customer getById(Long id) throws Exception {
        return customerRepo.getById(id);
    }

    @Override
    public void create(String name) throws Exception {
        Customer customer = new Customer();
        customer.setId(customerRepo.getLastId() + 1);
        customer.setName(name);
        customerRepo.save(customer);
    }

    @Override
    public void delete(Long id) throws Exception {
        Customer customer = getById(id);

        if (projectRepository.isContainCustomer(customer)) {
            throw new Exception(cannotDeleteCustomerMessage);
        }

        customerRepo.delete(customer);
    }

    @Override
    public void update(Long id, String name) throws Exception {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customerRepo.update(customer);
    }

    @Override
    public List<Customer> getAll() throws Exception {
            return customerRepo.getAll();
    }
}
