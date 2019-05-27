package service;

import model.Category;

public interface CategoryService extends GenericService<Category, Long> {

    void create(String name) throws Exception;

    void update(Long id, String name) throws Exception;
}
