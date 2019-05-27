package service;

import repository.CustomerRepository;
import model.Customer;
import repository.ProjectRepository;

import java.util.ArrayList;

public class JavaIOCustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepo;
    private ProjectRepository projectRepository;

    public JavaIOCustomerServiceImpl(CustomerRepository customerRepo, ProjectRepository projectRepository)
    {
        this.customerRepo = customerRepo;
        this.projectRepository = projectRepository;
    }

    @Override
    public Customer get(Long id) throws Exception {
        return customerRepo.get(id);
    }

    @Override
    public void create(String name) throws Exception {
        Customer customer = new Customer();
        customer.name = name;
        customerRepo.add(customer);
    }

    @Override
    public void delete(Long id) throws Exception {

        if (projectRepository.isContainCustomer(customerRepo.get(id))) {
            throw new Exception("Невозможно удалить покупателя, т.к. он привязан к проекту!");
        }

        customerRepo.delete(id);
    }

    @Override
    public void update(Long id, String name) throws Exception {
        Customer customer = new Customer();
        customer.id = id;
        customer.name = name;
        customerRepo.update(customer);
    }

    @Override
    public ArrayList<Customer> getAll()
    {
        return customerRepo.getAll();
    }

    @Override
    public void save()
    {
        customerRepo.save();
    }
}
