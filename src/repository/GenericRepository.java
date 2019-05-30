package repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    T getById(ID id) throws Exception;

    /*void add(T item) throws Exception;*/

    void delete(T item) throws Exception;

    void update(T item) throws Exception;

    void save(T item);

    List<T> getAll() throws Exception;

    ID getLastId() throws Exception;

    List<T> stringToData(List<String> items) throws Exception;

    List<String> dataToString(List<T> items);
    String dataToString(T item);
}
