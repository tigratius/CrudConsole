package main.java.com.tigratius.crud.repository;

import main.java.com.tigratius.crud.model.Category;
import main.java.com.tigratius.crud.model.Customer;
import main.java.com.tigratius.crud.model.Project;

public interface ProjectRepository extends GenericRepository<Project, Long> {

    boolean isContainCategory(Category category) throws Exception;
    boolean isContainCustomer(Customer customer) throws Exception;
    void checkEdit(Long id) throws Exception;
    void checkCategory(Long id) throws Exception;
    void checkCustomer(Long id) throws Exception;
}
