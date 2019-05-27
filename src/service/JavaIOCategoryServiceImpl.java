package service;

import repository.CategoryRepository;
import model.Category;
import repository.ProjectRepository;

import java.util.ArrayList;

public class JavaIOCategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepo;
    private ProjectRepository projectRepository;

    public JavaIOCategoryServiceImpl(CategoryRepository categoryRepo, ProjectRepository projectRepository) {
        this.categoryRepo = categoryRepo;
        this.projectRepository = projectRepository;
    }

    @Override
    public Category get(Long id) throws Exception {
        return categoryRepo.get(id);
    }

    @Override
    public void create(String name) throws Exception {
        Category category = new Category();
        category.name = name;
        categoryRepo.add(category);
    }

    @Override
    public void delete(Long id) throws Exception {

        if (projectRepository.isContainCategory(categoryRepo.get(id)))
        {
            throw new Exception("Невозможно удалить категорию, т.к. она привязана к проекту!");
        }

        categoryRepo.delete(id);
    }

    @Override
    public void update(Long id, String name) throws Exception {
        Category category = new Category();
        category.id = id;
        category.name = name;
        categoryRepo.update(category);
    }

    @Override
    public ArrayList<Category> getAll() {
        return categoryRepo.getAll();
    }

    @Override
    public void save() {
        categoryRepo.save();
    }
}
