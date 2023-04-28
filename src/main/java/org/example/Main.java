package org.example;

import dao.UserDAOImpl;
import models.Lot;
import models.User;
import models.UserType;

public class Main {
    public static void main(String[] args) {
        User user = new User(UserType.GUEST);
        user.setUserBalance(1000.0);
        // user.setUserType(UserType.ADMIN);

        Lot lot1 = new Lot();
        lot1.setMinPrice(100.0);
        Lot lot2 = new Lot();
        lot2.setCurrentPrice(2000.0);

        lot1.setUserOwner(user);
        lot2.setUserOwner(user);

        user.addUserLot(lot1);
        user.stayLastCustomerIn(lot2);
        System.out.println(user);

        UserDAOImpl userDAO = new UserDAOImpl();

        // System.out.println("Delete ALL: " + userDAO.deleteAll());

        System.out.println("Add User: " + userDAO.add(user));
        User updUser = userDAO.update(user);
        System.out.println("Update User: " + updUser);

        System.out.println("All users:\n" + userDAO.getAll());

        // UserManager userManager = new UserManager();
        // userManager.init();
        // System.out.println(userManager.saveUser(user1));
        // List<User> users = userManager.getAllUsers();
        // System.out.println(users);

        // User userForUpdate = userManager.getUserById(3);
        // userForUpdate.setUserType(UserType.USER);
        // System.out.println(userForUpdate.getUserId());
        // System.out.println(userForUpdate.getUserType());
        // userManager.updateUser(userForUpdate);

        // userManager.removeUser(userManager.getUserById(11));
    }
}