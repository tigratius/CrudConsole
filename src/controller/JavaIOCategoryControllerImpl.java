package controller;

import service.CategoryService;
import model.Category;

import java.util.ArrayList;

public class JavaIOCategoryControllerImpl implements CategoryController {

    CategoryService categoryService;

    public JavaIOCategoryControllerImpl(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @Override
    public ArrayList<Category> getAll() {

        return categoryService.getAll();
    }

    @Override
    public void save() {

        categoryService.save();
    }

    @Override
    public Category get(Long id) throws Exception {

        return categoryService.get(id);
    }

    @Override
    public void create(String name) throws Exception {

        categoryService.create(name);
    }

    @Override
    public void update(Long id, String name) throws Exception {

        categoryService.update(id, name);
    }

    @Override
    public void delete(Long id) throws Exception {

        categoryService.delete(id);
    }
}
