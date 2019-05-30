package repository.io;

import model.BaseEntity;
import model.Category;
import model.Message;
import repository.CategoryRepository;
import util.IOUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JavaIOCategoryRepositoryImpl implements CategoryRepository {

    private final static String FILE_NAME = "categories.txt";

    public JavaIOCategoryRepositoryImpl() {
    }

    @Override
    public Category getById(Long id) throws Exception {

        List<Category> categories = stringToData(IOUtil.read(FILE_NAME));

        Category current = null;

        for (Category c : categories
        ) {
            if (c.getId().equals(id)) {
                current = c;
                break;
            }
        }

        if (current != null) {
            return current;
        }

        throw new Exception(Message.NOT_FIND_ID.getMessage() + id);
    }

    @Override
    public void delete(Category item) {
        List<Category> categories = stringToData(IOUtil.read(FILE_NAME));

        Category removeCategory = null;
        for (Category c : categories
        ) {
            if (c.getId().equals(item.getId())) {
                removeCategory = c;
                break;
            }
        }

        categories.remove(removeCategory);
        IOUtil.writeList(FILE_NAME, dataToString(categories));
    }

    @Override
    public void update(Category item) throws Exception {
        delete(getById(item.getId()));
        save(item);
    }

    @Override
    public void save(Category item) {

        IOUtil.write(FILE_NAME, dataToString(item));
    }

    @Override
    public List<Category> getAll() {

        return stringToData(IOUtil.read(FILE_NAME));
    }

    @Override
    public Long getLastId() {
        List<Category> categories = stringToData(IOUtil.read(FILE_NAME));
        Collections.sort(categories, Comparator.comparing(BaseEntity::getId));

        if (categories.size() != 0) {
            return categories.get(categories.size() - 1).getId();
        }

        return 0L;
    }

    @Override
    public List<Category> stringToData(List<String> data) {

        List<Category> categories = new ArrayList<>();

        for (String str : data
        ) {
            String[] parts = str.split(",");
            Category category = new Category();
            category.setId(Long.parseLong(parts[0]));
            category.setName(parts[1]);
            categories.add(category);
        }

        return categories;
    }

    @Override
    public List<String> dataToString(List<Category> categories) {
        List<String> data = new ArrayList<>();
        for (Category c : categories) {
            data.add(dataToString(c));
        }
        return data;
    }

    @Override
    public String dataToString(Category category) {
        return category.getId() + "," + category.getName();
    }
}
