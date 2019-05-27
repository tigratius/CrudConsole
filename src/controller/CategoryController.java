package controller;
import model.Category;

public interface CategoryController extends GenericController<Category, Long> {

    void create(String name) throws Exception;

    void update(Long id, String name) throws Exception;
}
