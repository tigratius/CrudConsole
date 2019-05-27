package repository;

import model.Category;
import model.Customer;
import model.Project;
import model.ProjectStatus;
import util.ProjectFileHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class JavaIOProjectRepositoryImpl implements ProjectRepository {

    private ArrayList<Project> projects;
    private CustomerRepository customerRepository;
    private CategoryRepository categoryRepository;

    public JavaIOProjectRepositoryImpl(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        projects = ProjectFileHelper.read(customerRepository, categoryRepository);
    }

    @Override
    public Project get(Long id) throws Exception {
        Project current = null;
        for (Project c : projects
        ) {
            if (c.id == id) {
                current = c;
                break;
            }
        }

        if (current != null) {
            return current;
        }

        throw new Exception("ID = " + id + " нет!");
    }

    @Override
    public void add(Project item) throws Exception {
        Customer customer = customerRepository.get(item.customerId);
        Set<Category> categorySet = new HashSet<>();
        for (Long id : item.categoryIds
        ) {
            categorySet.add(categoryRepository.get(id));
        }
        item.customer = customer;
        item.categories = categorySet;
        item.id = getLastId() + 1;
        projects.add(item);
    }

    @Override
    public void delete(Long id) throws Exception {
        Project project = get(id);

        if (project.projectStatus == ProjectStatus.FINISHED) {
            throw new Exception("Нельзя удалить завершенный проект!");
        }

        project.projectStatus = ProjectStatus.DELETED;
    }

    @Override
    public void update(Project item) throws Exception {
        Project project = get(item.id);

        Customer customer = customerRepository.get(item.customerId);
        Set<Category> categorySet = new HashSet<>();
        for (Long id : item.categoryIds
        ) {
            categorySet.add(categoryRepository.get(id));
        }

        project.name = item.name;
        project.categories = categorySet;
        project.customer = customer;
    }

    @Override
    public void save() {
        ProjectFileHelper.write(projects);
    }

    @Override
    public void finish(Long id) throws Exception {
        Project project = get(id);
        if (project.projectStatus == ProjectStatus.DELETED) {
            throw new Exception("Нельзя завершить удаленный проект!");
        }

        project.projectStatus = ProjectStatus.FINISHED;
    }

    @Override
    public void checkEdit(Long id) throws Exception {

        Project project = get(id);

        if (project.projectStatus == ProjectStatus.DELETED) {
            throw new Exception("Нельзя редактировать удаленный проект!");
        }

        if (project.projectStatus == ProjectStatus.FINISHED) {
            throw new Exception("Нельзя редактировать завершенный проект!");
        }
    }

    @Override
    public void checkCategory(Long id) throws Exception {
        try {
            categoryRepository.get(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void checkCustomer(Long id) throws Exception {
        try {
            customerRepository.get(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ArrayList<Project> getAll() {

        return projects;
    }

    @Override
    public Long getLastId() {
        if (projects.size() != 0) {
            return projects.get(projects.size() - 1).id;
        }

        return 0L;
    }

    @Override
    public boolean isContainCategory(Category category) {
        for (Project p : projects
        ) {
            if (p.categories.contains(category))
                return true;
        }

        return false;
    }

    @Override
    public boolean isContainCustomer(Customer customer) {
        for (Project p : projects
        ) {
            if (p.customer == customer)
                return true;
        }

        return false;
    }
}
