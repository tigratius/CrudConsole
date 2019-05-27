package service;

import java.util.ArrayList;

public interface GenericService<T, ID> {
        T get(ID id) throws Exception;

        void delete(ID id) throws Exception;

        ArrayList<T> getAll();

        void save();
}
