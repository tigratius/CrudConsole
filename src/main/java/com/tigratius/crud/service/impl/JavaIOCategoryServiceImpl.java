package main.java.com.tigratius.crud.service.impl;

import main.java.com.tigratius.crud.model.Category;
import main.java.com.tigratius.crud.repository.CategoryRepository;
import main.java.com.tigratius.crud.repository.ProjectRepository;
import main.java.com.tigratius.crud.service.CategoryService;

import java.util.List;

public class JavaIOCategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepo;
    private ProjectRepository projectRepository;

    private final String cannotDeleteCategoryMessage = "Невозможно удалить категорию, т.к. она привязана к проекту!";


    public JavaIOCategoryServiceImpl(CategoryRepository categoryRepo, ProjectRepository projectRepository) {
        this.categoryRepo = categoryRepo;
        this.projectRepository = projectRepository;
    }

    @Override
    public Category getById(Long id) throws Exception {
        return categoryRepo.getById(id);
    }

    @Override
    public void create(String name) throws Exception {
        Category category = new Category();
        category.setId(categoryRepo.getLastId() + 1);
        category.setName(name);
        categoryRepo.save(category);
    }

    @Override
    public void delete(Long id) throws Exception {
        Category category = getById(id);
        if (projectRepository.isContainCategory(category)) {
            throw new Exception(cannotDeleteCategoryMessage);
        }
        categoryRepo.delete(category);
    }

    @Override
    public void update(Long id, String name) throws Exception {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        categoryRepo.update(category);
    }

    @Override
    public List<Category> getAll() throws Exception {
        return categoryRepo.getAll();
    }
}
