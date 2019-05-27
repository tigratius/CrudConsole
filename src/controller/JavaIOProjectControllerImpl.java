package controller;

import model.Project;
import model.ProjectStatus;
import service.ProjectService;

import java.util.ArrayList;
import java.util.Set;

public class JavaIOProjectControllerImpl implements ProjectController {

    ProjectService projectService;

    public JavaIOProjectControllerImpl(ProjectService projectService)
    {
        this.projectService = projectService;
    }

    @Override
    public ArrayList<Project> getAll() {
        return projectService.getAll();
    }

    @Override
    public void save() {
        projectService.save();
    }

    @Override
    public Project get(Long id) throws Exception {
        return projectService.get(id);
    }

    @Override
    public void create(String name, ProjectStatus projectStatus, Long customerId, Set<Long> categoryIds) throws Exception {
        projectService.create(name, projectStatus, customerId, categoryIds);
    }

    @Override
    public void update(Long id, String name, Long customerId, Set<Long> categoryIds) throws Exception {
        projectService.update(id, name, customerId, categoryIds);
    }

    @Override
    public void finish(Long id) throws Exception {
        projectService.finish(id);
    }

    @Override
    public void checkEdit(Long id) throws Exception {
        projectService.checkEdit(id);
    }

    @Override
    public void checkCategory(Long id) throws Exception {
        projectService.checkCategory(id);
    }

    @Override
    public void checkCustomer(Long id) throws Exception {
        projectService.checkCustomer(id);
    }

    @Override
    public void delete(Long id) throws Exception {
        projectService.delete(id);
    }
}
