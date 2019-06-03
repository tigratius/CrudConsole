package main.java.com.tigratius.crud.service;

import main.java.com.tigratius.crud.model.Category;

public interface CategoryService extends GenericService<Category, Long> {

    void create(String name) throws Exception;

    void update(Long id, String name) throws Exception;
}
