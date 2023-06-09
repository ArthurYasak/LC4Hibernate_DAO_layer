package application.dao;

import application.models.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll() throws Exception;
    User getById(Integer userId) throws Exception;
    void add(User user) throws Exception;
    User update(User user) throws Exception;
    void deleteAll() throws Exception;
    void deleteById(Integer userId) throws Exception;
}
