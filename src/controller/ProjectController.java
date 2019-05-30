package controller;

import model.Project;
import model.ProjectStatus;
import service.ProjectService;

import java.util.List;
import java.util.Set;

public class ProjectController{

    ProjectService projectService;

    public ProjectController(ProjectService projectService)
    {
        this.projectService = projectService;
    }

    public List<Project> getAll() throws Exception {
        return projectService.getAll();
    }

    /*public void save() {
        projectService.save();
    }*/

    public Project getById(Long id) throws Exception {
        return projectService.getById(id);
    }

    public void create(String name, ProjectStatus projectStatus, Long customerId, Set<Long> categoryIds) throws Exception {
        projectService.create(name, projectStatus, customerId, categoryIds);
    }

    public void update(Long id, String name, Long customerId, Set<Long> categoryIds) throws Exception {
        projectService.update(id, name, customerId, categoryIds);
    }

    public void finish(Long id) throws Exception {
        projectService.finish(id);
    }

    public void checkEdit(Long id) throws Exception {
        projectService.checkEdit(id);
    }

    public void checkCategory(Long id) throws Exception {
        projectService.checkCategory(id);
    }

    public void checkCustomer(Long id) throws Exception {
        projectService.checkCustomer(id);
    }

    public void delete(Long id) throws Exception {
        projectService.delete(id);
    }
}
