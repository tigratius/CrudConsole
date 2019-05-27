package controller;

import java.util.ArrayList;

public interface GenericController<T, ID> {

    ArrayList<T> getAll();

    void save();

    T get(ID id) throws Exception;

    void delete(ID id) throws Exception;
}
