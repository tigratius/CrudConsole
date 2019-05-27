package service;

import model.Project;
import model.ProjectStatus;

import java.util.Set;

public interface ProjectService extends GenericService<Project, Long> {

    void create(String name, ProjectStatus projectStatus, Long customerId, Set<Long> categoryIds) throws Exception;

    void update(Long id, String name, Long customerId, Set<Long> categoryIds) throws Exception;

    void finish(Long id) throws Exception;

    void checkEdit(Long id) throws Exception;

    void checkCategory(Long id) throws Exception;

    void checkCustomer(Long id) throws Exception;
}
