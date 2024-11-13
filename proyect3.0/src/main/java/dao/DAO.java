
package dao;

import java.util.List;

public interface DAO<T> {
    void save(T item);
    void delete(int id);
    T findById(int id);
    List<T> findAll();
}
