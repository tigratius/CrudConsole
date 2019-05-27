package service;

import model.Category;
import model.Customer;
import model.ProjectStatus;
import repository.CategoryRepository;
import repository.CustomerRepository;
import repository.ProjectRepository;
import model.Project;

import java.util.ArrayList;
import java.util.Set;

public class JavaIOProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepo;

    public JavaIOProjectServiceImpl(ProjectRepository projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Override
    public Project get(Long id) throws Exception {
        return projectRepo.get(id);
    }

    @Override
    public void delete(Long id) throws Exception {
        projectRepo.delete(id);
    }

    @Override
    public void create(String name, ProjectStatus projectStatus, Long customerId, Set<Long> categoryIds) throws Exception {
        Project project = new Project();
        project.name = name;
        project.projectStatus = projectStatus;
        project.categoryIds = categoryIds;
        project.customerId = customerId;
        projectRepo.add(project);
    }

    @Override
    public void update(Long id, String name, Long customerId, Set<Long> categoryIds) throws Exception {
        Project project = new Project();
        project.id = id;
        project.name = name;
        project.categoryIds = categoryIds;
        project.customerId = customerId;
        projectRepo.update(project);
    }

    @Override
    public void finish(Long id) throws Exception {
        projectRepo.finish(id);
    }

    @Override
    public void checkEdit(Long id) throws Exception {
        projectRepo.checkEdit(id);
    }

    @Override
    public void checkCategory(Long id) throws Exception {
        projectRepo.checkCategory(id);
    }

    @Override
    public void checkCustomer(Long id) throws Exception {
        projectRepo.checkCustomer(id);
    }

    @Override
    public ArrayList<Project> getAll() {
        return projectRepo.getAll();
    }

    @Override
    public void save() {
        projectRepo.save();
    }


}
