package repository;

import java.util.ArrayList;

public interface GenericRepository<T, ID> {

    T get(ID id) throws Exception;

    void add(T item) throws Exception;

    void delete(ID id) throws Exception;

    void update(T item) throws Exception;

    void save();

    ArrayList<T> getAll();

    ID getLastId();
}
