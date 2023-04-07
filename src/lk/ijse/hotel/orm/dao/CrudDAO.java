package lk.ijse.hotel.orm.dao;

import com.sun.xml.bind.v2.model.core.ID;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO{

    List<T> loadAll() throws Exception;
    String save(T obj) throws Exception;
    void update(T obj) throws Exception;
    void delete(T obj) throws Exception;
    T getObject(String id) throws Exception;

    String generateID() throws Exception;
}
