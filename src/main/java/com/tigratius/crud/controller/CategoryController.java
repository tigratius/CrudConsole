package main.java.com.tigratius.crud.controller;

import main.java.com.tigratius.crud.model.Category;
import main.java.com.tigratius.crud.service.CategoryService;

import java.util.List;

public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    public List<Category> getAll() throws Exception {

        return categoryService.getAll();
    }

    public Category getById(Long id) throws Exception {

        return categoryService.getById(id);
    }

    public void create(String name) throws Exception {

        categoryService.create(name);
    }

    public void update(Long id, String name) throws Exception {

        categoryService.update(id, name);
    }

    public void delete(Long id) throws Exception {

        categoryService.delete(id);
    }
}
