package service;

import java.util.List;

public interface GenericService<T, ID> {
        T getById(ID id) throws Exception;

        void delete(ID id) throws Exception;

        List<T> getAll() throws Exception;
}
