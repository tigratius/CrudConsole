package main.java.com.tigratius.crud.service.impl;

import main.java.com.tigratius.crud.model.Project;
import main.java.com.tigratius.crud.model.ProjectStatus;
import main.java.com.tigratius.crud.repository.ProjectRepository;
import main.java.com.tigratius.crud.service.ProjectService;

import java.util.List;
import java.util.Set;

public class JavaIOProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepo;

    private final String cannotDeleteFinishedProjectMessage = "Нельзя удалить завершенный проект!";
    private final String cannotFinishDeletedProjectMessage = "Нельзя завершить удаленный проект!";

    public JavaIOProjectServiceImpl(ProjectRepository projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Override
    public Project getById(Long id) throws Exception {
        return projectRepo.getById(id);
    }

    @Override
    public void delete(Long id) throws Exception {
        Project project = getById(id);

        if (project.getProjectStatus() == ProjectStatus.FINISHED) {
            throw new Exception(cannotDeleteFinishedProjectMessage);
        }
        project.setProjectStatus(ProjectStatus.DELETED);

        projectRepo.update(project);
    }

    @Override
    public void create(String name, ProjectStatus projectStatus, Long customerId, Set<Long> categoryIds) throws Exception {
        Project project = new Project();
        project.setId(projectRepo.getLastId() + 1);
        project.setName(name);
        project.setProjectStatus(projectStatus);
        project.setCategoryIds(categoryIds);
        project.setCustomerId(customerId);
        projectRepo.save(project);
    }

    @Override
    public void update(Long id, String name, Long customerId, Set<Long> categoryIds) throws Exception {
        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setProjectStatus(ProjectStatus.ACTIVE);
        project.setCategoryIds(categoryIds);
        project.setCustomerId(customerId);
        projectRepo.update(project);
    }

    @Override
    public void finish(Long id) throws Exception {
        Project project = getById(id);
        if (project.getProjectStatus() == ProjectStatus.DELETED) {
            throw new Exception(cannotFinishDeletedProjectMessage);
        }
        project.setProjectStatus(ProjectStatus.FINISHED);

        projectRepo.update(project);
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
    public List<Project> getAll() throws Exception {

        return projectRepo.getAll();
    }
}
