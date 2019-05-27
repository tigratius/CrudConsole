package repository;

import model.Category;
import util.CategoryFileHelper;

import java.util.ArrayList;

public class JavaIOCategoryRepositoryImpl implements CategoryRepository {

    private ArrayList<Category> categories;

    public JavaIOCategoryRepositoryImpl() {
        categories = CategoryFileHelper.read();
    }

    @Override
    public Category get(Long id) throws Exception {

        Category current = null;

        for (Category c : categories
        ) {
            if (c.id == id) {
                current = c;
                break;
            }
        }

        if (current != null) {
            return current;
        }

        throw new Exception("ID = " + id + " нет!");
    }

    @Override
    public void add(Category item) {
        item.id = getLastId() + 1;
        categories.add(item);
    }

    @Override
    public void delete(Long id) throws Exception {
        Category category = get(id);
        categories.remove(category);
    }

    @Override
    public void update(Category item) throws Exception {
        Category category = get(item.id);
        category.name = item.name;
    }

    @Override
    public void save() {
        CategoryFileHelper.write(categories);
    }

    @Override
    public ArrayList<Category> getAll() {

        return categories;
    }

    @Override
    public Long getLastId() {
        if (categories.size() != 0) {
            return categories.get(categories.size() - 1).id;
        }

        return 0L;
    }


}
