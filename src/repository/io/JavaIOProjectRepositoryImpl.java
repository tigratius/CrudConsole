package repository.io;

import model.Category;
import model.Customer;
import model.Project;
import model.ProjectStatus;
import repository.CategoryRepository;
import repository.CustomerRepository;
import repository.ProjectRepository;
import util.IOUtil;

import java.util.*;

public class JavaIOProjectRepositoryImpl implements ProjectRepository {

    /*private List<Project> projects;*/
    private CustomerRepository customerRepository;
    private CategoryRepository categoryRepository;

    private final static String FILE_NAME = "projects.txt";

    private final String cannotEditFinishedProjectMessage = "Нельзя редактировать завершенный проект!";
    private final String cannotEditDeletedProjectMessage = "Нельзя редактировать удаленный проект!";

    public JavaIOProjectRepositoryImpl(CategoryRepository categoryRepository, CustomerRepository customerRepository) throws Exception {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;

    }

    @Override
    public Project getById(Long id) throws Exception {
        List<Project> projects = stringToData(IOUtil.read(FILE_NAME));

        Project current = null;
        for (Project c : projects
        ) {
            if (c.getId() == id) {
                current = c;
                break;
            }
        }

        if (current != null) {
            return current;
        }

        throw new Exception("ID = " + id + " нет!");
    }

    /*@Override
    public void add(Project item) throws Exception {
        Customer customer = customerRepository.getById(item.getCustomerId());
        Set<Category> categorySet = new HashSet<>();
        for (Long id : item.getCategoryIds()
        ) {
            categorySet.add(categoryRepository.getById(id));
        }
        item.setCustomer(customer);
        item.setCategories(categorySet);
        item.setId(getLastId() + 1);
        projects.add(item);
    }*/

    @Override
    public void delete(Project item) throws Exception {
        List<Project> projects = stringToData( IOUtil.read(FILE_NAME));
        Project removeProject = null;
        for (Project c: projects
        ) {
            if (c.getId() == item.getId())
            {
                removeProject = c;
                break;
            }
        }

        projects.remove(removeProject);
        IOUtil.writeList(FILE_NAME, dataToString(projects));
    }

    @Override
    public void update(Project item) throws Exception {
        delete(getById(item.getId()));
        save(item);
    }

    @Override
    public void save(Project project) {

        IOUtil.write(FILE_NAME, dataToString(project));
    }

    @Override
    public void checkEdit(Long id) throws Exception {

        Project project = getById(id);

        if (project.getProjectStatus() == ProjectStatus.DELETED) {
            throw new Exception(cannotEditDeletedProjectMessage);
        }

        if (project.getProjectStatus() == ProjectStatus.FINISHED) {
            throw new Exception(cannotEditFinishedProjectMessage);
        }
    }

    @Override
    public void checkCategory(Long id) throws Exception {
        try {
            categoryRepository.getById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void checkCustomer(Long id) throws Exception {
        try {
            customerRepository.getById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Project> getAll() throws Exception {
        return stringToData(IOUtil.read(FILE_NAME));
    }

    @Override
    public Long getLastId() throws Exception {

        List<Project> projects = stringToData(IOUtil.read(FILE_NAME));
        if (projects.size() != 0) {
            return projects.get(projects.size() - 1).getId();
        }

        return 0L;
    }

    @Override
    public List<Project> stringToData(List<String> items) throws Exception {
        List<Project> projects = new ArrayList<>();

        for (String str : items
        ) {
            String[] parts = str.split(",");
            Project project = new Project();

            project.setId(Long.parseLong(parts[0]));
            project.setName(parts[1]);
            project.setProjectStatus(ProjectStatus.valueOf(parts[2]));

            Long customerId = Long.parseLong(parts[3]);
            project.setCustomerId(customerId);
            project.setCustomer(customerRepository.getById(customerId));

            String[] cIds = parts[4].split("/");
            HashSet<Category> categories = new HashSet<>();
            HashSet<Long> categoryIds = new HashSet<>();
            for (String id : cIds
            ) {
                Long categoryId = Long.parseLong(id);
                categoryIds.add(categoryId);
                categories.add(categoryRepository.getById(categoryId));
            }

            project.setCategoryIds(categoryIds);
            project.setCategories(categories);

            projects.add(project);
        }

        return projects;
    }

    @Override
    public List<String> dataToString(List<Project> items) {
        List<String> data = new ArrayList<>();
        for (Project pr : items) {
            data.add(dataToString(pr));
        }

        return data;
    }

    @Override
    public String dataToString(Project pr) {
        String data = pr.getId() + "," + pr.getName() + "," + pr.getProjectStatus() + "," + pr.getCustomerId() + ",";
        StringJoiner joiner = new StringJoiner("/");
        for (Long c : pr.getCategoryIds()
        ) {
            joiner.add(c+"");
        }
        data += joiner;

        return data;
    }

    @Override
    public boolean isContainCategory(Category category) throws Exception {
        List<Project> projects = stringToData(IOUtil.read(FILE_NAME));
        for (Project p : projects
        ) {
            if (p.getCategories().contains(category))
                return true;
        }

        return false;
    }

    @Override
    public boolean isContainCustomer(Customer customer) throws Exception {
        List<Project> projects = stringToData(IOUtil.read(FILE_NAME));
        for (Project p : projects
        ) {
            if (p.getCustomer() == customer)
                return true;
        }

        return false;
    }
}
