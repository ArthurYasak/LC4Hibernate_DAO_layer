package application.dao;

import application.models.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll();
    User getById(Integer userId);
    boolean add(User user);
    User update(User user);
    boolean deleteAll();
    boolean delete(Integer userId);
}
